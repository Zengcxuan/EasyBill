package com.tcl.easybill.base;



import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListListener;
import cn.bmob.v3.listener.UpdateListener;
import com.tcl.easybill.Utils.BillUtils;
import com.tcl.easybill.pojo.ShareBill;
import com.tcl.easybill.pojo.TotalBill;

public class BmobRepository {

    private static final String TAG = "BmobRepository";

    private static volatile BmobRepository sInstance;

    private BmobRepository() {
    }

    public static BmobRepository getInstance() {
        if (sInstance == null) {
            synchronized (BmobRepository.class) {
                if (sInstance == null) {
                    sInstance = new BmobRepository();
                }
            }
        }
        return sInstance;
    }
    /**
     * 删除账单
     */
    public void deleteBills(String id){
        ShareBill shareBill = new ShareBill();
        shareBill.setObjectId(id);
        shareBill.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null){
                    Log.e(TAG, "done: 成功 " );
                }else {
                    Log.e(TAG, "done: 失败" );
                }
            }
        });
    }
    /**********************批量操作***************************/
    /**
     * 批量上传账单
     *
     * @param list
     */
    public void saveBills(List<BmobObject> list, final List<TotalBill> listB) {
        new BmobBatch().insertBatch(list).doBatch(new QueryListListener<BatchResult>() {

            @Override
            public void done(List<BatchResult> o, BmobException e) {
                if (e == null) {
                    for (int i = 0, n = o.size(); i < n; i++) {
                        if (o.get(i).isSuccess()) {
                            //上传成功后更新本地账单，否则会重复同步
                            TotalBill TotalBill = listB.get(i);
                            TotalBill.setRid(o.get(i).getObjectId());
                            LocalRepository.getInstance().updateTotalBillByBmob(TotalBill);
                        }
                    }

                }
            }
        });
    }

    /**
     * 批量更新账单
     *
     * @param list
     */
    public void updateBills(List<BmobObject> list) {

        new BmobBatch().updateBatch(list).doBatch(new QueryListListener<BatchResult>() {

            @Override
            public void done(List<BatchResult> o, BmobException e) {
                if (e == null) {

                }
            }
        });
    }

    /**
     * 批量更新账单
     *
     * @param list
     */
    public void deleteBills(List<BmobObject> list) {

        new BmobBatch().deleteBatch(list).doBatch(new QueryListListener<BatchResult>() {

            @Override
            public void done(List<BatchResult> o, BmobException e) {
                if (e == null) {

                }
            }
        });
    }

    /**************************同步账单******************************/
    /**
     * 同步账单
     */
    public void syncBill(String userid) {

        BmobQuery<ShareBill> query = new BmobQuery<>();
        query.addWhereEqualTo("userid", userid);
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(500);
        //执行查询方法
        query.findObjects(new FindListener<ShareBill>() {
            @Override
            public void done(List<ShareBill> object, BmobException e) {
                if (e == null) {
                    List<TotalBill> TotalBills = LocalRepository.getInstance().getTotalBills();
                    //需要上传的账单
                    List<BmobObject> listUpload = new ArrayList<>();
                    List<TotalBill> listTotalBillUpdate = new ArrayList<>();
                    //需要更新的账单
                    List<BmobObject> listUpdate = new ArrayList<>();
                    //需要删除的账单
                    List<BmobObject> listDelete = new ArrayList<>();

                    HashMap<String, TotalBill> bMap = new HashMap<>();


                    for (TotalBill TotalBill : TotalBills) {
                        if (TotalBill.getRid() == null) {
                            //服务器端id为空，则表示为上传
                            listUpload.add(new ShareBill(TotalBill));
                            //以便账单成功上传后更新本地数据
                            listTotalBillUpdate.add(TotalBill);
                        } else
                            bMap.put(TotalBill.getRid(), TotalBill);
                    }

                    HashMap<String, ShareBill> cMap = new HashMap<>();
                    //服务器账单==》键值对
                    for (ShareBill ShareBill : object) {
                        cMap.put(ShareBill.getObjectId(), ShareBill);
                    }

                    List<TotalBill> listsave = new ArrayList<>();
                    List<TotalBill> listdelete = new ArrayList<>();
                    for (Map.Entry<String, TotalBill> entry : bMap.entrySet()) {
                        String rid = entry.getKey();
                        TotalBill TotalBill=entry.getValue();
                        if (cMap.containsKey(rid)) {
                            if (TotalBill.getVersion() < 0) {
                                //需要删除的账单
                                listDelete.add(new ShareBill(TotalBill));
                                listdelete.add(TotalBill);
                            } else {
                                //服务器端数据过期
                                if (TotalBill.getVersion()>cMap.get(rid).getVersion()) {
                                    listUpdate.add(new ShareBill(TotalBill));
                                }
                            }
                            cMap.remove(rid);
                        }
                    }
                    //提交服务器数据的批量操作
                    if(!listUpload.isEmpty()) saveBills(listUpload,listTotalBillUpdate);
                    if(!listUpdate.isEmpty()) updateBills(listUpdate);
                    if(!listDelete.isEmpty()) deleteBills(listDelete);

                    //ShareBill==》TotalBill
                    for (Map.Entry<String, ShareBill> entry : cMap.entrySet()) {
                        //需要保存到本地的账单
                        listsave.add(BillUtils.toTotalBill(entry.getValue()));
                    }
                    //向本地数据库提交的批量操作
                    LocalRepository.getInstance().saveTotalBills(listsave);
                    LocalRepository.getInstance().deleteBills(listdelete);
                    // 发送同步成功事件
                    EventBus.getDefault().post(new SyncEvent(100));
                }
                else
                    EventBus.getDefault().post(new SyncEvent(200));
            }
        });
    }

}

package finalhomework.tcl.com.finalhomework.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.Utils.DateUtils;
import finalhomework.tcl.com.finalhomework.Utils.ProgressUtils;
import finalhomework.tcl.com.finalhomework.mvp.presenter.impl.BillPresenterImpl;
import finalhomework.tcl.com.finalhomework.mvp.views.BillView;
import finalhomework.tcl.com.finalhomework.pojo.AllSortBill;
import finalhomework.tcl.com.finalhomework.pojo.BPay;
import finalhomework.tcl.com.finalhomework.pojo.SortBill;
import finalhomework.tcl.com.finalhomework.pojo.TotalBill;

import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.FORMAT_M;
import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.FORMAT_Y;
import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.FORMAT_YMD;

/**
 * 修改账单
 */
public class BillEditActivity extends BillAddActivity implements BillView {

    //old Bill
    private Bundle bundle;

    @Override
    protected int getLayout() {
        return R.layout.activity_add;
    }


    @Override
    protected void initEventAndData() {

        presenter=new BillPresenterImpl(this);
        //获取旧数据
        setOldBill();

        //初始化分类数据
        initSortView();

        //设置日期选择器初始日期
        mYear = Integer.parseInt(DateUtils.getCurYear(FORMAT_Y));
        mMonth = Integer.parseInt(DateUtils.getCurMonth(FORMAT_M));

    }
    /**
     * 获取旧数据
     */
    private void setOldBill() {

        bundle = getIntent().getBundleExtra("bundle");
        if (bundle == null)
            return;
        //设置账单日期
        days = DateUtils.long2Str(bundle.getLong("date"), FORMAT_YMD);
        dateTv.setText(days);
        isOutcome = !bundle.getBoolean("income");
        remarkInput = bundle.getString("content");
        DecimalFormat df = new DecimalFormat("######0.00");
        String money = df.format(bundle.getDouble("cost"));
        //小数取整
        num = money.split("\\.")[0];
        //截取小数部分
        dotNum = "." + money.split("\\.")[1];
        //设置金额
        moneyTv.setText(num + dotNum);
    }

    /**
     * 通过name查询分类信息
     *
     * @param name
     * @return
     */
    private SortBill findSortByName(String name) {
        if (isOutcome) {
            for (SortBill e : noteBean.getOutSortList()) {
                if (e.getSortName() == name)
                    return e;
            }
        } else {
            for (SortBill e : noteBean.getInSortList()) {
                if (e.getSortName() == name)
                    return e;
            }
        }
        return null;
    }

    @Override
    public void loadDataSuccess(AllSortBill tData) {
        noteBean=tData;
        //成功后加载布局
        setTitleStatus();
    }

    /**
     * 设置状态
     */
    protected void setTitleStatus() {

        //设置类型
        setTitle();

        lastBean = findSortByName(bundle.getString("sortName"));
        //当前分类未查询到次账单的分类
        //存在该分类被删除的情况，以及切换账单收入支出类型
        if (lastBean==null)
            lastBean=mDatas.get(0);
        sortTv.setText(lastBean.getSortName());


        initViewPager();
    }

    /**
     * 提交账单
     */
    public void doCommit() {
        final SimpleDateFormat sdf = new SimpleDateFormat(" HH:mm:ss");
        final String crDate = days + sdf.format(new Date());
        if ((num + dotNum).equals("0.00")) {
            Toast.makeText(this, "唔姆，你还没输入金额", Toast.LENGTH_SHORT).show();
            return;
        }

        ProgressUtils.show(mContext, "正在提交...");
        presenter.update(new TotalBill(bundle.getLong("id"),bundle.getString("rid"),
                Float.valueOf(num + dotNum),remarkInput,currentUser.getObjectId(),
               "no",
                "no",
                lastBean.getSortName(),lastBean.getSortImg(),
                DateUtils.getMillis(crDate),!isOutcome,bundle.getInt("version")+1));
    }


}

package finalhomework.tcl.com.finalhomework.mvp.model.impl;


import java.util.List;

import finalhomework.tcl.com.finalhomework.base.BaseObserver;
import finalhomework.tcl.com.finalhomework.base.LocalRepository;
import finalhomework.tcl.com.finalhomework.mvp.model.BillModel;
import finalhomework.tcl.com.finalhomework.pojo.AllSortBill;
import finalhomework.tcl.com.finalhomework.pojo.SortBill;
import finalhomework.tcl.com.finalhomework.pojo.TotalBill;
import finalhomework.tcl.com.finalhomework.pojo.base;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BillMdelImpl implements BillModel {
    private BillOnListener billOnListener;

    public BillMdelImpl(BillOnListener billOnListener){
        this.billOnListener = billOnListener;
    }

    /**
     * 回调方法
     */
    public interface BillOnListener {
        void onSuccess(base bean);
        void onSuccess(AllSortBill bean);
        void onFailure(Throwable e);
    }

    @Override
    public void getNote() {
        final AllSortBill note = new AllSortBill();
        LocalRepository.getInstance().getSortBill(false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<SortBill>>(){
                    @Override
                    protected void onSuccees(List<SortBill> sortBills) throws Exception {
                        note.setOutSortList(sortBills);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        billOnListener.onFailure(e);
                    }
                });
        LocalRepository.getInstance().getSortBill(true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<SortBill>>(){
                    @Override
                    protected void onSuccees(List<SortBill> sortBills) throws Exception {
                        note.setInSortList(sortBills);
                        billOnListener.onSuccess(note);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        billOnListener.onFailure(e);
                    }
                });


    }

    @Override
    public void add(TotalBill totalBill) {
        LocalRepository.getInstance().saveTotalBill(totalBill)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<TotalBill>() {
                    @Override
                    protected void onSuccees(TotalBill totalBill) throws Exception {
                        billOnListener.onSuccess(new base());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        billOnListener.onFailure(e);
                    }
                });
    }

    @Override
    public void update(TotalBill totalBill) {
        LocalRepository.getInstance().updateTotalBill(totalBill)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<TotalBill>() {
                    @Override
                    protected void onSuccees(TotalBill totalBill) throws Exception {
                        billOnListener.onSuccess(new base());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        billOnListener.onFailure(e);
                    }
                });
    }

    @Override
    public void delete(Long id) {
        LocalRepository.getInstance().deleteTotalBillById(id);
    }

    @Override
    public void onUnsubscribe() {

    }
}

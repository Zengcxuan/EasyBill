package finalhomework.tcl.com.finalhomework.mvp.model.impl;

import java.util.List;

import finalhomework.tcl.com.finalhomework.Utils.BillUtils;
import finalhomework.tcl.com.finalhomework.base.BaseObserver;
import finalhomework.tcl.com.finalhomework.base.LocalRepository;
import finalhomework.tcl.com.finalhomework.mvp.model.TotalRecordModel;
import finalhomework.tcl.com.finalhomework.pojo.DataSum;
import finalhomework.tcl.com.finalhomework.pojo.TotalBill;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TotalRecordModelImpl implements TotalRecordModel {
    private TotalRecordOnListener listener;
    @Override
    public void getTotalRecord(String id) {
        LocalRepository.getInstance().getDataSum(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<TotalBill>>() {
                    @Override
                    protected void onSuccees(List<TotalBill> Bills) throws Exception {
                        listener.onSuccess(BillUtils.getDataSum(Bills));
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
    }

    public TotalRecordModelImpl(TotalRecordOnListener listener){
        this.listener = listener;
    }

    public interface TotalRecordOnListener {

        void onSuccess(DataSum bean);

        void onFailure(Throwable e);
    }

    @Override
    public void onUnsubscribe() {

    }
}

package finalhomework.tcl.com.finalhomework.mvp.model.impl;

import java.util.List;

import finalhomework.tcl.com.finalhomework.Utils.BillUtils;
import finalhomework.tcl.com.finalhomework.base.BaseObserver;
import finalhomework.tcl.com.finalhomework.base.LocalRepository;
import finalhomework.tcl.com.finalhomework.mvp.model.MonthDetailModel;
import finalhomework.tcl.com.finalhomework.pojo.MonthDetailAccount;
import finalhomework.tcl.com.finalhomework.pojo.TotalBill;
import finalhomework.tcl.com.finalhomework.pojo.base;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MonthDetailModelImpl implements MonthDetailModel{
    private MonthDetailOnListener listener;

    public MonthDetailModelImpl(MonthDetailOnListener listener) {
        this.listener = listener;
    }
    /**
     * 回调接口
     */
    public interface MonthDetailOnListener {

        void onSuccess(MonthDetailAccount bean);

        void onSuccess(base bean);

        void onFailure(Throwable e);
    }
    @Override
    public void getMonthDetailBills(int id, String year, String month) {
        LocalRepository.getInstance().getTotalBillByUserIdWithYM(id, year, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<TotalBill>>() {
                    @Override
                    protected void onSuccees(List<TotalBill> bBills) throws Exception {
                        listener.onSuccess(BillUtils.packageDetailList(bBills));
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
    }

    @Override
    public void getDayCost(int id, String year, String month/* String date*/) {
        LocalRepository.getInstance().getTotalBillByUserIdWithYM(id, year, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<TotalBill>>() {
                    @Override
                    protected void onSuccees(List<TotalBill> bBills) throws Exception {
                        listener.onSuccess(BillUtils.packageDetailList(bBills));
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
    }

    @Override
    public void delete(Long id) {
        LocalRepository.getInstance().deleteTotalBillById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Long>() {
                    @Override
                    protected void onSuccees(Long l) throws Exception {
                        listener.onSuccess(new base());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
    }

    @Override
    public void update(TotalBill bBill) {
        LocalRepository.getInstance()
                .updateTotalBill(bBill)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<TotalBill>() {
                    @Override
                    protected void onSuccees(TotalBill bBill) throws Exception {
                        listener.onSuccess(new base());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
    }

    @Override
    public void onUnsubscribe() {

    }
}

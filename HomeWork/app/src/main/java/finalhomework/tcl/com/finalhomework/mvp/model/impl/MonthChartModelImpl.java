package finalhomework.tcl.com.finalhomework.mvp.model.impl;

import java.util.List;

import finalhomework.tcl.com.finalhomework.Utils.BillUtils;
import finalhomework.tcl.com.finalhomework.base.BaseObserver;
import finalhomework.tcl.com.finalhomework.base.LocalRepository;
import finalhomework.tcl.com.finalhomework.mvp.model.MonthChartModel;
import finalhomework.tcl.com.finalhomework.pojo.MonthBillForChart;
import finalhomework.tcl.com.finalhomework.pojo.TotalBill;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MonthChartModelImpl implements MonthChartModel {
    private MonthChartOnListener listener;

    public MonthChartModelImpl(MonthChartOnListener listener) {
        this.listener = listener;
    }

    @Override
    public void getMonthChartBills(int id, String year, String month) {
        LocalRepository.getInstance().getTotalBillByUserIdWithYM(id, year, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<TotalBill>>() {
                    @Override
                    protected void onSuccees(List<TotalBill> bBills) throws Exception {
                        listener.onSuccess(BillUtils.packageChartList(bBills));
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
    }

    @Override
    public void getDayDetailBills(int id, String year, String month, String day) {
        LocalRepository.getInstance().getTotalBillByUserIdWithYM(id, year, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<TotalBill>>() {
                    @Override
                    protected void onSuccees(List<TotalBill> bBills) throws Exception {
                        listener.onSuccess(BillUtils.packageChartList(bBills));
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

    /**
     * 回调接口
     */
    public interface MonthChartOnListener {

        void onSuccess(MonthBillForChart bean);

        void onFailure(Throwable e);
    }
}

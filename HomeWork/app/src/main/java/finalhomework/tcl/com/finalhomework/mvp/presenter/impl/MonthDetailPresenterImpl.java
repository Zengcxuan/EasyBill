package finalhomework.tcl.com.finalhomework.mvp.presenter.impl;

import finalhomework.tcl.com.finalhomework.mvp.model.MonthDetailModel;
import finalhomework.tcl.com.finalhomework.mvp.model.impl.MonthDetailModelImpl;
import finalhomework.tcl.com.finalhomework.mvp.presenter.MonthBillPresenter;
import finalhomework.tcl.com.finalhomework.mvp.presenter.MonthDetailPresenter;
import finalhomework.tcl.com.finalhomework.mvp.views.MonthDetailView;
import finalhomework.tcl.com.finalhomework.pojo.MonthDetailAccount;
import finalhomework.tcl.com.finalhomework.pojo.TotalBill;
import finalhomework.tcl.com.finalhomework.pojo.base;

public class MonthDetailPresenterImpl extends MonthDetailPresenter implements MonthDetailModelImpl.MonthDetailOnListener {
    private MonthDetailModel monthDetailModel;
    private MonthDetailView monthDetailView;

    public MonthDetailPresenterImpl(MonthDetailView monthDetailView) {
        this.monthDetailModel=new MonthDetailModelImpl(this);
        this.monthDetailView = monthDetailView;
    }
    @Override
    public void getMonthDetailBills(int id, String year, String month) {
        monthDetailModel.getMonthDetailBills(id,year,month);
    }

    @Override
    public void getDayCost(int id, String year, String month/*,String date*/) {
        monthDetailModel.getDayCost(id,year,month/*,date*/);
    }

    @Override
    public void deleteBill(Long id) {
        monthDetailModel.delete(id);
    }

    @Override
    public void updateBill(TotalBill bBill) {
        monthDetailModel.update(bBill);
    }

    @Override
    public void onSuccess(MonthDetailAccount bean) {
        monthDetailView.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(base bean) {
        monthDetailView.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        monthDetailView.loadDataError(e);
    }
}

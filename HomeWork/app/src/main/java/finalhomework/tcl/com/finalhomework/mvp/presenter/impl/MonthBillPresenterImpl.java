package finalhomework.tcl.com.finalhomework.mvp.presenter.impl;

import finalhomework.tcl.com.finalhomework.mvp.model.MonthBillModel;
import finalhomework.tcl.com.finalhomework.mvp.model.impl.MonthBillModelImpl;
import finalhomework.tcl.com.finalhomework.mvp.presenter.MonthBillPresenter;
import finalhomework.tcl.com.finalhomework.mvp.views.MonthBillView;
import finalhomework.tcl.com.finalhomework.pojo.MonthAccount;

public class MonthBillPresenterImpl extends MonthBillPresenter implements MonthBillModelImpl.MonthBillOnListener {

    private MonthBillModel monthBillModel;
    private MonthBillView monthBillView;

    public MonthBillPresenterImpl(MonthBillView view){
        this.monthBillView = view;
        this.monthBillModel = new MonthBillModelImpl(this);
    }
    @Override
    public void getMonthBills(int id, String year, String month) {
        monthBillModel.getMonthBills(id,year,month);
    }

    @Override
    public void onSuccess(MonthAccount bean) {
        monthBillView.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        monthBillView.loadDataError(e);
    }
}

package finalhomework.tcl.com.finalhomework.mvp.presenter.impl;

import finalhomework.tcl.com.finalhomework.mvp.model.MonthChartModel;
import finalhomework.tcl.com.finalhomework.mvp.model.impl.MonthChartModelImpl;
import finalhomework.tcl.com.finalhomework.mvp.presenter.MonthChartPresenter;
import finalhomework.tcl.com.finalhomework.mvp.views.MonthChartView;
import finalhomework.tcl.com.finalhomework.pojo.MonthBillForChart;

public class MonthChartPresenterImpl extends MonthChartPresenter implements MonthChartModelImpl.MonthChartOnListener {
    private MonthChartModel model;
    private MonthChartView view;

    public MonthChartPresenterImpl(MonthChartView view) {
        this.model=new MonthChartModelImpl(this);
        this.view = view;
    }
    @Override
    public void getMonthChartBills(String id, String year, String month) {
        model.getMonthChartBills(id,year,month);
    }

    @Override
    public void getDayDetailBills(int id, String year, String month, String day) {
        model.getDayDetailBills(id,year,month,day);
    }

    @Override
    public void onSuccess(MonthBillForChart bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }
}

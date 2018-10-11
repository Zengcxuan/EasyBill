package finalhomework.tcl.com.finalhomework.mvp.presenter;

public abstract class MonthChartPresenter extends BasePresenter{
    public abstract void getMonthChartBills(int id,String year,String month);

    public abstract void getDayDetailBills(int id,String year,String month,String day);
}

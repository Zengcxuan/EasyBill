package finalhomework.tcl.com.finalhomework.mvp.model;

public interface MonthChartModel {
    /**
     * 每月账单图表数据
     */
    void getMonthChartBills(String id, String year, String month);

    void getDayDetailBills(int id,String year,String month,String day);

    void onUnsubscribe();
}

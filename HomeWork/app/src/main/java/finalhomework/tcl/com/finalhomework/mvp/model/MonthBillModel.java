package finalhomework.tcl.com.finalhomework.mvp.model;

public interface MonthBillModel {
    void getMonthBills(int id, String year, String month);

    void onUnsubscribe();
}

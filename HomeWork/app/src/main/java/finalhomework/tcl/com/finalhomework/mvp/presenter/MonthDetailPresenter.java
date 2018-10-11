package finalhomework.tcl.com.finalhomework.mvp.presenter;

import finalhomework.tcl.com.finalhomework.pojo.TotalBill;

public abstract class MonthDetailPresenter {
    public abstract void getMonthDetailBills(int id,String year,String month);
    public abstract void getDayCost(int id, String year, String month/*,String date*/);
    public abstract void deleteBill(Long id);

    public abstract void updateBill(TotalBill bBill);
}

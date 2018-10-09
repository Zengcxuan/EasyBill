package finalhomework.tcl.com.finalhomework.mvp.model;

import finalhomework.tcl.com.finalhomework.pojo.TotalBill;

public interface MonthDetailModel {
    /**
     * 每月账单详情
     */
    void getMonthDetailBills(int id, String year, String month);

    /**
     * 删除账单
     */
    void delete(Long id);

    void update(TotalBill bBill);

    void onUnsubscribe();
}

package com.tcl.easybill.mvp.model;

import com.tcl.easybill.pojo.TotalBill;

public interface MonthDetailModel {
    /**
     * 每月账单详情
     */
    void getMonthDetailBills(String id, String year, String month);

    /**
     * test
     */
    void getDayCost(int id,String year,String month/*,String date*/);
    /**
     * 删除账单
     */
    void delete(Long id);

    void update(TotalBill bBill);

    void onUnsubscribe();
}

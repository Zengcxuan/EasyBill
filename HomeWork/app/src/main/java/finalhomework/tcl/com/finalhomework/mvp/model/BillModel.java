package finalhomework.tcl.com.finalhomework.mvp.model;


import finalhomework.tcl.com.finalhomework.pojo.TotalBill;

public interface BillModel {

    void getNote();
    /**
     * 添加账单
     */
    void add(TotalBill totalBill);
    /**
     * 修改账单
     */
    void update(TotalBill totalBill);
    /**
     * 删除账单
     */
    void delete(Long id);

    void onUnsubscribe();
}

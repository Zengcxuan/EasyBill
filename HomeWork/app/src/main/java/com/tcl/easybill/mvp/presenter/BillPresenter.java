package com.tcl.easybill.mvp.presenter;


import com.tcl.easybill.pojo.TotalBill;

public abstract  class BillPresenter extends BasePresenter {

    /**
     * 获取信息
     */
    public abstract void getNote();

    /**
     * 添加账单
     */
    public abstract void add(TotalBill totalBill);

    /**
     * 修改账单
     */
    public abstract void update(TotalBill totalBill);


    /**
     * 删除账单
     */
    public abstract void delete(Long id);
}

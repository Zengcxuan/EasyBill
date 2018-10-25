package com.tcl.easybill.mvp.views;

import com.tcl.easybill.pojo.MonthDetailAccount;
import com.tcl.easybill.pojo.base;

public interface  MonthDetailView extends BaseView<MonthDetailAccount> {

    /**
     * 本地当月账单
     * @param list
     */
    void loadDataSuccess(MonthDetailAccount list);
    /**
     * 删除成功
     * @param tData
     */
    void loadDataSuccess(base tData);
}

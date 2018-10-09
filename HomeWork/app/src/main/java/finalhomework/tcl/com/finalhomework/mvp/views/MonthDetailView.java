package finalhomework.tcl.com.finalhomework.mvp.views;

import finalhomework.tcl.com.finalhomework.pojo.MonthDetailAccount;
import finalhomework.tcl.com.finalhomework.pojo.base;

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

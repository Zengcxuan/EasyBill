package finalhomework.tcl.com.finalhomework.mvp.views;

import finalhomework.tcl.com.finalhomework.pojo.AllSortBill;
import finalhomework.tcl.com.finalhomework.pojo.base;

public interface BillView extends BaseView<base>{

    void loadDataSuccess(AllSortBill tData);
}

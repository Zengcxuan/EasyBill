package finalhomework.tcl.com.finalhomework.pojo;

import java.util.List;

public class AllSortBill extends base {
    private List<SortBill> outSortList;
    private List<SortBill> inSortList;

    public List<SortBill> getOutSortList() {
        return outSortList;
    }

    public void setOutSortList(List<SortBill> outSortList) {
        this.outSortList = outSortList;
    }

    public List<SortBill> getInSortList() {
        return inSortList;
    }

    public void setInSortList(List<SortBill> inSortList) {
        this.inSortList = inSortList;
    }
}

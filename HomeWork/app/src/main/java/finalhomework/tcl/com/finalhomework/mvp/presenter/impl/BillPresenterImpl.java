package finalhomework.tcl.com.finalhomework.mvp.presenter.impl;

import finalhomework.tcl.com.finalhomework.mvp.model.impl.BillMdelImpl;
import finalhomework.tcl.com.finalhomework.mvp.model.BillModel;
import finalhomework.tcl.com.finalhomework.mvp.presenter.BillPresenter;
import finalhomework.tcl.com.finalhomework.mvp.views.BillView;
import finalhomework.tcl.com.finalhomework.pojo.AllSortBill;
import finalhomework.tcl.com.finalhomework.pojo.TotalBill;
import finalhomework.tcl.com.finalhomework.pojo.base;

public class BillPresenterImpl extends BillPresenter implements BillMdelImpl.BillOnListener {
    private BillModel model;
    private BillView view;

    public BillPresenterImpl(BillView view) {
        this.model=new BillMdelImpl(this);
        this.view = view;
    }

    @Override
    public void onSuccess(base bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(AllSortBill bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }

    @Override
    public void getNote() {
        model.getNote();
    }

    @Override
    public void add(TotalBill bBill) {
        model.add(bBill);
    }

    @Override
    public void update(TotalBill bBill) {
        model.update(bBill);
    }

    @Override
    public void delete(Long id) {
        model.delete(id);
    }
}

package finalhomework.tcl.com.finalhomework.mvp.presenter.impl;

import finalhomework.tcl.com.finalhomework.mvp.model.TotalRecordModel;
import finalhomework.tcl.com.finalhomework.mvp.model.impl.TotalRecordModelImpl;
import finalhomework.tcl.com.finalhomework.mvp.presenter.TotalRecordPresenter;
import finalhomework.tcl.com.finalhomework.mvp.views.TotalRecordView;
import finalhomework.tcl.com.finalhomework.pojo.DataSum;
import finalhomework.tcl.com.finalhomework.pojo.TotalBill;

public class TotalRecordPresenterImpl extends TotalRecordPresenter implements TotalRecordModelImpl.TotalRecordOnListener {
    private TotalRecordModel model;
    private TotalRecordView view;

    public TotalRecordPresenterImpl(TotalRecordView view) {
       this.model = new TotalRecordModelImpl(this);
       this.view = view;
    }

    @Override
    public void getTotalRecord(String id) {
        model.getTotalRecord(id);
    }

    @Override
    public void onSuccess(DataSum bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }
}

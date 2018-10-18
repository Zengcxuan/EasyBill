package finalhomework.tcl.com.finalhomework.mvp.presenter.impl;
import finalhomework.tcl.com.finalhomework.mvp.model.UserInfoModel;
import finalhomework.tcl.com.finalhomework.mvp.model.impl.UserInfoModelImp;
import finalhomework.tcl.com.finalhomework.mvp.presenter.UserInfoPresenter;
import finalhomework.tcl.com.finalhomework.mvp.views.UserInfoView;
import finalhomework.tcl.com.finalhomework.pojo.Person;
import finalhomework.tcl.com.finalhomework.pojo.User;

public class UserInfoPresenterImp extends UserInfoPresenter implements UserInfoModelImp.UserInfoOnListener {

    private UserInfoModel model;
    private UserInfoView view;

    public UserInfoPresenterImp(UserInfoView view) {
        this.model=new UserInfoModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(Person user) {
        view.loadDataSuccess(user);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }

    @Override
    public void update(Person user) {
        model.update(user);
    }
}

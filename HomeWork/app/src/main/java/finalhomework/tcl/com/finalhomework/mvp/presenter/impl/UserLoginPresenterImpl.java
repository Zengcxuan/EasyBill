package finalhomework.tcl.com.finalhomework.mvp.presenter.impl;

import finalhomework.tcl.com.finalhomework.mvp.model.UserLoginModel;
import finalhomework.tcl.com.finalhomework.mvp.model.impl.UserLoginModelImpl;
import finalhomework.tcl.com.finalhomework.mvp.presenter.UserLoginPresenter;
import finalhomework.tcl.com.finalhomework.mvp.views.UserLoginView;
import finalhomework.tcl.com.finalhomework.pojo.User;

public class UserLoginPresenterImpl extends UserLoginPresenter implements UserLoginModelImpl.UserLoginOnListener {
    private UserLoginView view;
    private UserLoginModel model;

    public UserLoginPresenterImpl (UserLoginView view){
        this.model = new UserLoginModelImpl(this);
        this.view = view;
    }

    @Override
    public void login(String username, String password) {
        model.login(username,password);
    }

    @Override
    public void signup(String username, String password, String mail) {
        model.signup(username,password,mail);
    }

    @Override
    public void onSuccess(User user) {
        view.loadDataSuccess(user);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }
}

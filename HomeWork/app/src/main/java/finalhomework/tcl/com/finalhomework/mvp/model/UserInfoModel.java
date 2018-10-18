package finalhomework.tcl.com.finalhomework.mvp.model;


import finalhomework.tcl.com.finalhomework.pojo.User;

public interface UserInfoModel {

    void update(User user);

    void onUnsubscribe();
}

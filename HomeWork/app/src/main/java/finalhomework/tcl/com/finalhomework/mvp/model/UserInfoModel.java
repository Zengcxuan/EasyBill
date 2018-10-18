package finalhomework.tcl.com.finalhomework.mvp.model;


import finalhomework.tcl.com.finalhomework.pojo.Person;
import finalhomework.tcl.com.finalhomework.pojo.User;

public interface UserInfoModel {

    void update(Person user);

    void onUnsubscribe();
}

package finalhomework.tcl.com.finalhomework.mvp.model.impl;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import finalhomework.tcl.com.finalhomework.mvp.model.UserLoginModel;
import finalhomework.tcl.com.finalhomework.pojo.User;

public class UserLoginModelImpl implements UserLoginModel  {
    private UserLoginOnListener listener;

    public UserLoginModelImpl(UserLoginOnListener listener){
        this.listener = listener;
    }

    @Override
    public void login(String username, String password) {
        User.loginByAccount(username, password, new LogInListener<User>() {

            @Override
            public void done(User user, BmobException e) {
               if (e == null){
                   listener.onSuccess(user);
               }else {
                   listener.onFailure(e);
               }
            }
        });
    }

    @Override
    public void signup(String username, String password, String mail) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(mail);

       user.signUp(new SaveListener<User>() {

           @Override
           public void done(User user, BmobException e) {
               if (e==null){
                   listener.onSuccess(user);
               }else {
                   listener.onFailure(e);
               }

           }
       });
    }

    @Override
    public void onUnsubscribe() {

    }
    /**
     * 回调接口
     */
    public interface UserLoginOnListener {

        void onSuccess(User user);

        void onFailure(Throwable e);
    }
}

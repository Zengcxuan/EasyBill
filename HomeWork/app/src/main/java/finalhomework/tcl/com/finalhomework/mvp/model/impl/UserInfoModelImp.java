package finalhomework.tcl.com.finalhomework.mvp.model.impl;


import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import finalhomework.tcl.com.finalhomework.mvp.model.UserInfoModel;
import finalhomework.tcl.com.finalhomework.pojo.Person;
import finalhomework.tcl.com.finalhomework.pojo.User;

public class UserInfoModelImp implements UserInfoModel {

    private UserInfoOnListener listener;

    public UserInfoModelImp(UserInfoOnListener listener) {
        this.listener = listener;
    }

    @Override
    public void update(Person user) {
        user.update(user.getObjectId(),new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null)
                    listener.onSuccess(new Person());
                else
                    listener.onFailure(e);
            }
        });

    }

    @Override
    public void onUnsubscribe() {

    }

    /**
     * 回调接口
     */
    public interface UserInfoOnListener {

        void onSuccess(Person user);

        void onFailure(Throwable e);
    }
}

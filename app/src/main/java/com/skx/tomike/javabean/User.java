package com.skx.tomike.javabean;

import com.skx.tomike.missile.bean.UserSetting;

/**
 * Created by shiguotao on 2016/11/13.
 */
public class User implements Cloneable {
    public String userId;
    public String userName;
    public String password;
    public UserSetting userSetting;

    public User() {
        userId = "我是uerId";
    }

    @Override
    public User clone() {
        User user = null;
        try {
            user = (User) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        用这种方式的话，属性中如果有引用对象，也必须实现复制（/克隆）功能
        User user = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            user = (User) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        return user;
    }

    @Override
    public String toString() {
        return "userId:" + userId + "/" +
                "userName:" + userName + "/" + "password:" + password + "/" +
                "settingName:" + userSetting.settingName + "settingValue:" + userSetting.settingValue;
    }
}

package com.skx.tomike.cannon.bean

/**
 * 用户信息
 */
class User : Cloneable {

    var userId: String? = null
    var name: String? = null
    var nickName: String? = null
    var password: String? = null
    var headImage: String? = null
    var userSetting: UserSetting? = null

    public override fun clone(): User {
        var user: User? = null
        try {
            user = super.clone() as User
        } catch (e: Exception) {
            e.printStackTrace()
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
        return user as User
    }

    override fun toString(): String {
        return "userId:" + userId + "/" +
                "userName:" + name + "/" + "password:" + password + "/" +
                "settingName:" + userSetting!!.settingName + "settingValue:" + userSetting!!.settingValue
    }
}

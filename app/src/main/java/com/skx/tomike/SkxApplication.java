package com.skx.tomike;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

public class SkxApplication extends TinkerApplication {

    public SkxApplication() {
        // 注意：除了构造方法之外，最好不要引入其他的类，这将导致它们无法通过补丁修改。
        super( //tinkerFlags, tinker支持的类型，dex,library，还是全部都支持！
                ShareConstants.TINKER_ENABLE_ALL,
                //ApplicationLike的实现类，只能传递字符串
                "com.skx.tomike.TomikeAppLike",
                //Tinker的加载器，一般来说用默认的即可
                "com.tencent.tinker.loader.TinkerLoader",
                //tinkerLoadVerifyFlag, 运行加载时是否校验dex与,ib与res的Md5
                false);
    }
}

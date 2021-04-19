package com.skx.tomike;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import java.util.Stack;

public class SkxApplication extends TinkerApplication {

    private static final Stack<Activity> activityStack = new Stack<>();

    public SkxApplication() {
        // 注意：除了构造方法之外，最好不要引入其他的类，这将导致它们无法通过补丁修改。
        super(
                //tinkerFlags, tinker支持的类型，dex,library，还是全部都支持！
                ShareConstants.TINKER_ENABLE_ALL,
                //ApplicationLike的实现类，只能传递字符串。注意：不能使用class.getName()，
                "com.skx.tomike.TomikeAppLike");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new SwitchBackgroundCallbacks());
    }

    private static class SwitchBackgroundCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            activityStack.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            activityStack.remove(activity);
        }
    }
}

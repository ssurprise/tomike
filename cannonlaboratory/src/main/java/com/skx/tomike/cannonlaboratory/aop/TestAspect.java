package com.skx.tomike.cannonlaboratory.aop;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


/**
 * Created by shiguotao on 2018/3/22.
 */
@Aspect
public class TestAspect {

    private static final String TAG = "TestAspect";

    private static final String POINTCUT_METHOD = "call(@com.skx.tomike.aop.LogRecordAnnotation * *(..))";


    @Pointcut(POINTCUT_METHOD)
    public void logPointcuts(LogRecordAnnotation logRecordAnnotation) {
    }

//    @Before("logPointcuts()")
//    public void logPointcutsTest(JoinPoint joinPoint) throws Throwable {
//        Object target = joinPoint.getTarget();
//        Object aThis = joinPoint.getThis();
//        Log.e(TAG, "aop 测试!" + target.getClass().getSimpleName() + "--" + aThis.getClass().getSimpleName());
//    }


//    @Pointcut("execution(*com.terence.aop.aspectj.biz.*Biz.**(..))")
//    public void pointcut(){}


//    @Before("within(com.skx.tomike.fragment.business.*) && execution(*  android.support.v4.app.Fragment.on**(..))")
//    public void onActivityMethodWithin(JoinPoint joinPoint) throws Throwable {
//        String key = joinPoint.getSignature().toString();
//        Log.e(TAG, "before - 在 " + key + " 方法体之前执行");
//
//        /**
//         * eg:
//         * toLongString :   protected void com.skx.tomike.activity.function.AopTestActivity.onCreate(android.os.Bundle)
//         * toShortString :  AopTestActivity.onCreate(..)
//         * toShortString :  void com.skx.tomike.activity.function.AopTestActivity.onCreate(Bundle)
//         */
//    }

//    @Before("target(com.skx.tomike.activity.function.AopTestActivity) && execution(* android.app.Activity.on**(..))")
//    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
//        String key = joinPoint.getSignature().toString();
//        Log.e(TAG, "before - 在 " + key + " 方法体之前执行");
//
//        /**
//         * eg:
//         * toLongString :   protected void com.skx.tomike.activity.function.AopTestActivity.onCreate(android.os.Bundle)
//         * toShortString :  AopTestActivity.onCreate(..)
//         * toShortString :  void com.skx.tomike.activity.function.AopTestActivity.onCreate(Bundle)
//         */
//    }

    //    =========================== Advice - After 测试==================================================
//    @After("execution(* android.app.Activity.on**(..))")
//    public void onActivityMethodAfter(JoinPoint joinPoint) throws Throwable {
//        Log.e(TAG, "after  - 在Activity 的on**（）方法体之后执行");
//    }


    @Before("execution(* eat(..))")
//    @Before("execution(* com.skx.tomike.aop.Animal.eat(..))")
//    @Before("execution(* eat(..)) && target(com.skx.tomike.aop.Animal)")
    public void animalEat(JoinPoint joinPoint) throws Throwable {
        Log.e(TAG, "Entering " + joinPoint.getSourceLocation());
        /*
        条件：execution(* eat(..))
        10-30 14:33:23.338 26822-26822/com.skx.tomike E/TestAspect: Entering AopTestActivity.java:109
        Entering Dog.java:8
        10-30 14:33:23.338 26822-26822/com.skx.tomike E/DOG: 吃骨头
        10-30 14:33:23.338 26822-26822/com.skx.tomike E/TestAspect: Entering Cat.java:8
        10-30 14:33:23.338 26822-26822/com.skx.tomike E/CAT: 吃鱼

        条件: execution(* com.skx.tomike.aop.Animal.eat(..))
        结果:
        10-30 14:25:15.858 20182-20182/com.skx.tomike E/TestAspect: Entering Dog.java:8
        10-30 14:25:15.858 20182-20182/com.skx.tomike E/DOG: 吃骨头
        10-30 14:25:15.858 20182-20182/com.skx.tomike E/TestAspect: Entering Cat.java:8
        10-30 14:25:15.858 20182-20182/com.skx.tomike E/CAT: 吃鱼
        */
    }


    //    @Before("execution(* eat(..)) && target(com.skx.tomike.aop.Dog)")
//    public void animalEatTarget(JoinPoint joinPoint) throws Throwable {
//        Log.e(TAG, "Entering " + joinPoint.getSourceLocation());
//        /*
//        条件: execution(* eat(..)) && target(com.skx.tomike.aop.Animal)
//        结果:
//        10-30 14:25:15.858 20182-20182/com.skx.tomike E/TestAspect: Entering Dog.java:8
//        10-30 14:25:15.858 20182-20182/com.skx.tomike E/DOG: 吃骨头
//        10-30 14:25:15.858 20182-20182/com.skx.tomike E/TestAspect: Entering Cat.java:8
//        10-30 14:25:15.858 20182-20182/com.skx.tomike E/CAT: 吃鱼
//
//        条件：execution(* eat(..)) && target(com.skx.tomike.aop.Dog)
//        结果：
//        10-30 14:46:22.738 1947-1947/com.skx.tomike E/TestAspect: Entering Dog.java:8
//        10-30 14:46:22.738 1947-1947/com.skx.tomike E/DOG: 吃骨头
//        10-30 14:46:22.738 1947-1947/com.skx.tomike E/CAT: 吃鱼
//
//        结论：target 的获取有继承关系
//        */
//    }


    //    =========================== Advice - 两种写法==================================================

//    private static final String POINTCUT_ACTIVITY_ONMEHOD = "execution(* android.app.Activity.on**(..))";
//
//    @Pointcut(POINTCUT_ACTIVITY_ONMEHOD)
//    public void activityOnMethod(){
//
//    }
//
//    @After("activityOnMethod()")
//    public void onActivityMethodAfter(JoinPoint joinPoint) throws Throwable {
//        Log.e(TAG, "after - 在Activity 的on**（）方法体之后执行");
//    }
//
//
//    @After("execution(* android.app.Activity.on**(..))")
//    public void onActivityMethodAfter(JoinPoint joinPoint) throws Throwable {
//        Log.e(TAG, "after - 在Activity 的on**（）方法体之后执行");
//    }


}

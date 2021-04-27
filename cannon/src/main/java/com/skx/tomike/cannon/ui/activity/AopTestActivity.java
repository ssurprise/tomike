package com.skx.tomike.cannon.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.skx.tomike.cannon.aop.Animal;
import com.skx.tomike.cannon.aop.Cat;
import com.skx.tomike.cannon.aop.Dog;
import com.skx.tomike.cannon.R;

import java.util.ArrayList;
import java.util.List;

/**
 * AOP 测试
 *
 * @author shiguotao
 */
public class AopTestActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "AopTestActivity";

    private Button mBtnThis;
    private Button mBtnTarget;
    private Button mBtnArgs;
    private Button mBtnAdviceBefore;
    private Button mBtnAdviceAfter;
    private Button mBtnAdviceAround;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop_test);

//        mBtnThis =
        findViewById(R.id.aop_this).setOnClickListener(this);
//        mBtnTarget =
        findViewById(R.id.aop_target).setOnClickListener(this);
//        mBtnArgs =
        findViewById(R.id.aop_args).setOnClickListener(this);

//        mBtnAdviceBefore =
        findViewById(R.id.aop_advice_before).setOnClickListener(this);
//        mBtnAdviceAfter =
        findViewById(R.id.aop_advice_after).setOnClickListener(this);
//        mBtnAdviceAround =
        findViewById(R.id.aop_advice_around).setOnClickListener(this);
        findViewById(R.id.aop_custom_annotation).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.aop_this) {
            thisTest();
        } else if (id == R.id.aop_target) {
            targetTest();
        } else if (id == R.id.aop_args) {
            argsTest();
        } else if (id == R.id.aop_advice_before) {
            beforeTest();
        } else if (id == R.id.aop_advice_after) {
            afterTest();
        } else if (id == R.id.aop_advice_around) {
            aroundTest();
        } else if (id == R.id.aop_custom_annotation) {
            customAnnotation();
        }
    }

    //    @LogRecordAnnotation
    private void customAnnotation() {
        Log.e(TAG, "自定义的注解");
    }

    private void thisTest() {
        List<Animal> list = new ArrayList<>();
        list.add(new Dog());
        list.add(new Cat());
        eat(list);
    }

    private void targetTest() {
        List<Animal> list = new ArrayList<>();
        list.add(new Dog());
        list.add(new Cat());
        eat(list);
        Log.e(TAG, "target 内容");
    }

    private void argsTest() {
        Log.e(TAG, "arg 内容");
    }

    private void beforeTest() {
        Log.e(TAG, "Advice - Before 内容");
    }

    private void afterTest() {
        Log.e(TAG, "Advice - After 内容");
    }

    private void aroundTest() {
        Log.e(TAG, "Advice - Around 内容");
    }


    public void eat(List<Animal> list) {
        for (Animal a : list) {
            a.eat();
        }
    }
}

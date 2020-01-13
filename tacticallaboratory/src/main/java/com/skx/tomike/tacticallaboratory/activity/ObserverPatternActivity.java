package com.skx.tomike.tacticallaboratory.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.skx.tomike.tacticallaboratory.R;

/**
 * 观察者模式
 * <p>
 * <p>
 *
 * @author shiguotao
 */
public class ObserverPatternActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        refreshView();
    }

    private void initializeView() {
        setContentView(R.layout.activity_pattern_observer);
        TextView note = findViewById(R.id.clonePattern_note);
    }

    /*
    观察者模式又被称作发布/订阅模式，观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。
    这个主题对象在状态发生变化时，会通知所有观察者对象，使它们能够自动更新自己。
     */
    private void refreshView() {

    }
}

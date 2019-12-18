package com.skx.tomike.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.skx.tomike.R;

import java.util.Random;

public class TextSwitcherActivity extends Activity {

    private final String NAME_COFFEE = "咖啡机";
    private final String NAME_CAKE = "面包机";

    private TextSwitcher textSwitcher;
    private TextSwitcher textSwitcher2;

    private String index = NAME_COFFEE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_switcher);

        textSwitcher = findViewById(R.id.textSwitcher);

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);

        textSwitcher2 = findViewById(R.id.textSwitcher2);
        textSwitcher2.setInAnimation(this, R.anim.show);
        textSwitcher2.setOutAnimation(this, R.anim.hide);
        textSwitcher2.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(TextSwitcherActivity.this);
                textView.setTextSize(20);
                textView.setTextColor(Color.BLACK);

                return textView;
            }
        });

        findViewById(R.id.textSwitcher_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textSwitcher.setText(String.valueOf(new Random().nextInt()));

                if (NAME_COFFEE.equalsIgnoreCase(index)) {
                    index = NAME_CAKE;

                } else if (NAME_CAKE.equalsIgnoreCase(index)) {
                    index = NAME_COFFEE;
                }
                textSwitcher2.setText(index);
            }
        });


    }
}

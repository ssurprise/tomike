package com.skx.tomike.tanklaboratory.animation.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.skx.tomike.tanklaboratory.R;

public class CircularRevealActivity extends AppCompatActivity {

    private ImageView circularReveal_1;
    private boolean isShow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);

        circularReveal_1 = findViewById(R.id.circularReveal_1);
    }

    public void onBtnClick(View view) {
        isShow = !isShow;
        if (isShow) {
            circularRevealShow();
        } else {
            circularRevealHidden();
        }
    }

    public void circularRevealShow() {
        float finalRadius = (float) Math.hypot(circularReveal_1.getWidth(), circularReveal_1.getHeight());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animator anim = ViewAnimationUtils.createCircularReveal(circularReveal_1,
                    0, 0, 0, finalRadius);
            circularReveal_1.setVisibility(View.VISIBLE);
            anim.start();
        }
    }

    public void circularRevealHidden() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            float finalRadius = (float) Math.hypot(circularReveal_1.getWidth(), circularReveal_1.getHeight());
            Animator anim = ViewAnimationUtils.createCircularReveal(circularReveal_1,
                    0, 0, finalRadius, 0);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    circularReveal_1.setVisibility(View.INVISIBLE);
                }
            });
            anim.start();
        }
    }
}

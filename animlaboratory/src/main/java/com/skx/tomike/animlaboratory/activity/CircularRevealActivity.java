package com.skx.tomike.animlaboratory.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.skx.tomike.animlaboratory.R;


public class CircularRevealActivity extends AppCompatActivity {

    private ImageView circularReveal_1;
    private View circularReveal_2;
    private boolean isShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);

        circularReveal_1 = (ImageView) findViewById(R.id.circularReveal_1);
        circularReveal_2 = findViewById(R.id.circularReveal_2);

//        if (circularReveal_1 != null) {
//            circularReveal_1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Animator animator;
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                        animator = ViewAnimationUtils.createCircularReveal(circularReveal_1, circularReveal_1.getWidth() / 2, circularReveal_1.getHeight() / 2, circularReveal_1.getWidth(), 0);
//                        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//                        animator.setDuration(2000);
//                        animator.start();
//                    }
//                }
//            });
//        }
        if (circularReveal_2 != null) {
            circularReveal_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animator animator;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        animator = ViewAnimationUtils.createCircularReveal(circularReveal_2, circularReveal_2.getWidth() / 2, circularReveal_2.getHeight() / 2, circularReveal_2.getWidth(), 0);
                        animator.setInterpolator(new AccelerateInterpolator());
                        animator.setDuration(2000);
                        animator.start();
                    }
                }
            });
        }
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
            Animator anim = ViewAnimationUtils.createCircularReveal(circularReveal_1, 0, 0, 0, finalRadius);
            circularReveal_1.setVisibility(View.VISIBLE);
            anim.start();
        }
    }

    public void circularRevealHidden() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Float finalRadius = (float) Math.hypot(circularReveal_1.getWidth(), circularReveal_1.getHeight());
            Animator anim = ViewAnimationUtils.createCircularReveal(circularReveal_1, 0, 0, finalRadius, 0);
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

package com.skx.tomike.animlaboratory.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.skx.tomike.animlaboratory.R;

/**
 * 属性动画事例
 */
public class PropertyAnimatorActivity extends AppCompatActivity {
    //    TextView textView_object;
//    TextView textView_value;
    ImageView imageView_animator;
    ImageView imageView2_animator;
//    RelativeLayout container;
//    ViewPropertyHelper textViewTest;
//    ViewPropertyHelper layoutViewProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animatior);
        initializeView();
        refreshView();
        installListener();
    }

    private void initializeView() {
//        textView_object = (TextView) findViewById(R.id.textView_object);
//        textView_value = (TextView) findViewById(R.id.textView_value);
//        container = (RelativeLayout) findViewById(R.id.container);
        imageView_animator = findViewById(R.id.imageView_animator);
        imageView2_animator = findViewById(R.id.imageView2_animator);
    }

    private void refreshView() {
//        textViewTest = new ViewPropertyHelper(textView_value);
//        layoutViewProperty = new ViewPropertyHelper(container);
    }

    private void installListener() {
//        textView_object.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ObjectAnimator.ofFloat(textView_object, "rotationX", 0.0f, 360.0f).setDuration(500).start();
//            }
//        });
//        textView_value.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ValueAnimator valueAnimator = ValueAnimator.ofInt(300, 500);
//                valueAnimator.setDuration(500);
//                valueAnimator.setInterpolator(new LinearInterpolator());
//                valueAnimator.setTarget(textViewTest);
//                valueAnimator.start();
//                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        Integer animatedValue = (Integer) animation.getAnimatedValue();
//                        textViewTest.setHeight(animatedValue);
//                    }
//                });
//            }
//        });
//        container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ValueAnimator valueAnimator = ValueAnimator.ofInt(48, 210);
//                valueAnimator.setDuration(500);
//                valueAnimator.setInterpolator(new LinearInterpolator());
//                valueAnimator.setTarget(layoutViewProperty);
//                valueAnimator.start();
//                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        Integer animatedValue = (Integer) animation.getAnimatedValue();
//                        layoutViewProperty.setPadding(animatedValue);
//                    }
//                });
//            }
//        });

        imageView_animator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isFavorite = !isFavorite;
                if (!isFavorite) {
                    AnimationSet animationSet = new AnimationSet(true);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0f, 1, 0f,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
                    scaleAnimation.setDuration(200);
                    animationSet.addAnimation(scaleAnimation);
                    animationSet.setFillAfter(true); //让其保持动画结束时的状态。
                    imageView_animator.startAnimation(animationSet);
                } else {
                    AnimationSet animationSet = new AnimationSet(true);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
                    scaleAnimation.setDuration(200);
                    animationSet.addAnimation(scaleAnimation);
                    animationSet.setFillAfter(true); //让其保持动画结束时的状态。
                    imageView_animator.startAnimation(animationSet);
                }

            }
        });

        imageView2_animator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(imageView2_animator, "rotationY", 0.0f, 180.0f).setDuration(500).start();
            }
        });
    }

    private boolean isFavorite = true;
}

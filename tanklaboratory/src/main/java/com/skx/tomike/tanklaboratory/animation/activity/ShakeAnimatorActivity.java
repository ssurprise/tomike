package com.skx.tomike.tanklaboratory.animation.activity;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.skx.tomike.tanklaboratory.R;

/**
 * 属性动画事例
 */
public class ShakeAnimatorActivity extends AppCompatActivity {

    ImageView mTargetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_animatior);
        mTargetView = findViewById(R.id.shakeAnimator_target);
    }

    public void propertyAnimator(View view) {
        //先往左再往右
        PropertyValuesHolder rotateValuesHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.1f, -40),
                Keyframe.ofFloat(0.2f, 40),
                Keyframe.ofFloat(0.3f, -40),
                Keyframe.ofFloat(0.4f, 40),
                Keyframe.ofFloat(0.5f, -40),
                Keyframe.ofFloat(0.6f, 40),
                Keyframe.ofFloat(0.7f, -40),
                Keyframe.ofFloat(0.8f, 40),
                Keyframe.ofFloat(0.9f, -40),
                Keyframe.ofFloat(1.0f, 0f)
        );

//        ValueAnimator valueAnimator = ValueAnimator.ofPropertyValuesHolder(rotateValuesHolder);
//        valueAnimator.setDuration(2000);
//        valueAnimator.setTarget(mTargetView);
//        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
//        valueAnimator.start();

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mTargetView, rotateValuesHolder);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }

    public void viewAnimation(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
        mTargetView.startAnimation(animation);
    }
}

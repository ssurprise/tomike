package com.skx.tomike.activity;

import android.os.Bundle;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.skx.tomike.R;

public class VectorDrawableActivity extends AppCompatActivity {

    private ImageView mImageView;
    private ImageView mAnimatedImageView;
    private TextView mTextView;

//    static {
//        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_drawable);
        mImageView = (ImageView) findViewById(R.id.vectorDrawableTest_ImageView);
        mAnimatedImageView = (ImageView) findViewById(R.id.vectorDrawableTest_animatedImageView);
        mTextView = (TextView) findViewById(R.id.vectorDrawableTest_TextView);

        VectorDrawableCompat vectorDrawableCompat = VectorDrawableCompat.create(getResources(), R.drawable.ic_christmas_candy, null);
//        mImageView.setImageDrawable(vectorDrawableCompat);
        mTextView.setBackground(vectorDrawableCompat);


        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(this, R.drawable.animated_vector);
        mAnimatedImageView.setImageDrawable(animatedVectorDrawableCompat);
        if (animatedVectorDrawableCompat != null) {
            animatedVectorDrawableCompat.start();
        }

    }
}

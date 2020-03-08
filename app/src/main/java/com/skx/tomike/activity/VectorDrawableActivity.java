package com.skx.tomike.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.skx.tomike.R;

public class VectorDrawableActivity extends AppCompatActivity {

    private ImageView mImageView;
    private ImageView mAnimatedImageView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_drawable);
        mImageView = findViewById(R.id.vectorDrawableTest_ImageView);
        mAnimatedImageView = findViewById(R.id.vectorDrawableTest_animatedImageView);
        mTextView = findViewById(R.id.vectorDrawableTest_TextView);

        VectorDrawableCompat vectorDrawableCompat = VectorDrawableCompat.create(getResources(), R.drawable.ic_christmas_candy, null);
        ViewCompat.setBackground(mTextView, vectorDrawableCompat);


        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(this, R.drawable.animated_vector);
        mAnimatedImageView.setImageDrawable(animatedVectorDrawableCompat);
        if (animatedVectorDrawableCompat != null) {
            animatedVectorDrawableCompat.start();
        }

    }
}

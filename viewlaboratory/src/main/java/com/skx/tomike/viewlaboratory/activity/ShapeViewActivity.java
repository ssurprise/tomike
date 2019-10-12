package com.skx.tomike.viewlaboratory.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.skx.tomike.viewlaboratory.R;
import com.skx.tomike.viewlaboratory.view.ScoreView;

import java.util.Random;

public class ShapeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_view);

        ScoreView scoreView = findViewById(R.id.customView_scoreView);
        refreshScore(scoreView);
    }

    private void refreshScore(final ScoreView scoreView) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();
                int i = r.nextInt(20);
                scoreView.setIndicatorPos(i);

                refreshScore(scoreView);
            }
        }, 2000);
    }
}

package com.skx.tomike.activity.widget;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.skx.tomike.R;
import com.skx.tomike.customview.LuCommentScoreWidget;
import com.skx.tomike.customview.ScoreView;

import java.util.Random;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        LinearLayout customView_1 = (LinearLayout) findViewById(R.id.customView_1);
        customView_1.setMinimumHeight(300);

        ScoreView scoreView = (ScoreView) findViewById(R.id.customView_scoreView);
        final LuCommentScoreWidget totalScoreView = (LuCommentScoreWidget) findViewById(R.id.customView_totalScoreView);

        totalScoreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimator(totalScoreView);
            }
        });
        refreshScore(scoreView);


    }

    private void startAnimator(LuCommentScoreWidget totalScoreView) {
        totalScoreView.changeViewShape(500);
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
        },2000);
    }
}

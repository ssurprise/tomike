package com.skx.tomike.activity.widget;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.skx.tomike.R;

public class CollapsingToolbarLayoutActivity extends AppCompatActivity {

    AppBarLayout collapsing_app_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        collapsing_app_bar = (AppBarLayout) findViewById(R.id.collapsing_app_bar);
        collapsing_app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e("appBarLayout.getTop()", collapsing_app_bar.getTop() + "");
                Log.e("appBarLayout.getX()", collapsing_app_bar.getX() + "");
                Log.e("appBarLayout.getMeasuredHeight()", collapsing_app_bar.getMeasuredHeight() + "");
                Log.e("appBarLayout.getHeight()", collapsing_app_bar.getHeight() + "");

            }
        });
    }
}

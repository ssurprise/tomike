package com.skx.tomike.animlaboratory.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.skx.tomike.animlaboratory.R;
import com.skx.tomike.animlaboratory.view.TreeView;

/**
 * 贝塞尔动画事例
 */
public class BezierAnimatorActivity extends AppCompatActivity {

    private TreeView mTreeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_animatior);

        mTreeView = findViewById(R.id.bezierAnimator_tree_view);

    }
}

package com.skx.tomike.activity.widget;

import android.os.Bundle;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;
import com.skx.tomike.fragment.ItemFragment;
import com.skx.tomike.fragment.dummy.DummyContent;

public class CoordinatorLayoutActivity extends SkxBaseActivity implements ItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_coordinator_layout);
        initializeView();
        refreshView();
        installListener();
    }

    @Override
    public void initializeView() {
        super.initializeView();



    }

    @Override
    public void refreshView() {
        super.refreshView();

    }

    @Override
    public void installListener() {
        super.installListener();

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}

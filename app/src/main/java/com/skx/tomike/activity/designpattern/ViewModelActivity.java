package com.skx.tomike.activity.designpattern;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;
import com.skx.tomike.model.User;
import com.skx.tomike.viewmodel.UserProfileViewModel;

public class ViewModelActivity extends SkxBaseActivity {


    UserProfileViewModel mViewModel;

    private TextView textView2;
    private TextView textView4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_model);

        textView2 = findViewById(R.id.textView2);
        textView4 = findViewById(R.id.textView4);

        mViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        Observer<User> userObserver = new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {

            }
        };
        mViewModel.getUser().observe(this, userObserver);
    }
}

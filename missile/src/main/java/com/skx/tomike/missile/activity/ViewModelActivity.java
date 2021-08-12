package com.skx.tomike.missile.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.skx.tomike.missile.R;
import com.skx.tomike.missile.bean.User;
import com.skx.tomike.missile.viewmodel.UserProfileViewModel;

public class ViewModelActivity extends AppCompatActivity {

    private UserProfileViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_model);

        mViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        Observer<User> userObserver = new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {

            }
        };
        mViewModel.getUser().observe(this, userObserver);
    }
}

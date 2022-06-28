package com.skx.tomike.cannon.ui.activity;

import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_VIEW_MODEL;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.tomike.cannon.R;
import com.skx.tomike.cannon.bean.User;
import com.skx.tomike.cannon.viewmodel.UserProfileViewModel;


@Route(path = ROUTE_PATH_VIEW_MODEL)
public class ViewModelActivity extends AppCompatActivity {

    private UserProfileViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_model);

        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance().create(UserProfileViewModel.class);
        Observer<User> userObserver = new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {

            }
        };
        mViewModel.getUser().observe(this, userObserver);
    }
}

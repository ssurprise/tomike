package com.skx.tomike.tacticallaboratory.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.skx.tomike.tacticallaboratory.R;
import com.skx.tomike.tacticallaboratory.bean.User;
import com.skx.tomike.tacticallaboratory.viewmodel.UserProfileViewModel;

public class ViewModelActivity extends AppCompatActivity {


    private UserProfileViewModel mViewModel;

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

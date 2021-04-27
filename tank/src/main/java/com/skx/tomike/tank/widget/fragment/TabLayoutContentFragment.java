package com.skx.tomike.tank.widget.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.skx.tomike.tank.R;

public class TabLayoutContentFragment extends Fragment {

    private static final String ARG_PARAM1 = "content";
    private String mContent;
    private TextView textView;

    public TabLayoutContentFragment() {
    }

    public static TabLayoutContentFragment newInstance(String param1) {
        TabLayoutContentFragment fragment = new TabLayoutContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContent = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tablayout_content, container, false);
        textView = v.findViewById(R.id.tabLayoutContent_item);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView.setText(mContent);
    }

    public void setContent(String s) {
        textView.setText(s);
    }
}

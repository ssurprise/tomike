package com.skx.tomike.fragment.business;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skx.tomike.R;

public class TabLayoutContentFragment extends Fragment {

    private static final String ARG_PARAM1 = "content";
    private String mContent;
    TextView textView;

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
        textView = (TextView) v.findViewById(R.id.tabLayoutContent_item);
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

package com.skx.tomike.fragment.business;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.skx.tomike.R;
import com.skx.tomikecommonlibrary.imageloader.ImageLoader;

public class HomepageFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private final int[] mainImgRes = {R.drawable.main_clear_sky, R.drawable.main_cloud,
            R.drawable.main_lucky, R.drawable.main_night, R.drawable.main_snow,
            R.drawable.main_dazhongsi_1, R.drawable.main_dazhongsi_2, R.drawable.main_dazhongsi_3};

    private ImageView mIvMainImg1;
    private ImageView mIvMainImg2;
    private ImageView mIvMainImg3;
    private ImageView mIvMainImg4;
    private ImageView mIvMainImg5;
    private ImageView mIvMainImg6;

    private String mParam1;
    private String mParam2;

    private Context mContext;

    public HomepageFragment() {
    }

    public static HomepageFragment newInstance(String param1, String param2) {
        HomepageFragment fragment = new HomepageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        mIvMainImg1 = view.findViewById(R.id.homepage_main_1);
        mIvMainImg2 = view.findViewById(R.id.homepage_main_2);
        mIvMainImg3 = view.findViewById(R.id.homepage_main_3);
        mIvMainImg4 = view.findViewById(R.id.homepage_main_4);
        mIvMainImg5 = view.findViewById(R.id.homepage_main_5);
        mIvMainImg6 = view.findViewById(R.id.homepage_main_6);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageLoader.with(mContext).load(R.drawable.main_clear_sky).into(mIvMainImg1);
        ImageLoader.with(mContext).load(R.drawable.main_cloud).into(mIvMainImg2);
        ImageLoader.with(mContext).load(R.drawable.main_lucky).into(mIvMainImg3);
        ImageLoader.with(mContext).load(R.drawable.main_dazhongsi_1).into(mIvMainImg4);
        ImageLoader.with(mContext).load(R.drawable.main_dazhongsi_2).into(mIvMainImg5);
        ImageLoader.with(mContext).load(R.drawable.main_dazhongsi_3).into(mIvMainImg6);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }
}

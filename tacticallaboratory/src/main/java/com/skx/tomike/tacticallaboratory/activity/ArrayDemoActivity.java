package com.skx.tomike.tacticallaboratory.activity;

import android.util.Log;

import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.tacticallaboratory.R;

import java.util.Arrays;

/**
 * 描述 : 数据结构 - 数组 demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/6/29 10:26 AM
 */
public class ArrayDemoActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("数据结构 - 数组").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data_structure_array;
    }

    @Override
    protected void initView() {

        int[] source = {9, 8, 7, 6, 5, 40, 30, 20, 10, 0};

        StringBuilder stringBuilder = new StringBuilder();
        for (int value : source) {
            stringBuilder.append(value).append(",");
        }
        Log.e(TAG, "source:" + stringBuilder.toString());

        System.arraycopy(source, 5, source, 0, 5);

        StringBuilder stringBuilder2 = new StringBuilder();
        for (int value : source) {
            stringBuilder2.append(value).append(",");
        }
        Log.e(TAG, "arraycopy:" + stringBuilder2.toString());


        int[] copyOf = Arrays.copyOf(source, 5);
        StringBuilder stringBuilder3 = new StringBuilder();
        for (int value : copyOf) {
            stringBuilder3.append(value).append(",");
        }
        Log.e(TAG, "copyOf:" + stringBuilder3.toString());

        int[] copyOfRange = Arrays.copyOfRange(source, 3, 6);
        StringBuilder stringBuilder4 = new StringBuilder();
        for (int value : copyOfRange) {
            stringBuilder4.append(value).append(",");
        }
        Log.e(TAG, "copyOfRange:" + stringBuilder4.toString());

    }

}

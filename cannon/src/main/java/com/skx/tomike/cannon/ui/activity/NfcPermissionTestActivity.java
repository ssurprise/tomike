package com.skx.tomike.cannon.ui.activity;

import android.nfc.NfcAdapter;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.tomike.cannon.R;

import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_NFC_PERMISSION;

/**
 * Created by shiguotao on 2017/12/11.
 */
@Route(path = ROUTE_PATH_NFC_PERMISSION)
public class NfcPermissionTestActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_nfc_premission_test;
    }

    @Override
    protected void initView() {

    }


    public void onStartNfcPermissionTest(View view) {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Log.e(TAG, "没有NFC功能");

        } else if (nfcAdapter.isEnabled()) {
            Log.e(TAG, "有NFC功能，可以使用");

        } else {
            Log.e(TAG, "有NFC功能，但是不能使用");
        }
    }
}

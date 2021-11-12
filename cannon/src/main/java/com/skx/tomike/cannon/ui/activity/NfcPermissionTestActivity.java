package com.skx.tomike.cannon.ui.activity;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.tomike.cannon.R;

import static com.skx.tomike.cannon.RouteConstantsKt.ROUTER_GROUP;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_NFC_PERMISSION;

/**
 * Created by shiguotao on 2017/12/11.
 */
@Route(path = ROUTE_PATH_NFC_PERMISSION, group = ROUTER_GROUP)
public class NfcPermissionTestActivity extends AppCompatActivity {

    private final String TAG = "NfcPermissionTest";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_premission_test);
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

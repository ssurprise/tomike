package com.skx.tomike.activity.function;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;

/**
 * Created by shiguotao on 2017/12/11.
 */
public class NfcPermissionTestActivity extends SkxBaseActivity {

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

package com.skx.tomike.cannonlaboratory.activity;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.skx.tomike.cannonlaboratory.R;

/**
 * Created by shiguotao on 2017/12/11.
 */
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

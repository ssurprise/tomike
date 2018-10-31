package com.skx.tomike.activity.function;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.skx.tomike.R;

/**
 * NFC 测试类
 *
 * @author shiguotao
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class NfcGroupActivity extends AppCompatActivity {
    PendingIntent pi;
    NfcAdapter mNfcAdapter;
    private String mPackageName = "com.skx.tomike";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_group);


    }

    public void onNfcPermissionTest(View view) {
        Intent intent = new Intent(this, NfcPermissionTestActivity.class);
        startActivity(intent);
    }
}

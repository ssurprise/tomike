package com.skx.tomike.cannonlaboratory.ui.activity;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.skx.tomike.cannonlaboratory.R;


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

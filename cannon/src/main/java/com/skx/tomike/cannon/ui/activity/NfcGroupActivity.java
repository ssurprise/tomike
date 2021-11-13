package com.skx.tomike.cannon.ui.activity;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.tomike.cannon.R;

import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_NFC_GROUP;


/**
 * NFC 测试类
 *
 * @author shiguotao
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
@Route(path = ROUTE_PATH_NFC_GROUP)
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

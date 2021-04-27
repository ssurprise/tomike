package com.skx.tomike.cannon.ui.activity;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.skx.tomike.cannon.R;

/**
 * NFC 测试类
 *
 * @author shiguotao
 */
public class NfcWriteTextActivity extends NfcBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_group);

    }

    //    获取数据
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 当前app正在前端界面运行，这个时候有intent发送过来，那么系统就会调用onNewIntent回调方法，将intent传送过来
        // 我们只需要在这里检验这个intent是否是NFC相关的intent，如果是，就调用处理方法
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String[] techList = tag.getTechList();

            Log.e("tag.getId", tag.getId() + "");

            boolean haveMifareUltralight = false;
            for (String tecj : techList) {
                if (tecj.indexOf("MifareUltralight") > 0) {
                    haveMifareUltralight = true;
                    break;
                }
            }
            if (!haveMifareUltralight) {
                Toast.makeText(this, "不支持MifareUltralight格式", Toast.LENGTH_SHORT);
                return;
            }


        } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {

        } else if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {

        }
    }
}

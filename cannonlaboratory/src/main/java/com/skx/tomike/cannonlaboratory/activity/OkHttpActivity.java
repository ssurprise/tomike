package com.skx.tomike.cannonlaboratory.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomike.cannonlaboratory.bean.WeatherMini;

import java.io.IOException;

import okhttp3.MediaType;

public class OkHttpActivity extends AppCompatActivity {

    private EditText inputBox;
    private TextView wendu_value;
    private TextView ganmao_value;
    private TextView fengli_value;
    private TextView fengxiang_value;
    private String targetCity;
    private WeatherMini weatherMini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        installListener();
    }

    private void initializeView() {
        setContentView(R.layout.activity_okhttp);
        inputBox = findViewById(R.id.okhttp_inputBox);
        wendu_value = findViewById(R.id.okhttp_wendu_value);
        ganmao_value = findViewById(R.id.okhttp_ganmao_value);
        fengli_value = findViewById(R.id.okhttp_fengli_value);
        fengxiang_value = findViewById(R.id.okhttp_fengxiang_value);
    }

    private void installListener() {
        inputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                targetCity = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void refreshView() {
        if (weatherMini != null) {
            wendu_value.setText(weatherMini.getWendu());
            ganmao_value.setText(weatherMini.getGanmao());
        }
    }

    public void onOkHttpClickByGet(View view) {
        wendu_value.setText("");
        ganmao_value.setText("");

    }

    public void onOkHttpClickByPost(View view) throws IOException {
        wendu_value.setText("");
        ganmao_value.setText("");

    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    private String bowlingJson(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }
}

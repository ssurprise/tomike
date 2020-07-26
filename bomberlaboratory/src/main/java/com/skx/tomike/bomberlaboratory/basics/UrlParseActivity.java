package com.skx.tomike.bomberlaboratory.basics;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.skx.tomike.bomberlaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;
import com.skx.tomikecommonlibrary.utils.ToastTool;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 描述 : Url 解析
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/7/27 12:26 AM
 */
public class UrlParseActivity extends SkxBaseActivity<BaseViewModel> {

    private String mTargetUrl = "https://haokan.baidu.com/v?vid=17099850856972684618";
    private TextView mTvResult;

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("url 解析").create();
    }

    @Override
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_url_parse;
    }

    @Override
    protected void initView() {
        EditText mEtTargetUrl = findViewById(R.id.tv_urlParse_targetUrl);
        mEtTargetUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTargetUrl = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        TextView tv_urlParse_startParsing = findViewById(R.id.tv_urlParse_startParsing);
        tv_urlParse_startParsing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlParse();
            }
        });

        mTvResult = findViewById(R.id.tv_urlParse_result);
        mEtTargetUrl.setText(mTargetUrl);
        urlParse();
    }

    private void urlParse() {
        try {
            URL url = new URL(mTargetUrl);
            StringBuilder sb = new StringBuilder();

            // The protocol to use (ftp, http, nntp, ... etc.)
            sb.append("协议为（protocol）：\n").append(url.getProtocol()).append("\n\n")

                    // The authority part of this URL.
                    .append("验证信息（authority）：\n").append(url.getAuthority()).append("\n\n")

                    // The host name to connect to.
                    .append("主机名（host）：\n").append(url.getHost()).append("\n\n")

                    // The protocol port to connect to.
                    .append("端口（port）：\n").append(url.getPort()).append("\n\n")
                    .append("默认端口（DefaultPort）：\n").append(url.getDefaultPort()).append("\n\n")

                    // The specified file name on that host. {@code file} is  defined as {@code path[?query]}
                    .append("文件名及请求参数（file）：\n").append(url.getFile()).append("\n\n")

                    // The path part of this URL.
                    .append("路径（path）：\n").append(url.getPath()).append("\n\n")

                    // The query part of this URL.
                    .append("请求参数（query）：\n").append(url.getQuery()).append("\n\n")
                    .append("定位位置（ref）：\n").append(url.getRef()).append("\n\n");

            mTvResult.setText(sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
            ToastTool.showToast(mActivity, e.getMessage());
            mTvResult.setText("");
        }
    }
}

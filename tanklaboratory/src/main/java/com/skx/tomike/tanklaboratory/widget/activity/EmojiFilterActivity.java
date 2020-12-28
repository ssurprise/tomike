package com.skx.tomike.tanklaboratory.widget.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.EmojiTool;
import com.skx.tomike.tanklaboratory.R;

/**
 * emoji 表情过滤
 */
public class EmojiFilterActivity extends SkxBaseActivity<BaseViewModel> {

    private TextView tv_hasEmoji;
    private TextView tv_textLength_original;
    private TextView tv_textLength_filter;

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("EditText 过滤emoji表情").create();
    }

    @Override
    protected void initParams() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_emoji_filter;
    }

    @Override
    protected void initView() {
        EditText et_textContent = (EditText) findViewById(R.id.emojiFilter_textContent);
        tv_hasEmoji = (TextView) findViewById(R.id.emojiFilter_hasEmoji);
        tv_textLength_original = (TextView) findViewById(R.id.emojiFilter_originalTextLength);
        tv_textLength_filter = (TextView) findViewById(R.id.emojiFilter_filterTextLength);

        et_textContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_hasEmoji.setText(EmojiTool.containsEmoji(s) ? "是" : "否");
                tv_textLength_original.setText(String.valueOf(s.length()));
                tv_textLength_filter.setText(String.valueOf(EmojiTool.filterEmoji(s).length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

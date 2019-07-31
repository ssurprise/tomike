package com.skx.tomike.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomikecommonlibrary.utils.EmojiTool;

/**
 * emoji 表情过滤
 */
public class EmojiFilterActivity extends SkxBaseActivity {

    private EditText et_textContent;
    private TextView tv_hasEmoji;
    private TextView tv_textLength_original;
    private TextView tv_textLength_filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji_filter);

        initializeView();
        installListener();
    }

    @Override
    public void initializeView() {
        super.initializeView();
        et_textContent = (EditText) findViewById(R.id.emojiFilter_textContent);
        tv_hasEmoji = (TextView) findViewById(R.id.emojiFilter_hasEmoji);
        tv_textLength_original = (TextView) findViewById(R.id.emojiFilter_originalTextLength);
        tv_textLength_filter = (TextView) findViewById(R.id.emojiFilter_filterTextLength);
    }

    @Override
    public void installListener() {
        super.installListener();

        et_textContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String content = s.toString();

                tv_hasEmoji.setText(EmojiTool.containsEmoji(content) ? "是" : "否");
                tv_textLength_original.setText(content.length() + "");
//                tv_textLength_filter.setText(EmojiTool.getWordCount("","")+"");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void refreshView() {
        super.refreshView();
    }
}

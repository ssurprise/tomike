package com.skx.tomike.customview.customlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skx.tomike.R;

/**
 * 底部按钮选择器2.0
 */
public class BottomButtonLayout extends LinearLayout {

    public static final int CashPledgeDetail_1 = 1001;
    public static final int CashPledgeDetail_2 = 1002;
    public static final int CashPledgeDetail_3 = 1003;

    private Context mContext;

    public BottomButtonLayout(Context context) {
        this(context, null);
    }

    public BottomButtonLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomButtonLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    /**
     * 初始化底部按钮选择器。目前支持押金详情页底部的操作按钮，如果需要其他的类型需要自己添加支持的类型
     *
     * @param style    按钮组合类型
     * @param names    按钮名称的数组
     * @param listener 点击按钮的监听事件
     */
    public void initView(int style, String[] names, final OptionClickListener listener) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        if (CashPledgeDetail_1 == style) {
            View view = layoutInflater.inflate(R.layout.layout_bottom_button_one_option, this, true);
            TextView oneOptions = (TextView) view.findViewById(R.id.bottomBtn_oneOptions_center);
            if (names != null && names.length >= 1) {
                oneOptions.setText(names[0]);
            }
            oneOptions.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        if (listener instanceof OneOptionClickListener) {
                            ((OneOptionClickListener) listener).clickListener(v);
                        }
                    }
                }
            });
        } else if (CashPledgeDetail_2 == style) {
            View view = layoutInflater.inflate(R.layout.layout_bottom_button_two_options, this, true);
            TextView twoOptions_left = (TextView) view.findViewById(R.id.bottomBtn_twoOptions_left);
            TextView twoOptions_right = (TextView) view.findViewById(R.id.bottomBtn_twoOptions_right);

            if (names != null && names.length >= 2) {
                twoOptions_left.setText(names[0]);
                twoOptions_right.setText(names[1]);
            }
            twoOptions_left.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        if (listener instanceof TwoOptionClickListener) {
                            ((TwoOptionClickListener) listener).leftClickListener(v);
                        }
                    }
                }
            });
            twoOptions_right.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        if (listener instanceof TwoOptionClickListener) {
                            ((TwoOptionClickListener) listener).rightClickListener(v);
                        }
                    }
                }
            });
        } else if (CashPledgeDetail_3 == style) {
            View view = layoutInflater.inflate(R.layout.layout_bottom_button_three_options, this, true);
            TextView threeOptions_left = (TextView) view.findViewById(R.id.bottomBtn_threeOptions_left);
            TextView threeOptions_center = (TextView) view.findViewById(R.id.bottomBtn_threeOptions_center);
            TextView threeOptions_right = (TextView) view.findViewById(R.id.bottomBtn_threeOptions_right);

            if (names != null && names.length >= 3) {
                threeOptions_left.setText(names[0]);
                threeOptions_center.setText(names[1]);
                threeOptions_right.setText(names[2]);
            }

            threeOptions_left.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        if (listener instanceof ThreeOptionClickListener) {
                            ((ThreeOptionClickListener) listener).leftClickListener(v);
                        }
                    }
                }
            });
            threeOptions_center.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        if (listener instanceof ThreeOptionClickListener) {
                            ((ThreeOptionClickListener) listener).centerClickListener(v);
                        }
                    }
                }
            });
            threeOptions_right.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        if (listener instanceof ThreeOptionClickListener) {
                            ((ThreeOptionClickListener) listener).rightClickListener(v);
                        }
                    }
                }
            });
        }
    }

    public interface OptionClickListener {
    }

    public interface OneOptionClickListener extends OptionClickListener {
        void clickListener(View v);
    }

    /**
     * 两种选项的点击监听
     */
    public interface TwoOptionClickListener extends OptionClickListener {
        void leftClickListener(View v);

        void rightClickListener(View v);
    }

    public interface ThreeOptionClickListener extends OptionClickListener {
        void leftClickListener(View v);

        void centerClickListener(View v);

        void rightClickListener(View v);
    }
}

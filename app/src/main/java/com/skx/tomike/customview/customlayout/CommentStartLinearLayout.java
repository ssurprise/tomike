package com.skx.tomike.customview.customlayout;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.skx.tomike.R;

public class CommentStartLinearLayout extends LinearLayout implements View.OnClickListener {

    private ImageView start1;
    private ImageView start2;
    private ImageView start3;
    private ImageView start4;
    private ImageView start5;
    private int score = -1;

    private int startIsLight = 0;
    private final int COMMITSTARTCHANGE = 1;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == COMMITSTARTCHANGE) {
                if (startIsLight <= score) {
                    startIsLight++;
                    if (startIsLight == 1) {
                        start1.setImageResource(R.drawable.star_30dp_full);
                        start2.setImageResource(R.drawable.star_30dp);
                        start3.setImageResource(R.drawable.star_30dp);
                        start4.setImageResource(R.drawable.star_30dp);
                        start5.setImageResource(R.drawable.star_30dp);
                    } else if (startIsLight == 2) {
                        start1.setImageResource(R.drawable.star_30dp_full);
                        start2.setImageResource(R.drawable.star_30dp_full);
                        start3.setImageResource(R.drawable.star_30dp);
                        start4.setImageResource(R.drawable.star_30dp);
                        start5.setImageResource(R.drawable.star_30dp);
                    } else if (startIsLight == 3) {
                        start1.setImageResource(R.drawable.star_30dp_full);
                        start2.setImageResource(R.drawable.star_30dp_full);
                        start3.setImageResource(R.drawable.star_30dp_full);
                        start4.setImageResource(R.drawable.star_30dp);
                        start5.setImageResource(R.drawable.star_30dp);
                    } else if (startIsLight == 4) {
                        start1.setImageResource(R.drawable.star_30dp_full);
                        start2.setImageResource(R.drawable.star_30dp_full);
                        start3.setImageResource(R.drawable.star_30dp_full);
                        start4.setImageResource(R.drawable.star_30dp_full);
                        start5.setImageResource(R.drawable.star_30dp);
                    } else if (startIsLight == 5) {
                        start1.setImageResource(R.drawable.star_30dp_full);
                        start2.setImageResource(R.drawable.star_30dp_full);
                        start3.setImageResource(R.drawable.star_30dp_full);
                        start4.setImageResource(R.drawable.star_30dp_full);
                        start5.setImageResource(R.drawable.star_30dp_full);
                    }

                    if (startIsLight < score) {
                        mHandler.sendEmptyMessageDelayed(COMMITSTARTCHANGE, 5);
                    } else {
                        startIsLight = 0;
                    }
                }
            }
            super.handleMessage(msg);
        }

    };

    public CommentStartLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public CommentStartLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommentStartLinearLayout(Context context) {
        this(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_comment, this, true);
        start1 = (ImageView) view.findViewById(R.id.commentStart_start1);
        start2 = (ImageView) view.findViewById(R.id.commentStart_start2);
        start3 = (ImageView) view.findViewById(R.id.commentStart_start3);
        start4 = (ImageView) view.findViewById(R.id.commentStart_start4);
        start5 = (ImageView) view.findViewById(R.id.commentStart_start5);

        start1.setOnClickListener(this);
        start2.setOnClickListener(this);
        start3.setOnClickListener(this);
        start4.setOnClickListener(this);
        start5.setOnClickListener(this);
    }

    public String getScoreOfCommentLevel() {
        return String.valueOf(score);
    }

    public int getScoreOfComment() {
        return score;
    }

    public void setScoreOfComment(int score) {
        if (0 <= score && score <= 5) {
            this.score = score;
            mHandler.sendEmptyMessageDelayed(COMMITSTARTCHANGE, 0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commentStart_start1:
                score = 1;
                mHandler.sendEmptyMessageDelayed(COMMITSTARTCHANGE, 5);
                break;
            case R.id.commentStart_start2:
                score = 2;
                mHandler.sendEmptyMessageDelayed(COMMITSTARTCHANGE, 5);
                break;
            case R.id.commentStart_start3:
                score = 3;
                mHandler.sendEmptyMessageDelayed(COMMITSTARTCHANGE, 5);
                break;
            case R.id.commentStart_start4:
                score = 4;
                mHandler.sendEmptyMessageDelayed(COMMITSTARTCHANGE, 5);
                break;
            case R.id.commentStart_start5:
                score = 5;
                mHandler.sendEmptyMessageDelayed(COMMITSTARTCHANGE, 5);
                break;
            default:
                break;
        }
    }
}

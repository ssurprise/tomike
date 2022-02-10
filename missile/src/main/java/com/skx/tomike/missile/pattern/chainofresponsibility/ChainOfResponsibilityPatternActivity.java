package com.skx.tomike.missile.pattern.chainofresponsibility;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.missile.R;

import java.util.Random;

import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_CHAINOFRESPONSIBILITY;

/**
 * 责任链设计模式
 *
 * @author shiguotao
 */
@Route(path = ROUTE_PATH_CHAINOFRESPONSIBILITY)
public class ChainOfResponsibilityPatternActivity extends SkxBaseActivity<BaseViewModel> implements View.OnClickListener {

    private CheckBox mCbComplaintsReasonHosted;
    private CheckBox mCbComplaintsReasonActor;
    private CheckBox mCbComplaintsReasonNegativeAttitude;
    private CheckBox mCbComplaintsReasonVerbalAbuse;

    private TextView mTvSubmitResult;

    private LinearLayout mRlLoading;
    private TextView mTvLoadingText;

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("责任链模式").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_pattern_chain_of_responsibility;
    }

    @Override
    protected void initView() {
        mCbComplaintsReasonHosted = findViewById(R.id.cb_chainOfResponsibility_complaintsReason_hosted);
        mCbComplaintsReasonActor = findViewById(R.id.cb_chainOfResponsibility_complaintsReason_actor);
        mCbComplaintsReasonNegativeAttitude = findViewById(R.id.cb_chainOfResponsibility_complaintsReason_negativeAttitude);
        mCbComplaintsReasonVerbalAbuse = findViewById(R.id.cb_chainOfResponsibility_complaintsReason_verbalAbuse);

        mTvSubmitResult = findViewById(R.id.tv_chainOfResponsibility_submitResult);

        mRlLoading = findViewById(R.id.rl_chainOfResponsibility_loading);
        mTvLoadingText = findViewById(R.id.rl_chainOfResponsibility_loadingText);

        findViewById(R.id.btn_chainOfResponsibility_submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_chainOfResponsibility_submit) {
            if (!mCbComplaintsReasonHosted.isChecked() && !mCbComplaintsReasonActor.isChecked()
                    && !mCbComplaintsReasonNegativeAttitude.isChecked() && !mCbComplaintsReasonVerbalAbuse.isChecked()) {
                Toast.makeText(ChainOfResponsibilityPatternActivity.this, "其请选择举报内容", Toast.LENGTH_SHORT).show();
                return;
            }
            complaintsReport();
        }
    }

    /**
     * 举报投诉
     */
    private void complaintsReport() {
        ChainNodeHandler collectDataHandler = new CollectDataNodeHandler();
        ChainNodeHandler analyzeDataHandler = new AnalyzeDataNodeHandler();
        ChainNodeHandler receiveDataReturnHandler = new ReceiveDataReturnNodeHandler();

        collectDataHandler.setNextHandler(analyzeDataHandler);
        analyzeDataHandler.setNextHandler(receiveDataReturnHandler);

        collectDataHandler.doHandle();
    }


    public class CollectDataNodeHandler extends ChainNodeHandler {

        @SuppressLint("StaticFieldLeak")
        @Override
        public void doHandle() {
            new AsyncTask<String, Integer, String>() {

                @Override
                protected String doInBackground(String... strings) {
                    Random random = new Random();
                    int time = random.nextInt(4);
                    SystemClock.sleep(time < 2 ? 2000 : time * 1000);
                    int i = random.nextInt(10);

                    if (i < 8) {
                        return "200";
                    } else {
                        return "4000";
                    }
                }


                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    mRlLoading.setVisibility(View.VISIBLE);
                    mTvLoadingText.setText("收集评审数据...");
                }

                @Override
                protected void onPostExecute(String string) {
                    super.onPostExecute(string);
                    if ("200".equalsIgnoreCase(string)) {
                        if (getNextHandler() != null) {
                            getNextHandler().doHandle();
                        }
                    } else {
                        mRlLoading.setVisibility(View.GONE);
                        mTvSubmitResult.setText("收集评审数据失败");
                        mTvSubmitResult.setTextColor(Color.parseColor("#ff4081"));
                    }
                }
            }.execute();
        }
    }


    public class AnalyzeDataNodeHandler extends ChainNodeHandler {

        @SuppressLint("StaticFieldLeak")
        @Override
        public void doHandle() {
            new AsyncTask<String, Integer, String>() {

                @Override
                protected String doInBackground(String... strings) {
                    Random random = new Random();

                    int time = random.nextInt(5);
                    SystemClock.sleep(time < 2 ? 2000 : time * 1000);
                    int i = random.nextInt(10);

                    if (i < 7) {
                        return "200";
                    } else {
                        return "4000";
                    }
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    mRlLoading.setVisibility(View.VISIBLE);
                    mTvLoadingText.setText("分析数据...");
                }

                @Override
                protected void onPostExecute(String string) {
                    super.onPostExecute(string);
                    if ("200".equalsIgnoreCase(string)) {

                        if (getNextHandler() != null) {
                            getNextHandler().doHandle();
                        }
                    } else {
                        mRlLoading.setVisibility(View.GONE);
                        mTvSubmitResult.setText("分析数据失败");
                        mTvSubmitResult.setTextColor(Color.parseColor("#ff4081"));
                    }
                }
            }.execute();
        }
    }

    public class ReceiveDataReturnNodeHandler extends ChainNodeHandler {

        @SuppressLint("StaticFieldLeak")
        @Override
        public void doHandle() {
            new AsyncTask<String, Integer, String>() {

                @Override
                protected String doInBackground(String... strings) {
                    Random random = new Random();

                    int time = random.nextInt(5);
                    SystemClock.sleep(time < 2 ? 2000 : time * 1000);
                    int i = random.nextInt(10);

                    if (i < 7) {
                        return "200";
                    } else {
                        return "4000";
                    }
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    mRlLoading.setVisibility(View.VISIBLE);
                    mTvLoadingText.setText("接收数据返回...");
                }

                @Override
                protected void onPostExecute(String string) {
                    super.onPostExecute(string);
                    if ("200".equalsIgnoreCase(string)) {
                        mRlLoading.setVisibility(View.GONE);
                        mTvSubmitResult.setText("举报成功");
                        mTvSubmitResult.setTextColor(Color.parseColor("#212121"));

                        if (getNextHandler() != null) {
                            getNextHandler().doHandle();
                        }
                    } else {
                        mRlLoading.setVisibility(View.GONE);
                        mTvSubmitResult.setTextColor(Color.parseColor("#ff4081"));
                        mTvSubmitResult.setText("接收数据返回失败");
                    }
                }
            }.execute();
        }
    }
    /*
     * 举报投诉
     */
/*
    public void complaintReport() {
        收集评审数据(参数a, new 收集评审数据接口() {

            public void 收集评审数据成功() {

                分析数据(参数a, new 分析数据接口() {

                    public void 分析数据成功() {

                        接收数据返回(参数a, new 接收数据返回接口() {

                            public void 接收数据返回成功() {

                            }

                            public void 接收数据返回失败() {

                            }

                        });

                    }

                    public void 分析数据失败() {

                    }

                });

            }

            public void 收集评审数据失败() {

            }

        });
    }
    */


//    /**
//     * 举报投诉
//     */
//    public void complaintReport() {
//        collectData();
//    }
//
//    /**
//     * 收集评审数据
//     */
//    public void collectData(){
//        收集评审数据(参数a, new 收集评审数据接口() {
//
//            public void 收集评审数据成功() {
//                analyzeData();
//            }
//
//            public void 收集评审数据失败() {
//
//            }
//
//        });
//    }
//
//    /**
//     * 分析数据
//     */
//    public void analyzeData(){
//        分析数据(参数a, new 分析数据接口() {
//
//            public void 分析数据成功() {
//                receiveDataReturn();
//            }
//
//            public void 分析数据失败() {
//
//            }
//
//        });
//    }
//
//    /**
//     * 接收数据返回
//     */
//    private void receiveDataReturn() {
//        接收数据返回(参数a, new 接收数据返回接口() {
//
//            public void 接收数据返回成功() {
//                //TODO 数据返回成功后的操作
//            }
//
//            public void 接收数据返回失败() {
//
//            }
//
//        });
//    }
}

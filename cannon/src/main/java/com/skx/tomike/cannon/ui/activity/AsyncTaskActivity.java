package com.skx.tomike.cannon.ui.activity;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.ToastTool;
import com.skx.tomike.cannon.R;

import java.lang.ref.WeakReference;
import java.util.Locale;

import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_ASYNC_TASK;

/**
 * 描述 : AsyncTask demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/30 4:34 PM
 */
@Route(path = ROUTE_PATH_ASYNC_TASK)
public class AsyncTaskActivity extends SkxBaseActivity<BaseViewModel> implements View.OnClickListener {

    private SeekBar mSeekBarProcess;
    private DownloadFilesTask mDownloadFilesTask;

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("AsyncTask demo").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_async_task;
    }

    @Override
    protected void initView() {
        final TextView mTvProcessText = findViewById(R.id.tv_asyncTask_processText);
        mSeekBarProcess = findViewById(R.id.seekb_asyncTask_process);
        mSeekBarProcess.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTvProcessText.setText(String.format(Locale.getDefault(), "%d%%", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.btn_asyncTask_download).setOnClickListener(this);
        findViewById(R.id.btn_asyncTask_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_asyncTask_download) {
            mDownloadFilesTask = new DownloadFilesTask(this);
            mDownloadFilesTask.execute();

        } else if (id == R.id.btn_handler_sendOrPostRunnable) {
            if (mDownloadFilesTask != null) {
                mDownloadFilesTask.cancel(true);
            }
        }
    }

    public static class DownloadFilesTask extends AsyncTask<Params, Integer, Result> {

        public WeakReference<AsyncTaskActivity> mW;

        public DownloadFilesTask(AsyncTaskActivity asyncTaskActivity) {
            this.mW = new WeakReference<>(asyncTaskActivity);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (mW.get() != null) {
                mW.get().mSeekBarProcess.setProgress(values[0]);
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mW.get() != null) {
                ToastTool.showToast(mW.get().mActivity, "开始下载");
                Log.e("AsyncTaskActivity", "onPreExecute thread: " + Thread.currentThread().getName());
            }
        }

        @Override
        protected Result doInBackground(Params... params) {
            Log.e("AsyncTaskActivity", "doInBackground thread: " + Thread.currentThread().getName());
            int process = 0;
            while (process < 100) {
                process += 1;
                onProgressUpdate(process);
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return new Result();
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
            if (mW.get() != null) {
                Log.e("AsyncTaskActivity", "onPostExecute thread: " + Thread.currentThread().getName());
                ToastTool.showToast(mW.get().mActivity, "下载完成");
            }
        }
    }

    private static class Params {

    }

    private static class Result {

    }
}

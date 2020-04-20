package com.skx.tomike.cannonlaboratory.ui.activity;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;
import com.skx.tomikecommonlibrary.utils.ToastTool;

import java.lang.ref.WeakReference;
import java.util.Locale;

/**
 * 描述 : AsyncTask demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/30 4:34 PM
 */
public class AsyncTaskActivity extends SkxBaseActivity<BaseViewModel> implements View.OnClickListener {

    private SeekBar mSeekBarProcess;
    private DownloadFilesTask mDownloadFilesTask;

    @Override
    protected void initParams() {
        mDownloadFilesTask = new DownloadFilesTask(this);
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("AsyncTask demo").create();
    }

    @Override
    protected int getLayoutId() {
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
            if (mDownloadFilesTask != null) {
                mDownloadFilesTask.execute();
            }

        } else if (id == R.id.btn_handler_sendOrPostRunnable) {
//            if (mDownloadFilesTask != null) {
//                mDownloadFilesTask.cancel();
//            }
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
            }
            Log.e("AsyncTaskActivity", "onPreExecute thread: " + Thread.currentThread().getName());
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
            Log.e("AsyncTaskActivity", "onPostExecute thread: " + Thread.currentThread().getName());
            ToastTool.showToast(mW.get().mActivity, "下载完成");
        }
    }

    public static class Params {

    }

    public static class Result {

    }
}

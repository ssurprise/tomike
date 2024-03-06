package com.skx.tomike.tank.widget.activity;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_RECYCLER_COUNT_DOWN;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.tank.R;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

/**
 * 描述 : RecyclerView item 中有倒计时
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/20 6:27 PM
 */
@Route(path = ROUTE_PATH_RECYCLER_COUNT_DOWN)
public class RecyclerViewCountDownTimerActivity extends SkxBaseActivity<BaseViewModel<?>> {

    private final List<ItemData> mCon = new LinkedList<>();

    @Override
    protected void initParams() {
        for (int i = 0, j = 1; i < j; i++) {
            Random random = new Random();
            Long i1 = 60 * 1000L + random.nextInt(1000 * 60 * 2);// 随机2-4 分钟
            mCon.add(new ItemData(i1));
        }
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("RecyclerView item中倒计时").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_recyclerview_countdown_timer;
    }

    @Override
    protected void initView() {
        RecyclerView rv = findViewById(R.id.rv_rvCountDown_content);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(new PubCountDownTimerAdapter());
    }

    public void privateCountDownTimer(View view) {
    }

    public void publicCountDownTimer(View view) {
    }


    private class PubCountDownTimerAdapter extends RecyclerView.Adapter<CountDownTimerViewHolder> {

        private final OrderCountDownTimer mCountDownTimer;

        public PubCountDownTimerAdapter() {
            long max = 0L;
            for (ItemData i : mCon) {
                max = Math.max(i.totalTime, max);
            }
            mCountDownTimer = new OrderCountDownTimer(max, 1000);
            mCountDownTimer.start();
        }

        @NonNull
        @Override
        public CountDownTimerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CountDownTimerViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.adapter_recycler_view_countdown_timer, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CountDownTimerViewHolder holder, int position, @NonNull List<Object> payloads) {
            if (payloads.isEmpty()) {
                super.onBindViewHolder(holder, position, payloads);
            } else {
                for (Object obj : payloads) {

                }
            }
        }

        @Override
        public void onBindViewHolder(@NonNull CountDownTimerViewHolder holder, int position) {
            ItemData itemData = mCon.get(position);
            holder.bindData(itemData.remainTime, position);
            mCountDownTimer.addListener(holder);
        }

        @Override
        public int getItemCount() {
            return mCon.size();
        }

        @Override
        public void onViewAttachedToWindow(@NonNull CountDownTimerViewHolder holder) {
            bindCountDown(holder, true);
        }

        @Override
        public void onViewDetachedFromWindow(@NonNull CountDownTimerViewHolder holder) {
            bindCountDown(holder, false);
        }

        private void bindCountDown(OnCountDownListener holder, boolean bind) {
            if (mCountDownTimer != null) {
                if (holder.getTotalMillis() <= 0) return;
                if (bind) mCountDownTimer.addListener(holder);
                else mCountDownTimer.removeListener(holder);
            }
        }
    }

//    private class PriCountDownTimerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//        private final SparseArray<CountDownTimer> countDownMap = new SparseArray<>();
//
//
//        @NonNull
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            return new PriCountDownTimerViewHolder(LayoutInflater.from(parent.getContext()).inflate(
//                    R.layout.adapter_recycler_view_countdown_timer, parent, false));
//        }

//        @Override
//        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
//            if (payloads.isEmpty()) {
//                super.onBindViewHolder(holder, position, payloads);
//            } else {
//                for (Object obj : payloads) {
//                    if (obj instanceof AppStateInfo) {
//                        setStateBtn(holder.itemView.context, holder.btnView, it)
//                    }
//                }
//            }
//        }

//        @Override
//        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//            if (holder instanceof PriCountDownTimerViewHolder) {
//                Integer integer = mCon.get(position);
//                ((PriCountDownTimerViewHolder) holder).mTvName.setText("第" + position + "个商品");
//
//                if (integer != null) {
//                    if (integer <= 0) {
//                        ((PriCountDownTimerViewHolder) holder).mTvTimer.setText("剩余" + getCountDownTime(mCon.get(position)));
//
//                    } else {
//                        if (countDownMap.get(holder.hashCode()) == null) {
//                            CountDownTimer start = new CountDownTimer(integer, 1) {
//                                @Override
//                                public void onTick(long millisUntilFinished) {
//                                    ((PriCountDownTimerViewHolder) holder).mTvTimer.setText("剩余" + getCountDownTime(millisUntilFinished));
//                                }
//
//                                @Override
//                                public void onFinish() {
//                                    ((PriCountDownTimerViewHolder) holder).mTvTimer.setText("剩余 00:00:00");
//                                }
//                            }.start();
//                            countDownMap.put(holder.hashCode(), start);
//                        }
//                    }
//                }
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            return mCon.size();
//        }
//
//        private String getCountDownTime(long time) {
//            long h = time / 3600;
//            long m = (time % 3600) / 60;
//            long s = (time % 3600) % 60;
//            return String.format(Locale.getDefault(), "%02d:%02d:%02d", h, m, s);
//        }
//
//        public void cancelTimers() {
//            for (int i = 0, j = countDownMap.size(); i < j; i++) {
//                CountDownTimer countDownTimer = countDownMap.get(countDownMap.keyAt(i));
//                countDownTimer.cancel();
//            }
//            countDownMap.clear();
//        }
//    }

    private static class CountDownTimerViewHolder extends RecyclerView.ViewHolder implements OnCountDownListener {

        public TextView mTvName;
        public TextView mTvTimer;

        private long originCountDownTime;

        public CountDownTimerViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.iv_rvCountDownTimer_name);
            mTvTimer = itemView.findViewById(R.id.iv_rvCountDownTimer_timer);
        }

        @SuppressLint("SetTextI18n")
        public void bindData(long time, int position) {
            originCountDownTime = time;
            mTvName.setText("第" + position + "个商品");
        }

        @Override
        public long getTotalMillis() {
            return originCountDownTime;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long h = millisUntilFinished / 3600;
            long m = (millisUntilFinished % 3600) / 60;
            long s = (millisUntilFinished % 3600) % 60;
            String format = String.format(Locale.getDefault(), "%02d:%02d:%02d", h, m, s);
            mTvTimer.setText(format);
        }

        @Override
        public void onFinish() {
            String format = "00:00:00";
            mTvTimer.setText(format);
        }
    }

    public static class OrderCountDownTimer extends CountDownTimer {
        private final Set<OnCountDownListener> mListeners = new HashSet<>();
        private final long mMillisInFuture;

        public OrderCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            mMillisInFuture = millisInFuture + 50;
        }

        public void addListener(OnCountDownListener listener) {
            mListeners.add(listener);
        }

        public void removeListener(OnCountDownListener listener) {
            mListeners.remove(listener);
        }

        public void clearListener() {
            mListeners.clear();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.e("111111", "onTick= " + (mMillisInFuture - millisUntilFinished));
//            HashSet<OnCountDownListener> source = (HashSet<OnCountDownListener>) mListeners;
//            @SuppressWarnings("unchecked")
//            Set<OnCountDownListener> list = (Set<OnCountDownListener>) source.clone();
//            long eclipsedTime = mMillisInFuture - millisUntilFinished;
//            for (OnCountDownListener listener : list) {
//                listener.onTick(listener.getTotalMillis() - eclipsedTime);
//            }
        }

        @Override
        public void onFinish() {
//            HashSet<OnCountDownListener> source = (HashSet<OnCountDownListener>) mListeners;
//            @SuppressWarnings("unchecked")
//            Set<OnCountDownListener> list = (Set<OnCountDownListener>) source.clone();
//            for (OnCountDownListener listener : list) {
//                listener.onFinish();
//            }
        }
    }

    public interface OnCountDownListener {
        /**
         * @return 该listener的总时间
         */
        long getTotalMillis();

        /**
         * 根据{@link #getTotalMillis()}计算出剩余时间
         *
         * @param millisUntilFinished 不是计时器剩余时间，而是该listener的剩余时间
         */
        void onTick(long millisUntilFinished);

        /**
         * 倒计时器结束，而不是该listener时间耗尽
         */
        void onFinish();
    }

    public class ItemData {
        public Long totalTime = 0L;
        public Long remainTime = 0L;

        public ItemData(Long totalTime) {
            this.totalTime = totalTime;
            this.remainTime = totalTime;
        }
    }
}

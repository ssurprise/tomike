package com.skx.tomike.activity.function;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.javabean.Student;
import com.skx.tomike.javabean.Transcript;
import com.skx.tomike.util.ToastTool;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {

    private LinearLayout rl_rxjava_loading;
    private TextView rl_rxjava_loadingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        initView();
    }

    private void initView() {
        rl_rxjava_loading = findViewById(R.id.rl_rxjava_loading);
        rl_rxjava_loadingText = findViewById(R.id.rl_rxjava_loadingText);
    }

    public void begin(View view) {
        Observable.create(new ObservableOnSubscribe<Student>() {
            @Override
            public void subscribe(ObservableEmitter<Student> emitter) throws Exception {
                rl_rxjava_loadingText.setText("学生信息获取中...");

                Thread.sleep(3000);

                Student student = new Student();
                student.id = 101;
                student.sex = "男";
                student.name = "小黑";
                student.clazz = "3年级2班";

                emitter.onNext(student);
            }
        })
                .observeOn(Schedulers.io())
//                .subscribeOn(AndroidS)
                .flatMap(new Function<Student, ObservableSource<Pair<Student, Transcript>>>() {
            @Override
            public ObservableSource<Pair<Student, Transcript>> apply(Student student) {
                return Observable.zip(Observable.just(student), Observable.create(new ObservableOnSubscribe<Transcript>() {
                    @Override
                    public void subscribe(ObservableEmitter<Transcript> emitter) throws Exception {

                        rl_rxjava_loadingText.setText("成绩单获取中...");

                        Thread.sleep(3000);

                        Transcript transcript = new Transcript();
                        transcript.id = 101;
                        transcript.Chinese = 100;
                        transcript.English = 98;
                        transcript.Mathematics = 99;

                        emitter.onNext(transcript);
                    }
                }), new BiFunction<Student, Transcript, Pair<Student, Transcript>>() {
                    @Override
                    public Pair<Student, Transcript> apply(Student student, Transcript transcript) {
                        return new Pair<>(student, transcript);
                    }
                });
            }
        }).subscribe(new Observer<Pair<Student, Transcript>>() {
            @Override
            public void onSubscribe(Disposable d) {
                rl_rxjava_loading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNext(Pair<Student, Transcript> studentTranscriptPair) {
                String studentTranscript = String.format("%s的语文成绩为：%s",
                        studentTranscriptPair.first.name,
                        studentTranscriptPair.second.Chinese);

                ToastTool.showToast(RxJavaActivity.this, studentTranscript);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                rl_rxjava_loading.setVisibility(View.GONE);
            }
        });
    }
}

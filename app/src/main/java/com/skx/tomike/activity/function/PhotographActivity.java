package com.skx.tomike.activity.function;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shiguotao
 *         <p/>
 *         拍照界面
 */
public class PhotographActivity extends SkxBaseActivity implements View.OnClickListener {
    private SurfaceView surfaceView;
    private Button take_photo_btn;
    private SurfaceHolder holder;
    private Camera camera = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        refreshView();
    }

    @Override
    public void initializeView() {
        super.initializeView();
        setContentView(R.layout.activity_photograph);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        take_photo_btn = (Button) findViewById(R.id.take_photo_btn);
    }

    @Override
    public void refreshView() {
        super.refreshView();
        holder = surfaceView.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.setFixedSize(1080, 1920); //设置Surface分辨率
        holder.setKeepScreenOn(true);// 屏幕常亮
        holder.addCallback(new SurfaceCallback());

        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                camera.autoFocus(autoFocusCallback);
                return false;
            }
        });
    }

    private final class SurfaceCallback implements SurfaceHolder.Callback {

        // 拍照状态变化时调用该方法
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            // 2
            Camera.Parameters parameters = camera.getParameters(); // 获取各项参数
            parameters.setPictureFormat(PixelFormat.JPEG); // 设置图片格式
            parameters.setPreviewSize(width, height); // 设置预览大小
            parameters.setPreviewFrameRate(5);  //设置每秒显示4帧
            parameters.setPictureSize(width, height); // 设置保存的图片尺寸
            parameters.setJpegQuality(80); // 设置照片质量
            // 3
//            camera.setParameters(parameters);
        }

        // 开始拍照时调用该方法
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                // 1
                camera = Camera.open(); // 打开摄像头
                // 4
                camera.setDisplayOrientation(getPreviewDegree(PhotographActivity.this));
                // 5
                camera.setPreviewDisplay(holder); // 设置用于显示拍照影像的SurfaceHolder对象
                // 6
                camera.startPreview(); // 开始预览
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 停止拍照时调用该方法
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (camera != null) {
                camera.release(); // 释放照相机
                camera = null;
            }
        }
    }

    // 提供一个静态方法，用于根据手机方向获得相机预览画面旋转的角度
    public static int getPreviewDegree(Activity activity) {
        // 获得手机的方向
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degree = 0;
        // 根据手机的方向计算相机预览画面应该选择的角度
        switch (rotation) {
            case Surface.ROTATION_0:
                degree = 90;
                break;
            case Surface.ROTATION_90:
                degree = 0;
                break;
            case Surface.ROTATION_180:
                degree = 270;
                break;
            case Surface.ROTATION_270:
                degree = 180;
                break;
        }
        return degree;
    }

    private final class MyPictureCallback implements Camera.PictureCallback {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            try {
                Bundle bundle = new Bundle();
                bundle.putByteArray("bytes", data); //将图片字节数据保存在bundle当中，实现数据交换
                saveToSDCard(data); // 保存图片到sd卡
                camera.startPreview(); // 拍完照后，重新开始预览

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将拍下来的照片存放在SD卡中
     *
     * @param data
     * @throws IOException
     */
    public static void saveToSDCard(byte[] data) throws IOException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 格式化时间
        String filename = format.format(date) + ".jpg";
        File fileFolder = new File(Environment.getExternalStorageDirectory() + "/finger/");
        if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
            fileFolder.mkdir();
        }
        File jpgFile = new File(fileFolder, filename);
        FileOutputStream outputStream = new FileOutputStream(jpgFile); // 文件输出流
        outputStream.write(data); // 写入sd卡中
        outputStream.close(); // 关闭输出流
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_photo_btn:
                camera.takePicture(null, null, new MyPictureCallback());


                Intent intent = new Intent(PhotographActivity.this, PicturePreviewActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        camera.stopPreview();
        camera.release();
    }
    private Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback(){

        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            if (success){
                Log.e("聚焦","成功");
            }else{
                Log.e("聚焦","失败");
            }
        }
    };


//    @Override
//    protected void onResume() {
//        super.onResume();
//        camera.startPreview();
//    }
}

package com.example.weezn.colorid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * PreViewSurface
 *
 * @author: weezn
 * @time: 2016/1/23 10:48
 */
public class PreViewSurface extends CameraSurface implements Camera.PreviewCallback {


    private OnColorlisnter colorlisnter;

    public PreViewSurface(Context context) {
        super(context);
    }

    public PreViewSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);
        camera.setPreviewCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        super.surfaceChanged(holder, format, width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.setPreviewCallback(null);
        super.surfaceDestroyed(holder);
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

        //照片大小
        Camera.Size size=camera.getParameters().getPreviewSize();


        //将图片转换为yuvimage格式
        YuvImage image =new YuvImage(data,ImageFormat.NV21,size.width, size.height,null);

        //新建一个输出流对象
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        if(null != image){
            //将图片转换为jpeg格式
            image.compressToJpeg(new Rect(0,0,size.width,size.height),100,outputStream);
            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Bitmap bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(outputStream
                    .toByteArray()));
            int color = bitmap.getPixel(size.width / 2, size.height / 2);

            Log.i(TAG,"Color:"+color);

            if(null!=colorlisnter){
                colorlisnter.onColor(color);
            }
        }
    }

    /**
     *监听器   监听颜色的变化
     */

    public void setOnColorListener(OnColorlisnter listener){
        this.colorlisnter=listener;

    }

    public interface OnColorlisnter{
        //默认public供其他函数实现
        void onColor(int color);
    }





}

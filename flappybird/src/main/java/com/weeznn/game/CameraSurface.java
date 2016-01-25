package com.weeznn.game;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * CameraSurface
 *
 * @author: weezn
 * @time: 2016/1/25 14:11
 */
public class CameraSurface extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    public final static String TAG="CameraSurface";
    protected Camera camera;
    private  boolean ispreview;//判断是否为预览状态

    private SurfaceHolder holder;


    public CameraSurface(Context context) {
        super(context);
        init();

    }

    public CameraSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    /**
     *初始化
     */

    private void init(){
        holder=getHolder();
        holder.addCallback(this);

        ispreview=false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        camera=Camera.open();//打开camera

        if(null!=camera){
            try {
                camera.setPreviewDisplay(holder);//设置预览见面到当前surface中
            } catch (IOException e) {
                e.printStackTrace();
                //程序出错，将camera释放
                camera.release();
                camera=null;
            }
        }

        Camera.Parameters parameters=camera.getParameters();
        parameters.setPreviewSize(getWidth(),getHeight());//设置camera为预览尺寸
        camera.setParameters(parameters);


        camera.startPreview();//开始预览
        ispreview=true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        if(null!=camera){
            if(ispreview){
                camera.stopPreview();//停止预览
            }
            camera.release();//释放照相机
            camera=null;
        }




    }

    @Override
    public void run() {

    }



}

package com.example.weezn.colorid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * CricleCrossView
 *
 * @author: weezn
 * @time: 2016/1/23 8:19
 */
public class CricleCrossView extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    private Canvas canvas;
    private Paint paint;
    private SurfaceHolder holder;

    private int color=Color.RED;


    private static final String TAG= "CricleCrossView";





    public CricleCrossView(Context context) {
        super(context);
        init();
    }

    public CricleCrossView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    /**
     * 初始化
     */
    public void init(){
        holder=getHolder();
        holder.addCallback(this);

        //使多个surface共存
        holder.setFormat(PixelFormat.TRANSLUCENT);
        setKeepScreenOn(true);
        setZOrderOnTop(true);

        paint=new Paint();
        paint.setAntiAlias(true);//抗锯齿，光滑
        color=Color.RED;

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        refresh();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    @Override
    public void run() {

    }





    /**
     *绘图
     */

    private void myDraw(Canvas canvas){
        //设置背景
//        paint.setColor(Color.WHITE);
//        canvas.drawRect(0,0,getWidth(),getHeight(),paint);

        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);//设置坐标为中心
        paint.setStyle(Paint.Style.STROKE);//设置画笔为不填充
        paint.setColor(color);
        paint.setStrokeWidth(10);

        float radius=getHeight()/4;
        canvas.drawCircle(0,0,radius,paint);//画圆

        paint.setStrokeWidth(5);
        canvas.drawLine(0, paint.getStrokeWidth()/2, 0, -radius, paint);//画上线
        canvas.drawLine(-paint.getStrokeWidth()/2,0,radius,0,paint);//画右线


        canvas.restore();

    }

    /**
     * 逻辑
     */
    private  void myLogic(){


    }


    /**
     *刷新
     */
    public   void refresh(){
        canvas=holder.lockCanvas();
        if(null!=canvas){
            myDraw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
        myLogic();
    }


    /**
     *改变颜色
     */
    public void setColor(int color){
        this.color=color;
    }


}


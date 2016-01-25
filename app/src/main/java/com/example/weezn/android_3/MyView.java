package com.example.weezn.android_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * MyView
 *
 * @author: weezn
 * @time: 2016/1/22 8:22
 */
public class MyView extends View {


    private Paint paint;
    private int x,y;
    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    /**
     * 初始化
     */
    private void init(){
        paint=new Paint();//笔刷

        paint.setColor(Color.RED);//设置颜色
        paint.setAntiAlias(true); //
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

       //DrawTest(canvas);
        //drawPath(canvas);
        //drawBitmap(canvas);
        Drawtouch(canvas);
    }

    /**
     * 绘图函数
     */

//    private void DrawTest(Canvas canvas){
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setStrokeWidth(15);
//
////        //画线
////       // canvas.drawLine(0, 0, getWidth(), getHeight(), paint);
//        canvas.drawLine(0, 0, x, y, paint);
////
////        //画圆
////        canvas.drawCircle(200, 100, 50, paint);
////
////        //画矩形
////        paint.setColor(Color.BLUE);
////        canvas.drawRect(100, 100, 200, 200, paint);
////
////
////        //画圆角矩形
////        paint.setColor(Color.GREEN);
////        //canvas.drawRoundRect(200,200,300,300,paint);
//
//
//    }

    private void Drawtouch(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(15);

        canvas.drawLine(0, 0, x, y, paint);
    }

//    private void drawPath(Canvas canvas){
//        Path path=new Path();
//        path.moveTo(200, 500);
//        path.lineTo(200, 800);
//        path.lineTo(250, 650);
//
//        paint.setColor(Color.GREEN);
//
//        Path path1=new Path();
//        path1.addCircle(500,500,200,Path.Direction.CW);
//        path1.addCircle(500, 500, 180, Path.Direction.CCW);
//        path1.moveTo(500, 300);
//        path1.lineTo(500, 700);
//        path1.moveTo(300, 500);
//        path1.lineTo(700, 500);
//
//        paint.setColor(Color.YELLOW);
//        paint.setStyle(Paint.Style.STROKE);
//
//        canvas.drawPath(path1, paint);
//    }
//
//    private void drawBitmap(Canvas canvas){
//        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
//
//        canvas.drawBitmap(bitmap,500,500,paint);
//        //设置坐标系为（）
//        canvas.translate(getWidth()/2,getHeight()/2);
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.drawLine(0,getHeight()/2,0,-getHeight()/2,paint);
//        canvas.drawLine(-getWidth()/2,0,getWidth()/2,0,paint);
//
//        //将旋转进行限制
//        canvas.save();
//        //设置旋转角度（画布整体（坐标系）旋转）
//        canvas.rotate(60);
//        canvas.drawBitmap(bitmap,100,0,paint);
//        canvas.restore();
//
//        canvas.drawRect(200,200,300,300,paint);
//
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x=(int) event.getX();
        y=(int) event.getY();

        //每次绘图都必需重新绘制View
        invalidate();
        return super.onTouchEvent(event);
    }
}




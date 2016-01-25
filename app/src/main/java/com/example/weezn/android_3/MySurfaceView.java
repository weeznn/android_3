package com.example.weezn.android_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * MySurfaceView
 *
 * @author: weezn
 * @time: 2016/1/22 10:17
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback , Runnable{

    public final static String TAG="MySurfaceView";


    private  SurfaceHolder holder;

    private  Thread thread;
    private  boolean flag;

    private Canvas canvas;//画布
    private Paint paint;//画笔

    private  float x,y;
    private  float speedx,speedy;
    private  int radius;
    private int color;



    private Vector loca;  //位置
    private Vector speed; //速度
    private  Vector acc;  //加速度

    private float rectX,rectY;
    private float rectWidth,rectHeight;

    private float rect1X,rect1Y;
    private float rect1Width,rect1Height;

    private boolean iscoll;

    public MySurfaceView(Context context) {
        super(context);
        init();
        initGame();
    }


    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initGame();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreated");

        flag=true;
        thread=new Thread(this);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i(TAG, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i(TAG,"surfaceDestroyed");
        flag=false;

    }

    /**
     *初始化
     */

    private void init(){
        holder=getHolder();
        holder.addCallback(this);

        paint=new Paint();
        paint.setAntiAlias(true);//抗锯齿，光滑
        paint.setColor(Color.RED);

    }

    private  void initGame(){
        x=getWidth()/2;
        y=getHeight()/2;
        speedx=10;
        speedy=20;


        loca =new Vector(100,100);
        speed= new Vector(10,20);
        acc= new Vector(1.0f,2.0f);

        rectX=getWidth()/2-100;
        rectY=getHeight()/2-100;
        rectWidth=100;
        rectHeight=100;

        rect1X=0;
        rect1Y=0;
        rect1Height=20;
        rect1Width=30;


    }
    /**
     * 画图
     */

    private  void myDraw(Canvas canvas){

        paint.setColor(Color.BLACK);
        canvas.drawRect(rectX, rectY, rectX + rectWidth, rectY + rectHeight, paint);

        paint.setColor(Color.BLUE);
        canvas.drawRect(rect1X, rect1Y, rect1X + rect1Width, rect1Y + rect1Height, paint);

        paint.setColor(Color.BLACK);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        paint.setColor(Color.RED);

        canvas.drawCircle(x, y, 100, paint);


        paint.setColor(Color.YELLOW);
        canvas.drawCircle(loca.x,loca.y,radius,paint);

        if(iscoll){
            paint.setColor(Color.GREEN);
            canvas.drawCircle(100,100,20,paint);
        }




    }

    /**
     *逻辑
     */

    private void logic(Canvas canvas){

        x=rect1X;
        x=rect1Y;
        //简单运动的小球的坐标
        x+=speedx;
        y+=speedy;
        if(y>getHeight()||y<0){
            speedy=-speedy;
        }
        if(x>getWidth()||x<0){
            speedx=-speedx;
        }

        //向量移动
        speed.limit(50);
        speed.add(acc);
        loca.add(speed);

        if(loca.y>getHeight()||loca.y<0){
            speed.y=-speed.y;
            acc.y=-acc.y;
        }
        if(loca.x>getWidth()||loca.x<0){
            speed.x=-speed.x;
            acc.x=-acc.x;
        }

        //碰撞检测

        //矩形与矩形
       // iscoll=rectAndrect(rectX, rectY, rectHeight, rectWidth, rect1X, rect1Y, rect1Height, rect1Width);


        //圆与圆
       // iscoll=cirlAndcirl(x,y,radius,loca.x,loca.y,loca.radius);

        //圆与矩形
        iscoll=cirlAndrect(rectX,rectY,rectWidth,rectHeight,loca.x,loca.y,loca.radius);

    }

    /**
     *矩形与矩形 碰撞检测
     */

    private boolean rectAndrect(float rectX,float rectY,float rectHeight,float rectWidth,float rect1X,
                                float rect1Y,float rect1Height,float rect1Width){

        if(rect1X+rect1Width < rectX){
            return false;
        }else if(rect1X-rectWidth>rectX){
            return false;
        }else if(rect1Y+rect1Height<rectY){
            return false;
        }else if (rect1Y>rectY+rectHeight){
            return false;
        }
        return true;
    }

    /**
     * 圆与矩形  碰撞检测
     * @param cirl1X
     * @param cirl1Y
     * @param cirl1R
     * @param cirl2X
     * @param cirl2Y
     * @param cirl2R
     * @return
     */
    private boolean cirlAndcirl(float cirl1X,float cirl1Y,float cirl1R,float cirl2X,float cirl2Y,float cirl2R){
        double dis=(float)(Math.pow((cirl2X-cirl1X),2)+Math.pow((cirl2Y-cirl1Y),2));
        if(dis>Math.pow((cirl1R + cirl2R),2)){
            return false;
        }else {
            return true;
        }
    }

    private boolean cirlAndrect(float rectX,float rectY,float rectHeight,float rectWidth,
                                float cirlX,float cirlY,float cirlR){
        if(cirlX+cirlR<rectX){
            return true;
        }else if(cirlX-cirlR>rectX+rectWidth){
            return true;
        }else if(cirlY+cirlR<rectY){
            return true;
        }else if(cirlY-cirlR>rectY+rectHeight){
            return true;
        }else if(Math.pow(cirlX-rectX,2)+Math.pow(cirlY-rectY,2)>cirlR*cirlR&&cirlX<rectX&&cirlY<rectY){
            return true;
        }else if(Math.pow(cirlX-rectX-rectWidth,2)+Math.pow(cirlY-rectY,2)>cirlR*cirlR&&cirlX>rectX+rectWidth
                &&cirlY<rectY){
            return true;
        }else if(Math.pow(cirlX-rectX,2)+Math.pow(cirlY-rectY-rectHeight,2)>cirlR*cirlR&&cirlX<rectX
                &&cirlY>rectY+rectHeight){
            return true;
        }else if(Math.pow(cirlX-rectX-rectWidth,2)+Math.pow(cirlY-rectY-rectHeight,2)>cirlR*cirlR
                &&cirlX>rectX+rectWidth&&cirlY>rectY+rectHeight){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        color=Color.argb(0,x%255,y%255,100);
        radius=new Random().nextInt(10)+50;




        //引力
        Vector touch=new Vector(x,y);

        acc=Vector.sub(touch,loca);
        acc.normalize();
        acc.mult(10);

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void run() {
        long start=System.currentTimeMillis();

        while(flag){
            canvas=holder.lockCanvas();//加锁
            if(null!=canvas){
                myDraw(canvas);
                holder.unlockCanvasAndPost(canvas);//解锁
            }

            logic(canvas);

            long end=System.currentTimeMillis();

            if(end-start<50){
                try {
                    Thread.sleep(50-(end-start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


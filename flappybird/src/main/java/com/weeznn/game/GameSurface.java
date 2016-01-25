package com.weeznn.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.weezn.flappybird.utils.Constants;
import com.weeznn.game.layer.Background;
import com.weeznn.game.layer.Barrier;
import com.weeznn.game.layer.Player;
import com.weeznn.game.layer.Score;
import com.weeznn.game.layer.Start;

/**
 * GameSurface
 *
 * @author: weezn
 * @time: 2016/1/24 9:29
 */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    public final static String TAG="GameSurface";

    private int gameState;//游戏当前状态

    private SurfaceHolder holder;

    private Paint paint;
    private Canvas canvas;

    private Thread thread;
    private boolean flag;

    //游戏图层
    private Background background;
    private Start start;
    private Player player;
    private Barrier barrier;
    private Score score;

    private int ScoreMax;

    //游戏进程
    private int startTime,endTime;

    public void init(){
        holder=getHolder();
        holder.addCallback(this);
        setKeepScreenOn(true);
        holder.setFormat(PixelFormat.TRANSLUCENT);
        setZOrderOnTop(true);

        paint=new Paint();
        paint.setAntiAlias(true);//抗锯齿，光滑
        paint.setColor(Color.RED);


    }

    public void initGame(){
        gameState= Constants.GAME_START;//设置初始状态为游戏开始状态
        background=new Background(this);
        player=new Player(this);
        start=new Start(this);
        barrier=new Barrier(this);
        score=new Score(this);

        score.setScoreMax(ScoreMax);

        Log.i(TAG,"initGame");
    }


    public GameSurface(Context context) {
        super(context);
        init();

    }

    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public GameSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initGame();


        flag=true;
        thread=new Thread(this);//调用run（）；
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag=false;

    }

    @Override
    public void run() {

        while(flag){

            long start=System.currentTimeMillis();

            canvas=holder.lockCanvas();
            if(null!=canvas){
                myDraw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }

            Logic();

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
    /**
     *绘图
     */
    public void myDraw(Canvas canvas){
//        background.draw(canvas,paint);

        //清屏
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
//        barrier.draw(canvas,paint);

        switch (gameState){
            case Constants.GAME_START:
                start.draw(canvas,paint);
                player.draw(canvas,paint);
                break;
            case Constants.GAME_ING:
                player.draw(canvas, paint);
                barrier.draw(canvas,paint);
                score.draw(canvas,paint);
                break;
            case Constants.GAME_OVER:
                player.draw(canvas,paint);
                score.draw(canvas,paint);
                break;
            default:
                break;
        }

    }

    /**
     *逻辑
     */

    public void Logic(){
        switch (gameState){
            case Constants.GAME_START:
                break;
            case Constants.GAME_ING:
                player.logic();
                barrier.setPlayerX(player.getPlayerX());
                barrier.setPlayerY(player.getPlayerY());
                barrier.setPlayerR(player.getRadius());
                barrier.logic();
                score.logic();
                break;
            case Constants.GAME_OVER:

                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (gameState){
            case Constants.GAME_START:
                start.onTouchEvent(event);
                break;
            case Constants.GAME_ING:
                player.onTouchEvent(event);
                break;
            case Constants.GAME_OVER:

                break;
            default:
                break;
        }
        return super.onTouchEvent(event);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);

    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public float getStartTime() {
        return startTime;
    }

    public float getEndTime() {
        return endTime;
    }

    public void setScoreMax(int scoreMax) {
        ScoreMax = scoreMax;
    }
}

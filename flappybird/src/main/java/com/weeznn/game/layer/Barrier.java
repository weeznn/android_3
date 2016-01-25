package com.weeznn.game.layer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.weezn.flappybird.R;
import com.example.weezn.flappybird.utils.Assist;
import com.example.weezn.flappybird.utils.Constants;
import com.weeznn.game.GameSurface;

import java.util.Random;

/**
 * Barrier
 *
 * @author: weezn
 * @time: 2016/1/24 10:03
 */
public class Barrier extends BaseLayer {

    private float spaceH;//障碍间隙
    private float spaceW;//障碍间隔
    private float barrierW;//障碍的宽
    private float barrierY;//障碍的y坐标


    private float barrier1X;//第一个障碍的坐标x// ;
    private float barrier1H;//第一个障碍的高h；

    private float barrier2X;//第二个障碍的坐标x，y;
    private float barrier2H;//第二个障碍的高h；

    private float speed;//障碍的速度

    private float playerX, playerY, playerR;//palyer的圆心坐标x，y，半径r



    private float getBarrierH() {
        return new Random().nextInt((int) (screenH-spaceH-300));
    }

    public void setPlayerX(float playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(float playerY) {
        this.playerY = playerY;
    }

    public void setPlayerR(float playerR) {
        this.playerR = playerR;
    }


    public Barrier(GameSurface surface) {
        super(surface);
        barrierY = 0;
        barrierW = 130;

        barrier1X = screenW + 200;
        barrier1H = getBarrierH();

        spaceH = screenH / 4;
        spaceW = screenW / 2 - barrierW / 2;

        barrier2X = barrier1X + spaceW + barrierW;
        barrier2H = getBarrierH();

        speed = 15;


    }


    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Assist.getColor(resources, R.color.springGring));

        canvas.drawRect(barrier1X, barrierY, barrier1X + barrierW, barrierY + barrier1H, paint); //第一个障碍上
        canvas.drawRect(barrier1X, barrier1H + spaceH, barrier1X + barrierW, screenH, paint); //第一个障碍下

        canvas.drawRect(barrier2X, barrierY, barrier2X + barrierW, barrierY + barrier2H, paint); //第二个障碍上
        canvas.drawRect(barrier2X, barrier2H + spaceH, barrier2X + barrierW, screenH, paint); //第二个障碍下

    }

    @Override
    public void logic() {
        barrier1X -= speed;
        barrier2X -= speed;

        if (barrier1X + barrierW < 0) {
            barrier1X = screenW;
            barrier1H = getBarrierH();
        }

        if (barrier2X + barrierW < 0) {
            barrier2X = screenW;
            barrier2H = getBarrierH();
        }

        //player与barrier的碰撞检
        boolean isColl1 = circleAndRect(playerX, playerY, playerR, barrier1X, barrierY, barrier1X + barrierW, barrierY + barrier1H);
        boolean isColl2 = circleAndRect(playerX, playerY, playerR, barrier1X, barrier1H + spaceH, barrier1X + barrierW, screenH);
        boolean isColl3 = circleAndRect(playerX, playerY, playerR, barrier2X, barrierY, barrier2X + barrierW, barrierY + barrier2H);
        boolean isColl4 = circleAndRect(playerX, playerY, playerR, barrier2X, barrier2H + spaceH, barrier2X + barrierW, screenH);

        if (isColl1 || isColl2 || isColl3 || isColl4) {
            surface.setGameState(Constants.GAME_OVER);
        }
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onKeyDown(int key, MotionEvent motionEvent) {

    }


    /**
     * 圆与矩形碰撞检测
     * @param circleX
     * @param circleY
     * @param circleR
     * @param rectX
     * @param rectY
     * @param rectW
     * @param rectH
     * @return
     */

    private boolean circleAndRect( float circleX, float circleY, float circleR,float rectX,
                                   float rectY, float rectW, float rectH) {
        if (circleX + circleR < rectX) {
            return false;
        } else if (circleX - circleR > rectX + rectW) {
            return false;
        } else if (circleY + circleR < rectY) {
            return false;
        } else if (circleY - circleR > rectY + rectH){
            return false;
        } else if (Math.pow(rectX - circleX, 2) + Math.pow(rectY - circleY, 2) > circleR *
                circleR && circleX < rectX && circleY < rectX) {
            return false;
        } else if (Math.pow(rectX + rectW - circleX, 2) + Math.pow(rectY - circleY, 2) > circleR *
                circleR && circleX > rectX + rectW && circleY < rectY) {
            return false;
        } else if (Math.pow(rectX - circleX, 2) + Math.pow(rectY + rectH - circleY, 2) > circleR *
                circleR && circleX < rectX && circleY > rectY + rectH) {
            return false;
        } else if (Math.pow(rectX + rectW - circleX, 2) + Math.pow(rectY + rectH - circleY, 2) >
                circleR * circleR && circleX > rectX + rectW && circleY > rectY + rectH) {
            return false;
        }

        return true;
    }

}


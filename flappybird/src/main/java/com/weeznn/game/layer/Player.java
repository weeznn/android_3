package com.weeznn.game.layer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.weezn.flappybird.R;
import com.example.weezn.flappybird.utils.Assist;
import com.example.weezn.flappybird.utils.Constants;
import com.weeznn.game.GameSurface;

/**
 * Player
 *
 * @author: weezn
 * @time: 2016/1/24 10:01
 */
public class Player extends BaseLayer{
    private float playerX, playerY;
    private float radius;
    private float speed;
    private float acc;


    public Player(GameSurface surface) {
        super(surface);

        playerX =screenW/3;
        playerY =screenH/2;
        radius=50;
        speed=10;
        acc=2;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
       paint.setColor(Assist.getColor(resources, R.color.Black));



        switch (surface.getGameState()){
            case Constants.GAME_START:
                canvas.drawCircle(screenW/3, screenH/2,radius,paint);
                break;
            case Constants.GAME_ING:
                canvas.drawCircle(playerX, playerY,radius,paint);
                break;
            case Constants.GAME_OVER:

                canvas.drawCircle(screenW/2, screenH/2,radius,paint);
                break;
            default:
                break;
        }

    }

    @Override
    public void logic() {

        playerY +=speed;
        speed+=acc;

        if(playerY-radius<0||playerY+radius>screenH){//与上/下边界碰撞
            surface.setGameState(Constants.GAME_OVER);
        }



    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        speed=-speed;



    }

    @Override
    public void onKeyDown(int key, MotionEvent motionEvent) {

    }

    public float getPlayerX() {
        return playerX;
    }

    public float getPlayerY() {
        return playerY;
    }

    public float getRadius() {
        return radius;
    }
}

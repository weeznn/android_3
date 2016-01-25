package com.weeznn.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;

import com.example.weezn.flappybird.utils.Constants;
import com.weeznn.game.GameSurface;

/**
 * Start
 *
 * @author: weezn
 * @time: 2016/1/24 10:02
 */
public class Start extends BaseLayer {

    private float x,y;
    private float w,h;
    private float triangleW,TriangleH;

    public Start(GameSurface surface) {
        super(surface);


        w=200;
        h=100;
        x=screenW/2-w/2;
        y=2*screenH/3-h/2;

        triangleW=80;
        TriangleH=50;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        canvas.drawRect(x, y, x + w, y + h, paint);

        paint.setColor(Color.GREEN);
        Path path=new Path();
        path.moveTo(x+w/2-TriangleH/2,y+h/2-triangleW/2);
        path.lineTo(x + w / 2 - TriangleH / 2, y + h / 2 + triangleW / 2);
        path.lineTo(x+w/2+TriangleH/2,y+h/2);
        canvas.drawPath(path,paint);
    }

    @Override
    public void logic() {
    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        int touchX=(int)event.getX();
        int touchY=(int)event.getY();

        //判断是否点击开始
        if(touchX>x&&touchX<x+w&&touchY>y&&touchY<y+h){
            surface.setGameState(Constants.GAME_ING);
        }

    }

    @Override
    public void onKeyDown(int key, MotionEvent motionEvent) {

    }
}

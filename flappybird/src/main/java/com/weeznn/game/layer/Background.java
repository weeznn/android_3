package com.weeznn.game.layer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.weezn.flappybird.R;
import com.example.weezn.flappybird.utils.Assist;
import com.weeznn.game.GameSurface;

/**
 * Background
 *
 * @author: weezn
 * @time: 2016/1/24 10:01
 */
public class Background extends BaseLayer  {


    public Background(GameSurface surface) {
        super(surface);


    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        //画纯色背景
        paint.setColor(Assist.getColor(resources, R.color.Cyan));
        //paint.setColor(Color.GREEN);
        canvas.drawRect(0,0,screenW,screenH,paint);

    }

    @Override
    public void logic() {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {


    }

    @Override
    public void onKeyDown(int key, MotionEvent motionEvent) {

    }
}

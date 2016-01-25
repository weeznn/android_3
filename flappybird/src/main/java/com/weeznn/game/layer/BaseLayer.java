package com.weeznn.game.layer;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.weeznn.game.GameSurface;

/**
 * BaseLayer
 *
 * @author: weezn
 * @time: 2016/1/24 10:12
 */
public abstract class BaseLayer {

    protected GameSurface surface;

    /**
     * 当前suface的大小
     */
    protected int screenW;
    protected int screenH;

    protected Resources resources;

    public BaseLayer(GameSurface surface){

        this.surface=surface;
        this.screenW=surface.getWidth();
        this.screenH=surface.getHeight();
        resources=surface.getResources();
    }

    /**
     * 画布画笔
     * @param canvas
     * @param paint
     */
    public abstract void draw(Canvas canvas,Paint paint);

    /**
     * 逻辑
     */
    public abstract void logic();


    /**
     * 触摸事件
     * @param event
     */

    public abstract void onTouchEvent(MotionEvent event);

    /**
     * 按键点击事件
     * @param key
     * @param motionEvent
     */
    public abstract void onKeyDown(int  key,MotionEvent motionEvent);
}

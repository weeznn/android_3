package com.weeznn.game.layer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.weezn.flappybird.R;
import com.example.weezn.flappybird.utils.Assist;
import com.example.weezn.flappybird.utils.Constants;
import com.weeznn.game.GameSurface;

/**
 * Score
 *
 * @author: weezn
 * @time: 2016/1/24 10:04
 */
public class Score  extends BaseLayer{

    private long startTime,endTime;
    private boolean isStart;
    private float scoreX,scoreY;
    private int score;
    private int scoreMax;


    public Score(GameSurface surface) {
        super(surface);


        isStart=true;

        scoreX=screenW/2;
        scoreY=screenH/3;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Assist.getColor(resources, R.color.Red));
        paint.setTextSize(100);
        canvas.drawText(score+"s",scoreX,scoreY,paint);
        switch (surface.getGameState()){
            case Constants.GAME_START:
                canvas.drawText(scoreMax+"s",scoreX,scoreY,paint);
                break;
            case Constants.GAME_ING:
                canvas.drawText(score+"s",scoreX,scoreY,paint);
                break;
        }


    }

    @Override
    public void logic() {

        if(isStart){
            startTime=System.currentTimeMillis();
            isStart=false;
        }
        endTime=System.currentTimeMillis();

        score=(int)((endTime-startTime)/1000);
        if(score>scoreMax) {
            surface.setScoreMax(score);
        }

    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onKeyDown(int key, MotionEvent motionEvent) {

    }

    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }

//    public int getScoreMax() {
//        return scoreMax;
//    }
}

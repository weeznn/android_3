package com.example.weezn.android_3;

/**
 * Vector
 *
 * @author: weezn
 * @time: 2016/1/22 14:17
 */
public class Vector {

    public float x;
    public float y;
    public float radius=10;

    public Vector(float x,float y) {
        this.x = x;
        this.y=y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    /**
    * 加法
    * */

    public Vector add(Vector vector){
        x+=vector.x;
        y+=vector.y;
        return this;
    }

    public static Vector add(Vector v1,Vector v2){
        return new Vector(v1.x+v2.x,v1.y+v2.y);
    }
    /**
    * 减法
    * */
    public  Vector sub(Vector vector){
        x-=vector.x;
        y-=vector.y;
        return this;
    }

    public static Vector sub(Vector v1,Vector v2){
        return new Vector(v1.x-v2.x,v1.y-v2.y);
    }
    /**
     *乘法
     */
    public Vector mult(float n){
        x*=n;
        y*=n;
        return this;
    }
    /**
     * 除法
     */

    public Vector div(float n){
        if(0!=n){
            x/=n;
            y/=n;
        }
        return this;
    }

    /**
     * 求模
     */
    public float mag(){
        return (float)Math.sqrt(x*x+y*y);
    }

    /**
     *限制大小
     */

    public void limit(float max){
        if(max*max < mag()*mag() ){
            normalize();
            mult(max);
        }
    }


    /**
     *单位化
     */

    public void normalize(){
        div(mag());
    }







}

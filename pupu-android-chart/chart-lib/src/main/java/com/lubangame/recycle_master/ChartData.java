package com.lubangame.recycle_master;

import android.graphics.PointF;

/**
 * Description
 *
 * @author weixuefeng@lubangame.com
 * @version 1.0
 * @copyright (c) 2016 Beijing ShenJiangHuDong Technology Co., Ltd. All rights reserved.
 */

public class ChartData {
    private PointF pointf;
    private String lable;
    private int maxNum;
    public ChartData(PointF pointF,String lable){
        this.pointf = pointF;
        this.lable = lable;
    }
    public ChartData(PointF pointF,String lable,int maxNum){
        this.pointf = pointF;
        this.lable = lable;
        this.maxNum = maxNum;
    }

    public float getX(){
        return pointf.x;
    }

    public float getY(){
        return pointf.y;
    }

    public String getLable(){
        return lable;
    }
    public int getMaxNum(){
        return maxNum;
    }
}

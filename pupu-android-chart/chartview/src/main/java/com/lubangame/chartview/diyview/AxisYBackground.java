package com.lubangame.chartview.diyview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import com.lubangame.chartview.utils.DimenUtils;

/**
 * Description
 *
 * @author weixuefeng@lubangame.com
 * @version 1.0
 * @copyright (c) 2016 Beijing ShenJiangHuDong Technology Co., Ltd. All rights reserved.
 */

public class AxisYBackground extends Chart {

    public AxisYBackground(Context context) {
        this(context,null);
    }

    public AxisYBackground(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AxisYBackground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        XPoint = XDistance;
    }


    @Override
    public void onDraw(Canvas canvas) {
        if (chartData!=null && chartData.backgroundColor != 0){
            setBackgroundColor(chartData.backgroundColor);
        }
        drawLable(canvas);
        drawYLine(canvas);
    }

    private void drawYLine(Canvas canvas) {
        YPoint = getYPoint(YDistance);
        //Y轴
        canvas.drawLine(XPoint,YPoint,XPoint,YDistance/2,axisPaint);
        //Y箭头
        canvas.drawLine(XPoint,YDistance/2,XPoint-arrawLength,YDistance/2+arrawLength,axisPaint);
        //Y轴刻度
        int yScale;
        for (int i = 1; i <= 10; i++) {
            yScale = i * YDistance;
            canvas.drawLine(XPoint,getYPoint(YDistance + yScale),XPoint-arrawLength,getYPoint(YDistance + yScale),axisPaint);
        }
    }

    public void drawLable(Canvas canvas) {
        //y轴标注信息.
        int yScale;
        int scale = maxNum / 10;
        for (int i = 0; i <= 10; i++) {
            yScale = i * YDistance;
            if (xAxisIsMid){
                canvas.drawText((i-5)*scale+"",XPoint- DimenUtils.dp2Px(context,22),getYPoint(YDistance + yScale) + DimenUtils.dp2Px(context,5),yLablePaint);
            }else{
                canvas.drawText(i*scale+"",XPoint- DimenUtils.dp2Px(context,22),getYPoint(YDistance + yScale) + DimenUtils.dp2Px(context,5),yLablePaint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(XDistance,heightMeasureSpec);
    }
}

package com.lubangame.chartview.diyview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lubangame.chartview.R;
import com.lubangame.chartview.utils.DimenUtils;

import java.util.ArrayList;


/**
 * Description
 *
 * @author weixuefeng@lubangame.com
 * @version 1.0
 * @copyright (c) 2016 Beijing ShenJiangHuDong Technology Co., Ltd. All rights reserved.
 */


public abstract class Chart extends View {

    protected String TAG = "LineChart";
    /**
     * 屏幕的宽
     */
    protected int screenWidth;

    /**
     * 屏幕的高
     */
    protected int screenHeight;

    /**
     * X轴间距
     */
    protected int XDistance;

    /**
     * Y轴间距
     */
    protected int YDistance;

    /**
     * 原点X
     */
    protected float XPoint;

    /**
     * 原点Y
     */
    protected float YPoint;

    /**
     * 箭头长度
     */
    protected int arrawLength;

    /**
     * 数据点集合
     */
    protected ArrayList<PointF> pointFs = new ArrayList<>();

    protected ArrayList<Integer> nums;
    /**
     * x轴的刻度数量
     */
    protected int xScaleNums = 20;

    protected ArrayList<String> lables;
    /**
     * x轴是否需要在中间
     */
    protected boolean xAxisIsMid = false;

    protected Context context;

    protected ChartData chartData;

    /**
     * 画线笔
     */
    protected Paint linePaint;

    /**
     * x标签笔
     */
    protected Paint xLablePaint;

    /**
     * y标签笔
     */
    protected Paint yLablePaint;

    /**
     * 轴线笔
     */
    protected Paint axisPaint;

    /**
     * 点笔
     */
    protected Paint pointPaint;

    /**
     * 默认最大值
     */
    protected int maxNum = 100;

    public Chart(Context context) {
        this(context, null);
    }

    public Chart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Chart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        screenWidth = DimenUtils.getScreenWidth(context);
        screenHeight = DimenUtils.getScreenHeight(context);
        XDistance = screenWidth / xScaleNums;
        YDistance = screenHeight / 12;
        XPoint = 0;
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.LineCharView);
            xAxisIsMid = attributes.getBoolean(R.styleable.LineCharView_xAxisIsMid, false);
        }
        if (xAxisIsMid) {
            YPoint = screenHeight / 2;
            this.maxNum = 2 * maxNum;
        } else {
            YPoint = getYPoint(YDistance);
        }
        arrawLength = DimenUtils.dp2Px(context, 5);
        initPaint();
    }

    protected float getYPoint(float ypoint) {
        return screenHeight - ypoint;
    }

    protected void initPaint() {
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xLablePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        yLablePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        axisPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setColor(Color.BLACK);
        axisPaint.setColor(Color.WHITE);
        linePaint.setColor(Color.RED);
        xLablePaint.setColor(Color.WHITE);
        xLablePaint.setTextSize(DimenUtils.dp2Px(getContext(), 10));
        yLablePaint.setColor(Color.WHITE);
        yLablePaint.setTextSize(DimenUtils.dp2Px(getContext(), 10));
        if (chartData != null) {
            if (chartData.xLableSize != 0) {
                xLablePaint.setTextSize(chartData.xLableSize);
            }
            if (chartData.xLableColor != 0) {
                xLablePaint.setColor(chartData.xLableColor);
            }
            if (chartData.yLableSize != 0) {
                yLablePaint.setTextSize(chartData.yLableSize);
            }
            if (chartData.yLableColor != 0) {
                yLablePaint.setColor(chartData.yLableColor);
            }
            if (chartData.isFill){
                linePaint.setStyle(Paint.Style.FILL_AND_STROKE);
            }
            if (chartData.isGradient){
                LinearGradient lg = new LinearGradient(0,0,XPoint,YPoint,chartData.startColor,chartData.endColor, Shader.TileMode.MIRROR);
                linePaint.setShader(lg);
            }
            if (chartData.lineColor != 0){
                linePaint.setColor(chartData.lineColor);
            }
            if (chartData.axisColor != 0){
                axisPaint.setColor(chartData.axisColor);
            }
        }
    }


    public int getXDistance() {
        return XDistance;
    }

    public int getXScaleNums() {
        return xScaleNums;
    }

    public int getXSurplusRange() {
        return XDistance / 2;
    }

    public void setxAxisIsMid(boolean isMid) {
        this.xAxisIsMid = isMid;
    }

    public void setData(ChartData chartData) {
        this.chartData = chartData;
        this.lables = chartData.xLables;
        this.nums = chartData.yLables;
        if (xAxisIsMid) {
            YPoint = screenHeight / 2;
            this.maxNum = 2 * chartData.maxNum;
        } else {
            this.maxNum = chartData.maxNum;
            YPoint = getYPoint(YDistance);
        }
        initPaint();
        invalidate();
    }

}

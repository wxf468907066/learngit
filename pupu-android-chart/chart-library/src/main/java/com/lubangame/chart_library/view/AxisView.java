package com.lubangame.chart_library.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description
 *
 * @author weixuefeng@lubangame.com
 * @version 1.0
 * @copyright (c) 2016 Beijing ShenJiangHuDong Technology Co., Ltd. All rights reserved.
 */

public class AxisView extends View {

    protected int screenWidth;
    protected int screenHeight;
    protected Context context;
    protected Paint axisPaint;
    protected int XPoint;
    protected int YPoint;
    protected int arrowLength;
    protected int xEnd;
    protected int yEnd;
    protected int paddingTop;
    protected int paddingBottom;
    protected int paddingLeft;
    protected int paddingRight;
    protected int scaleNum = 10;
    protected Paint redPaint;
    protected Paint zeroPaint;
    protected Paint bluePaint;
    protected int textSize;
    protected int maxNum = 100;
    protected int yScale;
    protected int height;
    protected String lable = "0-0";
    protected boolean xAxisMid = false;
    protected String TAG = "AxisView";
    public AxisView(Context context) {
        super(context);
    }

    public AxisView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AxisView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.AxisView);
            xAxisMid = typedArray.getBoolean(R.styleable.AxisView_xAxisMid,false);
        }
        this.context = context;
        initData();
        initPaint();
    }

    public void setMaxNum(int maxNum){
        if (xAxisMid){
            this.maxNum = 2 * maxNum;
        }else {
            this.maxNum = maxNum;
        }
        invalidate();
    }

    private void initPaint() {
        axisPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        axisPaint.setColor(Color.WHITE);
        axisPaint.setStrokeWidth(5.5f);
        axisPaint.setTextSize(textSize);
        axisPaint.setTextAlign(Paint.Align.RIGHT);
        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setColor(Color.parseColor("#eb7451"));
        redPaint.setTextSize(textSize);
        redPaint.setTextAlign(Paint.Align.RIGHT);
        zeroPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        zeroPaint.setColor(Color.parseColor("#c4a85f"));
        zeroPaint.setTextSize(textSize);
        zeroPaint.setTextAlign(Paint.Align.RIGHT);
        bluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bluePaint.setColor(Color.parseColor("#56bde8"));
        bluePaint.setTextSize(textSize);
        bluePaint.setTextAlign(Paint.Align.RIGHT);
    }

    private void initData() {
        paddingBottom = DimenUtils.dp2Px(context, 40);
        paddingTop = DimenUtils.dp2Px(context, 40);
        paddingLeft = DimenUtils.dp2Px(context, 40);
        paddingRight = DimenUtils.dp2Px(context, 40);
        textSize = DimenUtils.dp2Px(context, 10);
        screenHeight = DimenUtils.getScreenHeight(context);
        screenWidth = DimenUtils.getScreenWidth(context);
        arrowLength = DimenUtils.dp2Px(context, 5);
        XPoint = paddingLeft;
        if (xAxisMid){
            YPoint = screenHeight / 2;
            maxNum = 2 * maxNum;
        }else {
            YPoint = screenHeight - paddingBottom;
        }
        height = screenHeight - paddingTop - paddingBottom;
        yScale = height / scaleNum;
    }


}

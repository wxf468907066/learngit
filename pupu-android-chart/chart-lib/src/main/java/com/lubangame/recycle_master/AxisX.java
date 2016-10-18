package com.lubangame.recycle_master;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * Description
 *
 * @author weixuefeng@lubangame.com
 * @version 1.0
 * @copyright (c) 2016 Beijing ShenJiangHuDong Technology Co., Ltd. All rights reserved.
 */

public class AxisX extends AxisView {

    public AxisX(Context context) {
        this(context, null);
    }

    public AxisX(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AxisX(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AxisView);
            lable = typedArray.getString(R.styleable.AxisView_xLable);
        }
        xEnd = screenWidth - XPoint / 2;
        yEnd = YPoint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(XPoint, YPoint, xEnd, yEnd, axisPaint);
        drawArrow(canvas);
        drawLable(canvas);
    }

    private void drawLable(Canvas canvas) {
        canvas.drawText(lable, xEnd + arrowLength, yEnd + 3 * arrowLength, axisPaint);
    }

    private void drawArrow(Canvas canvas) {
        canvas.drawLine(xEnd, yEnd, xEnd - arrowLength, yEnd - arrowLength, axisPaint);
        canvas.drawLine(xEnd, yEnd, xEnd - arrowLength, yEnd + arrowLength, axisPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(screenWidth, YPoint + 5 * arrowLength);
    }
}

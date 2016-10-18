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

public class AxisY extends AxisView {

    public AxisY(Context context) {
        this(context, null);
    }

    public AxisY(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AxisY(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        YPoint = screenHeight - paddingBottom;
        xEnd = XPoint;
        yEnd = XPoint / 2;
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AxisView);
            lable = typedArray.getString(R.styleable.AxisView_yLable);
            xAxisMid = typedArray.getBoolean(R.styleable.AxisView_xAxisMid, false);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (xAxisMid) {
            canvas.drawLine(XPoint, YPoint + 3 * arrowLength, xEnd, yEnd, axisPaint);
        } else {
            canvas.drawLine(XPoint, YPoint, xEnd, yEnd, axisPaint);
        }
        drawArrow(canvas);
        drawScale(canvas);
        drawLable(canvas);
    }

    private void drawLable(Canvas canvas) {
        canvas.drawText(lable, XPoint / 2 + 2 * arrowLength, (float) (YPoint - 10.5 * yScale), axisPaint);
    }

    private void drawScale(Canvas canvas) {
        for (int i = 0; i <= scaleNum; i++) {
            int yDistance = YPoint - i * yScale;
            if (xAxisMid) {
                i = i - 5;
            }

            if (i != 0 && !xAxisMid || xAxisMid) {
                canvas.drawLine(XPoint, yDistance, XPoint - arrowLength, yDistance, axisPaint);
            }
            if (i == 0) {
                canvas.drawText(i * maxNum / scaleNum + "", XPoint / 2 + 2 * arrowLength, yDistance + arrowLength, zeroPaint);
            } else if (i > 0) {
                canvas.drawText(i * maxNum / scaleNum + "", XPoint / 2 + 2 * arrowLength, yDistance + arrowLength, redPaint);
            } else {
                canvas.drawText(i * maxNum / scaleNum + "", XPoint / 2 + 2 * arrowLength, yDistance + arrowLength, bluePaint);
            }
            if (xAxisMid) {
                i = i + 5;
            }
        }
    }

    private void drawArrow(Canvas canvas) {
        canvas.drawLine(xEnd, yEnd, xEnd + arrowLength, yEnd + arrowLength, axisPaint);
        canvas.drawLine(xEnd, yEnd, xEnd - arrowLength, yEnd + arrowLength, axisPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(XPoint + arrowLength, heightMeasureSpec);
    }
}

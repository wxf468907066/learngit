package com.lubangame.chartview.diyview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

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


public class LineChartDataView extends Chart {

    public LineChartDataView(Context context) {
        this(context, null);
    }

    public LineChartDataView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChartDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas canvas) {
        drawLable(canvas);
        drawDataPoint(canvas);
        drawDataPath(canvas);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((nums.size()) * XDistance, heightMeasureSpec);
    }

    public void drawLable(Canvas canvas) {
        //x轴标注信息
        int xScale;
        for (int i = 0; i < lables.size(); i++) {
            xScale = i * XDistance;
            canvas.drawText(lables.get(i), XPoint + xScale - DimenUtils.dp2Px(context, 12), YPoint + DimenUtils.dp2Px(context, 12), xLablePaint);
            canvas.drawLine(XPoint + xScale, YPoint, XPoint + xScale, YPoint - arrawLength, xLablePaint);
        }
    }

    /**
     * 绘制数据点连线
     *
     * @param canvas
     */
    private void drawDataPath(Canvas canvas) {
        if (pointFs.size() != 0) {
            Path path = new Path();
            path.moveTo(XPoint, YPoint);
            for (int i = 0; i < pointFs.size(); i++) {
                PointF pointF = pointFs.get(i);
                path.lineTo(pointF.x, pointF.y);
                if (i == pointFs.size() - 1) {
                    path.lineTo(pointF.x, YPoint);
                }
            }
            canvas.drawPath(path, linePaint);
        }
    }

    /**
     * 绘制数据源点
     *
     * @param canvas
     */
    private void drawDataPoint(Canvas canvas) {
        for (int i = 0; i < nums.size(); i++) {
            PointF point = new PointF();
            point.x = (XPoint + i * XDistance);
            float y = (10 * YDistance / maxNum) * (nums.get(i));
            point.y = YPoint - y;
            canvas.drawCircle(point.x, point.y, 3, pointPaint);
            pointFs.add(point);
        }
    }
}

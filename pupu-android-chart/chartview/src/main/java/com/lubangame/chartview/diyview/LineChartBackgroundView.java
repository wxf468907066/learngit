package com.lubangame.chartview.diyview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.lubangame.chartview.R;
import com.lubangame.chartview.utils.DimenUtils;

/**
 * Description
 *
 * @author weixuefeng@lubangame.com
 * @version 1.0
 * @copyright (c) 2016 Beijing ShenJiangHuDong Technology Co., Ltd. All rights reserved.
 */

public class LineChartBackgroundView extends Chart {
    private String xLable;
    private String yLable;
    public LineChartBackgroundView(Context context) {
        this(context, null);
    }

    public LineChartBackgroundView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChartBackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null){
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LineCharView);
            xLable = array.getString(R.styleable.LineCharView_xLable);
            yLable = array.getString(R.styleable.LineCharView_yLable);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        XPoint = XDistance;
        drawLable(canvas);
        drawXYLine(canvas);
    }

    public void drawLable(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(DimenUtils.dp2Px(context, 10));
        //x轴titile信息
        canvas.drawText(xLable, XPoint + DimenUtils.dp2Px(context, 10), YDistance / 2 - DimenUtils.dp2Px(context, 5), paint);
        //y轴title信息
        canvas.drawText(yLable, screenWidth - 2 * XDistance, YPoint - DimenUtils.dp2Px(context, 15), paint);
    }

    /**
     * 绘制XY坐标轴
     * @param canvas
     */
    private void drawXYLine(Canvas canvas) {
        //X轴
        canvas.drawLine(XPoint, YPoint, screenWidth - XDistance / 2, YPoint, axisPaint);
        //X箭头
        canvas.drawLine(screenWidth - XDistance / 2, YPoint, screenWidth - XDistance / 2 - arrawLength, YPoint + arrawLength, axisPaint);
        canvas.drawLine(screenWidth - XDistance / 2, YPoint, screenWidth - XDistance / 2 - arrawLength, YPoint - arrawLength, axisPaint);
        //Y右键头
        canvas.drawLine(XPoint, YDistance / 2, XPoint + arrawLength, YDistance / 2 + arrawLength, axisPaint);
    }


}

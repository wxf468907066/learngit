package com.lubangame.recycle_master;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Shader;
import android.util.AttributeSet;

/**
 * Description
 *
 * @author weixuefeng@lubangame.com
 * @version 1.0
 * @copyright (c) 2016 Beijing ShenJiangHuDong Technology Co., Ltd. All rights reserved.
 */

public class ChartItemView extends AxisView {
    private ChartData pre = new ChartData(new PointF(0, 0), "10-1");
    private ChartData next = new ChartData(new PointF(0, 0), "10-2");
    private Path path;
    private Paint paint;
    private int width;
    private int arrowLength;
    private Paint scalePaint;
    private LinearGradient lg;

    public ChartItemView(Context context) {
        this(context, null);
    }

    public ChartItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        arrowLength = DimenUtils.dp2Px(context, 5);
        scalePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        scalePaint.setStrokeWidth(5.5f);
        scalePaint.setColor(Color.WHITE);
        path = new Path();
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        if (xAxisMid) {
            lg = new LinearGradient(0, 0, 0, screenHeight, Color.parseColor("#eb7451"), Color.parseColor("#56bde8"), Shader.TileMode.MIRROR);
        } else {
            lg = new LinearGradient(0, 0, 0, screenHeight, Color.parseColor("#eb7451"), Color.parseColor("#333333"), Shader.TileMode.MIRROR);
        }
        paint.setShader(lg);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.moveTo(0, YPoint);
        path.lineTo(0, YPoint - height * pre.getY() / maxNum);
        path.lineTo(width, YPoint - height * next.getY() / maxNum);
        path.lineTo(width, YPoint);
        path.close();
        canvas.drawPath(path, paint);
        canvas.drawLine(width, YPoint, width, YPoint - arrowLength, scalePaint);
        canvas.drawText(next.getLable(), width, YPoint + 3 * arrowLength, axisPaint);
        path.reset();
    }

    public void update(ChartData pre, ChartData next) {
        this.pre = pre;
        this.next = next;
        this.maxNum = pre.getMaxNum();
        setMaxNum(maxNum);
    }
}

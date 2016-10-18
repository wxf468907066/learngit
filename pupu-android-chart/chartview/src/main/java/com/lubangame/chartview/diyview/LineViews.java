package com.lubangame.chartview.diyview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import com.lubangame.chartview.R;
import com.lubangame.chartview.utils.DimenUtils;

import java.util.ArrayList;

/**
 * Description
 * @author weixuefeng@lubangame.com
 * @version 1.0
 * @copyright (c) 2016 Beijing ShenJiangHuDong Technology Co., Ltd. All rights reserved.
 */


public class LineViews extends ViewGroup {

    private ArrayList<ArrayList<String>> lables1;

    private ArrayList<ArrayList<Integer>> lists;

    private ChartData chartData;
    private int XDistance;

    private int XScaleNums;
    private boolean xAxisMid;
    private LineChartDataView dataView;

    public LineViews(Context context) {
        this(context, null);
    }

    public LineViews(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        xAxisMid = context.obtainStyledAttributes(attrs,R.styleable.LineCharView).getBoolean(R.styleable.LineCharView_xAxisIsMid,false);
    }

    public LineViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (lables1 != null){
            setMeasuredDimension(lables1.size()*DimenUtils.getScreenWidth(getContext()), heightMeasureSpec);
        }else {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);
            setMeasuredDimension(width, height);
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(i * DimenUtils.getScreenWidth(getContext()), 0, (i + 1) * DimenUtils.getScreenWidth(getContext()), DimenUtils.getScreenHeight(getContext()));
        }
        if (XDistance != 0) {
            setTranslationX(XDistance);
        }
    }

    public int getLeftRange() {
        if (lists != null && lists.size() > 0) {
            int a = lists.size();
            int b = lists.get(a - 1).size();
            int c = (a - 1) * XScaleNums + b;
            if (a > 1 || lists.get(0).size() == 20) {
                return (c + 1) * XDistance - DimenUtils.getScreenWidth(getContext());
            }
        }
        return 0;
    }


    public void setData(ChartData chartData) {
        this.chartData = chartData;
        this.lables1 = chartData.lableLists;
        this.lists = chartData.numLists;
        for (int i = 0; i < lables1.size(); i++) {
            dataView = new LineChartDataView(getContext());
            dataView.setxAxisIsMid(xAxisMid);
            chartData.xLables = lables1.get(i);
            chartData.yLables = lists.get(i);
            dataView.setData(chartData);
            addView(dataView);
            if (i == 0) {
                XDistance = dataView.getXDistance();
                XScaleNums = dataView.getXScaleNums();
            }
        }
    }
}

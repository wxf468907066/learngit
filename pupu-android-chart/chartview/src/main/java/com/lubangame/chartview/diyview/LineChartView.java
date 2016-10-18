package com.lubangame.chartview.diyview;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Description
 *
 * @author weixuefeng@lubangame.com
 * @version 1.0
 * @copyright (c) 2016 Beijing ShenJiangHuDong Technology Co., Ltd. All rights reserved.
 */


public class LineChartView extends RelativeLayout {

    private LineChartBackgroundView backgroundView;

    private LineViews lineViews;

    private AxisYBackground yBackground;

    private ViewDragHelper mdragHelper;

    private int leftRange;

    private String TAG = "LineChartView";

    protected ArrayList<String> lables = new ArrayList<>();

    protected ArrayList<Integer> nums = new ArrayList<>();
    /**
     * 每组数据的集合
     */
    protected ArrayList<Integer> numslist;
    /**
     * 所有组的集合.
     */
    protected ArrayList<ArrayList<Integer>> lists;

    /**
     * 所有标签组的集合
     */
    protected ArrayList<ArrayList<String>> lables1;

    /**
     * 每组标签的集合
     */
    protected ArrayList<String> lablelist;

    /**
     * Y轴最大值
     */
    private int maxNum = 100;
    /**
     * x轴的刻度数量
     */
    protected int xScaleNums = 20;

    private ChartData chartData;

    public LineChartView(Context context) {
        this(context, null);
    }

    public LineChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public LineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
        initDragView();
    }

    private void initDragView() {
        mdragHelper = ViewDragHelper.create(this, 5.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == lineViews;
            }

            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
            }

            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
            }

            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                super.onEdgeDragStarted(edgeFlags, pointerId);
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return leftRange;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (left > 0) {
                    return 0;
                } else if (left < -leftRange) {
                    return -leftRange;
                }
                return left;
            }
        });
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        backgroundView = new LineChartBackgroundView(context, attrs);
        lineViews = new LineViews(context, attrs);
        yBackground = new AxisYBackground(context, attrs);
    }


    protected void dispatchData() {
        lists = new ArrayList<>();
        lables1 = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            if (i % xScaleNums == 0) {
                if (numslist != null && lablelist != null && numslist.size() > 0) {
                    lists.add(numslist);
                    lables1.add(lablelist);
                }
                numslist = new ArrayList<>();
                lablelist = new ArrayList<>();
            }
            numslist.add(nums.get(i));
            lablelist.add(lables.get(i));
            //最后一组数据
            if (i == nums.size() - 1) {
                if (numslist != null && numslist.size() > 0) {
                    lists.add(numslist);
                    lables1.add(lablelist);
                }
            }
        }
        //整合数据
        for (int i = 0; i < lables1.size() - 1; i++) {
            lists.get(i).add(lists.get(i + 1).get(0));
            lables1.get(i).add(lables1.get(i + 1).get(0));
        }

        if (lables1 != null && lables1.size() > 0) {
            chartData.lableLists = lables1;
            chartData.numLists = lists;

            backgroundView.setData(chartData);
            yBackground.setData(chartData);
            lineViews.setData(chartData);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mdragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            mdragHelper.processTouchEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addView(backgroundView);
        addView(lineViews);
        addView(yBackground);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        leftRange = lineViews.getLeftRange();
    }

    @Override
    public void computeScroll() {
        if (mdragHelper.continueSettling(true)) {
            invalidate();
        }
    }


    public void setData(ChartData chartData) {
        this.chartData = chartData;
        this.lables = chartData.xLables;
        this.nums = chartData.yLables;
        this.maxNum = chartData.maxNum;
        if (chartData.backgroundColor != 0) {
            setBackgroundColor(chartData.backgroundColor);
        }
        dispatchData();
    }
}

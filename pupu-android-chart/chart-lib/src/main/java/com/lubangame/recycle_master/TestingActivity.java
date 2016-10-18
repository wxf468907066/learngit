package com.lubangame.recycle_master;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;
/**
 * a.Setting xAxis position is mid in xml file.
 * b.Setting every Point to ChartData.then add data to List for RecyclerView.Adapter.
 * c.The ChartData contains a Point for Y,a lable for X,a maxNum for ChartDataView.
 *
 * @author weixuefeng@lubangame.com
 * @version 1.0
 * @copyright (c) 2016 Beijing ShenJiangHuDong Technology Co., Ltd. All rights reserved.
 */

public class TestingActivity extends AppCompatActivity {
    private RecyclerView chartView;
    private PointF point;
    private String lable;
    private int maxNum = 200;
    private ArrayList<ChartData> chartDatas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        chartView = (RecyclerView) findViewById(R.id.chartView);
        AxisY axisY = (AxisY) findViewById(R.id.axisy);
        axisY.setMaxNum(maxNum);
        initData();
        initCycle();
    }

    private void initData() {
        Random random = new Random();
        PointF p = new PointF(0,100);
        ChartData c = new ChartData(p,"xx",maxNum);
        ChartData c1 = new ChartData(p,"xx",maxNum);
        chartDatas.add(c);
        chartDatas.add(c1);
        for (int i = 0; i < 100; i++) {
            lable = new String("10-" + i);
            point = new PointF(0,random.nextInt(100));
            ChartData chartData = new ChartData(point,lable,maxNum);
            chartDatas.add(chartData);
        }
        for (int i = 0; i < 100; i++) {
            lable = new String("8-" + i);
            point = new PointF(0,(0-random.nextInt(100)));
            ChartData chartData = new ChartData(point,lable,maxNum);
            chartDatas.add(chartData);
        }
    }

    private void initCycle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        ChartAdapter adapter = new ChartAdapter(chartDatas);
        chartView.setLayoutManager(linearLayoutManager);
        chartView.setAdapter(adapter);
    }
}

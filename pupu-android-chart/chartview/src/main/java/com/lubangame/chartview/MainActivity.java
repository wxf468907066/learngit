package com.lubangame.chartview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.lubangame.chartview.diyview.Chart;
import com.lubangame.chartview.diyview.ChartData;
import com.lubangame.chartview.diyview.LineChartView;
import com.lubangame.chartview.utils.DimenUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> lables = new ArrayList<>();
    private ArrayList<Integer> nums = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        int backgroundColor = Color.parseColor("#3F51B5");
        initView(backgroundColor);

    }

    private void initView(int backgroundColor) {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_main);
        LineChartView lineChartView = (LineChartView) findViewById(R.id.lineChart);
        ChartData chartData = new ChartData();
        chartData.backgroundColor = backgroundColor;
        chartData.lineColor = Color.RED;
        chartData.startColor = Color.parseColor("#FF8000");
        chartData.endColor = Color.parseColor("#33333333");
        chartData.isGradient = true;
        chartData.isFill = true;
        chartData.maxNum = 200;
        chartData.xLables = lables;
        chartData.yLables = nums;
        chartData.xLableSize = DimenUtils.dp2Px(this,10);
        chartData.yLableSize = DimenUtils.dp2Px(this,10);
        chartData.axisColor = Color.WHITE;
        chartData.pointColor = Color.BLUE;
        lineChartView.setData(chartData);
        Chart chart = (Chart) lineChartView.getChildAt(0);
        //下面这个view遮住最右边.
        View view = new View(this);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(chart.getXSurplusRange(),DimenUtils.getScreenHeight(this));
        params1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        view.setLayoutParams(params1);
        view.setBackgroundColor(backgroundColor);
        layout.addView(view);
    }

    private void initData() {
        for (int i = 1; i <= 100; i++) {
            String days = "10-" + i;
            lables.add(days);
        }
        nums.add(10);
        nums.add(20);
        nums.add(30);
        nums.add(40);
        nums.add(50);
        nums.add(62);
        nums.add(78);
        nums.add(86);
        nums.add(95);
        nums.add(108);

        nums.add(90);
        nums.add(80);
        nums.add(60);
        nums.add(50);
        nums.add(40);
        nums.add(32);
        nums.add(28);
        nums.add(16);
        nums.add(95);
        nums.add(-108);

        nums.add(-90);
        nums.add(-80);
        nums.add(-60);
        nums.add(-50);
        nums.add(-40);
        nums.add(-32);
        nums.add(-28);
        nums.add(-16);
        nums.add(-95);
        nums.add(-108);

        nums.add(108);
        nums.add(80);
        nums.add(60);
        nums.add(50);
        nums.add(40);
        nums.add(32);
        nums.add(28);
        nums.add(16);
        nums.add(95);
        nums.add(108);

        nums.add(108);
        nums.add(80);
        nums.add(60);
        nums.add(50);
        nums.add(40);
        nums.add(32);
        nums.add(28);
        nums.add(16);
        nums.add(95);
        nums.add(108);

        nums.add(10);
        nums.add(20);
        nums.add(30);
        nums.add(40);
        nums.add(50);
        nums.add(62);
        nums.add(78);
        nums.add(86);
        nums.add(95);
        nums.add(108);

        nums.add(90);
        nums.add(80);
        nums.add(60);
        nums.add(50);
        nums.add(40);
        nums.add(32);
        nums.add(28);
        nums.add(16);
        nums.add(95);
        nums.add(108);

        nums.add(90);
        nums.add(80);
        nums.add(60);
        nums.add(50);
        nums.add(40);
        nums.add(32);
        nums.add(28);
        nums.add(16);
        nums.add(95);
        nums.add(108);

        nums.add(108);
        nums.add(80);
        nums.add(60);
        nums.add(50);
        nums.add(40);
        nums.add(32);
        nums.add(28);
        nums.add(16);
        nums.add(95);
        nums.add(108);

        nums.add(108);
        nums.add(80);
        nums.add(60);
        nums.add(50);
        nums.add(40);
        nums.add(32);
        nums.add(28);
        nums.add(16);
        nums.add(95);
        nums.add(108);
    }

}

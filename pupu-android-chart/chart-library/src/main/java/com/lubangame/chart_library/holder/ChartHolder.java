package com.lubangame.chart_library.holder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lubangame.chart_library.ChartData;
import com.lubangame.chart_library.view.ChartItemView;

/**
 * Description
 *
 * @author weixuefeng@lubangame.com
 * @version 1.0
 * @copyright (c) 2016 Beijing ShenJiangHuDong Technology Co., Ltd. All rights reserved.
 */

public class ChartHolder extends RecyclerView.ViewHolder {
    ChartItemView chartItemView;

    public ChartHolder(View itemView) {
        super(itemView);
        chartItemView = (ChartItemView) itemView.findViewById(R.id.chartItemView);
    }

    public static ChartHolder create(ViewGroup parent){
        return new ChartHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,parent,false));
    }

    public void update(ChartData pre, ChartData next) {
        chartItemView.update(pre,next);
    }
}

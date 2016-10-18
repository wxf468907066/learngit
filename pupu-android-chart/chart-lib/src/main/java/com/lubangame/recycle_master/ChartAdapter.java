package com.lubangame.recycle_master;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Description
 *
 * @author weixuefeng@lubangame.com
 * @version 1.0
 * @copyright (c) 2016 Beijing ShenJiangHuDong Technology Co., Ltd. All rights reserved.
 */

public class ChartAdapter extends RecyclerView.Adapter<ChartHolder> {

    private ArrayList<ChartData> chartDatas;

    public ChartAdapter(ArrayList<ChartData> chartDatas){
        this.chartDatas = chartDatas;
    }
    @Override
    public ChartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ChartHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ChartHolder holder, int position) {
        ChartData pre = chartDatas.get(position);
        ChartData next = chartDatas.get(position+1);
        holder.update(pre,next);
    }

    @Override
    public int getItemCount() {
        return chartDatas.size() - 1;
    }
}

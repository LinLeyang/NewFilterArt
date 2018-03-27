package com.penta.newfilterart.filter.adapter;

import android.support.v7.widget.RecyclerView;

import com.penta.newfilterart.filter.bean.FilterBean;

/**
 * Created by linyueyang on 2018/3/27.
 */

public abstract class FilterBaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    onItemClickListener onItemClickListener;

    public void restoreData() {
        refreshSelectedData();
        notifyDataSetChanged();
    }

    abstract void saveSelectedToModel();

    abstract void refreshSelectedData();

    public void setOnItemClickListener(FilterSingleChoiceAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onClick(FilterBean filterBean);
    }
}

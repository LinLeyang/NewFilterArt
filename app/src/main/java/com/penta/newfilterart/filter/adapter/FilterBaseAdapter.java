package com.penta.newfilterart.filter.adapter;

import android.support.v7.widget.RecyclerView;

import com.penta.newfilterart.filter.bean.FilterBean;

/**
 * Created by linyueyang on 2018/3/27.
 *
 * Filter所有最低层item的适配器基类
 *
 */

public abstract class FilterBaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    onItemClickListener onItemClickListener;

    /**
     * 还原原有的选中 刷新页面
     */
    public void restoreData() {
        refreshSelectedData();
        notifyDataSetChanged();
    }

    /**
     * 临时选中-》真正选中
     */
    public abstract void saveSelectedToModel();

    /**
     * 记录临时选中状态
     */
    abstract void refreshSelectedData();

    public void setOnItemClickListener(FilterSingleChoiceAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onClick(FilterBean filterBean);
    }
}

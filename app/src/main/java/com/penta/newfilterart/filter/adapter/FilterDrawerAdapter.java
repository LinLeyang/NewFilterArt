package com.penta.newfilterart.filter.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.penta.newfilterart.R;
import com.penta.newfilterart.filter.bean.FilterBean;
import com.penta.newfilterart.filter.view.CommonDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linyueyang on 2018/3/26.
 */

public class FilterDrawerAdapter extends RecyclerView.Adapter<FilterDrawerAdapter.ViewHolder> {

    Context context;
    List<FilterBean> filterBeanList;

    Map<Integer, FilterBaseAdapter> multipleChoiceAdapterMap = new HashMap<>();

    public FilterDrawerAdapter(Context context, List<FilterBean> filterBeanList) {
        this.context = context;
        this.filterBeanList = filterBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_filter_multiple, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FilterBean filterBean = filterBeanList.get(position);
        holder.tvFilterItemTitle.setText(filterBean.getText());
        if (filterBean.getSubList() != null) {

            FilterBaseAdapter multipleChoiceAdapter = multipleChoiceAdapterMap.get(position);

            if (null == multipleChoiceAdapter) {
                multipleChoiceAdapter = new FilterMultipleChoiceAdapter(context, filterBean.getSubList());
                multipleChoiceAdapterMap.put(position, multipleChoiceAdapter);
            }

            holder.rvFilterItem.setLayoutManager(new GridLayoutManager(context, 3));
            holder.rvFilterItem.setAdapter(multipleChoiceAdapter);
            CommonDecoration commonDecoration = new CommonDecoration(context);
            commonDecoration.setHeight(10);
            commonDecoration.setWidth(10);
            holder.rvFilterItem.addItemDecoration(commonDecoration);
        }
    }

    @Override
    public int getItemCount() {
        if (null != filterBeanList)
            return filterBeanList.size();
        return 0;
    }

    public void restoreData() {
        for (Map.Entry<Integer, FilterBaseAdapter> entry : multipleChoiceAdapterMap.entrySet()) {
            entry.getValue().restoreData();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFilterItemTitle;
        RecyclerView rvFilterItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvFilterItemTitle = itemView.findViewById(R.id.tv_filter_item_title);
            rvFilterItem = itemView.findViewById(R.id.rv_filter_item);
        }
    }

    public void saveSelectedToModel() {
        for (Map.Entry<Integer, FilterBaseAdapter> entry : multipleChoiceAdapterMap.entrySet()) {
            entry.getValue().saveSelectedToModel();
        }
    }

}

package com.penta.newfilterart.filter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.penta.newfilterart.R;
import com.penta.newfilterart.filter.bean.FilterBean;

import java.util.List;

/**
 * Created by linyueyang on 4/27/17.
 */

public class FilterSingleChoiceAdapter extends FilterBaseAdapter<FilterSingleChoiceAdapter.FilterViewHolder> {

    Context context;
    List<FilterBean> filterBeanList;



    FilterBean selectedBean;

    public FilterSingleChoiceAdapter(Context context, List<FilterBean> filterBeanList) {
        this.context = context;
        this.filterBeanList = filterBeanList;

        if (null == filterBeanList || filterBeanList.size() == 0) {
            return;
        }
        refreshSelectedData();
    }

    @Override
    public FilterSingleChoiceAdapter.FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FilterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_filter, parent, false));
    }

    @Override
    public void onBindViewHolder(FilterSingleChoiceAdapter.FilterViewHolder holder, final int position) {
        final FilterBean filterBean = filterBeanList.get(position);
        if (selectedBean == filterBean) {
            holder.textView.setTextColor(context.getResources().getColor(R.color.hy_common_orange));
            holder.textView.setBackgroundColor(context.getResources().getColor(R.color.hy_common_bg_gray));
        } else {
            holder.textView.setTextColor(context.getResources().getColor(R.color.hy_common_text_black));
            holder.textView.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

        holder.textView.setText(filterBean.getText());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBean = filterBean;
                notifyDataSetChanged();
                if (null != onItemClickListener) {
                    onItemClickListener.onClick(filterBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (null != filterBeanList)
            return filterBeanList.size();
        return 0;
    }

    class FilterViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public FilterViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv);
        }
    }



    @Override
    public void saveSelectedToModel() {
        for (FilterBean filterBean : filterBeanList) {
            if (selectedBean == filterBean) {
                filterBean.setSelected(true);
            } else {
                filterBean.setSelected(false);
            }
        }

    }
    @Override
    public void refreshSelectedData() {
        for (FilterBean filterBean : filterBeanList) {
            if (filterBean.isSelected()) {
                selectedBean = filterBean;
            }
        }
        if (null == selectedBean) {
            selectedBean = filterBeanList.get(0);
        }
    }


}

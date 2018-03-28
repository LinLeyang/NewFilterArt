package com.penta.newfilterart.filter.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.penta.newfilterart.DpPxUtil;
import com.penta.newfilterart.R;
import com.penta.newfilterart.filter.bean.FilterBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linyueyang on 9/4/17.
 *
 * 多选筛选框适配器
 *
 */

public class FilterMultipleChoiceAdapter extends FilterBaseAdapter<FilterMultipleChoiceAdapter.MultipleChoiceViewHolder> {

    private Context context;
    private List<FilterBean> filterBeanList;

    List<FilterBean> selectedFilterBean = new ArrayList<>();

    public FilterMultipleChoiceAdapter(Context context, @Nullable List<FilterBean> filterBeanList) {
        this.context = context;
        setFilterBeanList(filterBeanList);
    }

    public void setFilterBeanList(List<FilterBean> filterBeanList) {
        this.filterBeanList = filterBeanList;
        refreshSelectedData();
    }

    @Override
    public MultipleChoiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(context);
        textView.setPadding(DpPxUtil.dip2px(context, 5), DpPxUtil.dip2px(context, 10), DpPxUtil.dip2px(context, 5), DpPxUtil.dip2px(context, 10));
        textView.setTextSize(12);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);
        return new MultipleChoiceViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(MultipleChoiceViewHolder holder, final int position) {
        final FilterBean filterBean = filterBeanList.get(position);

        holder.textView.setText(filterBean.getText());
        if (selectedFilterBean.contains(filterBean)) {
            holder.textView.setTextColor(context.getResources().getColor(R.color.hy_common_orange));
            holder.textView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.choice_selected_shape));
        } else {
            holder.textView.setTextColor(context.getResources().getColor(R.color.hy_common_text_black));
            holder.textView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.choise_unselected_shape));
        }
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem(position, filterBean);
            }
        });
    }

    private void clickItem(int position, FilterBean filterBean) {
        if (position == 0) {
            selectedFilterBean.clear();
            selectedFilterBean.add(filterBean);
        } else {
            if (selectedFilterBean.contains(filterBeanList.get(0))) {
                selectedFilterBean.remove(filterBeanList.get(0));
            }

            if (selectedFilterBean.contains(filterBean)) {
                selectedFilterBean.remove(filterBean);
            } else {
                selectedFilterBean.add(filterBean);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void saveSelectedToModel(){
        for (FilterBean filterBean : filterBeanList) {
            if (selectedFilterBean.contains(filterBean)) {
                filterBean.setSelected(true);
            }else {
                filterBean.setSelected(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (null != filterBeanList)
            return filterBeanList.size();
        else
            return 0;
    }

    public class MultipleChoiceViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MultipleChoiceViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }

    @Override
    public void refreshSelectedData(){
        selectedFilterBean.clear();
        for (FilterBean filterBean : filterBeanList) {
            if (filterBean.isSelected()) {
                selectedFilterBean.add(filterBean);
            }
        }
    }

}

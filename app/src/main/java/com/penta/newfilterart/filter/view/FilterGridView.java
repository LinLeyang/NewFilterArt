package com.penta.newfilterart.filter.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.penta.newfilterart.R;
import com.penta.newfilterart.filter.adapter.FilterBaseAdapter;
import com.penta.newfilterart.filter.adapter.FilterMultipleChoiceAdapter;
import com.penta.newfilterart.filter.bean.FilterBean;

/**
 * Created by linyueyang on 2018/3/22.
 * <p>
 * 单级多选筛选项View
 */

public class FilterGridView extends FilterBaseView {

    View view;

    RecyclerView rvGridFilter;
    TextView tvGridFilterTitle;
    TextView tvGridFilterConfirm;

    public FilterGridView(Context context) {
        super(context);
    }

    public FilterGridView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FilterGridView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void initView() {
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    protected void bindDataToView(FilterBean filterBean) {

        if (filterBean == null) {
            return;
        }

        this.filterBean = filterBean;

        view = LayoutInflater.from(context).inflate(R.layout.filter_grid_view, this, false);
        rvGridFilter = view.findViewById(R.id.rv_filter_item);
        tvGridFilterTitle = view.findViewById(R.id.tv_filter_item_title);
        tvGridFilterConfirm = view.findViewById(R.id.tv_grid_filter_confirm);

        tvGridFilterTitle.setText(filterBean.getText());

        filterAdapter = new FilterMultipleChoiceAdapter(context, filterBean.getSubList());
        rvGridFilter.setLayoutManager(new GridLayoutManager(context, 4));
        rvGridFilter.setAdapter(filterAdapter);
        CommonDecoration commonDecoration = new CommonDecoration(context);
        commonDecoration.setHeight(10);
        commonDecoration.setWidth(10);
        rvGridFilter.addItemDecoration(commonDecoration);
        tvGridFilterConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                filterAdapter.saveSelectedToModel();
                confirmClick();
            }
        });

        addView(view);

    }

}

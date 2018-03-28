package com.penta.newfilterart.filter.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.penta.newfilterart.R;
import com.penta.newfilterart.filter.adapter.FilterBaseAdapter;
import com.penta.newfilterart.filter.adapter.FilterDrawerAdapter;
import com.penta.newfilterart.filter.bean.FilterBean;

/**
 * Created by linyueyang on 2018/3/27.
 *
 * 侧滑抽屉式筛选View
 *
 */

public class FilterDrawerView extends FilterBaseView {

    RelativeLayout drawView;
    RecyclerView rvDrawerFilter;
    TextView tvDrawerFilterClear;
    TextView tvDrawerFilterConfirm;

    public FilterDrawerView(Context context) {
        super(context);
    }

    public FilterDrawerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FilterDrawerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView() {

        drawView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.filter_drawer_view, null);
        drawView.setBackgroundColor(context.getResources().getColor(R.color.white));

        rvDrawerFilter = drawView.findViewById(R.id.rv_drawer_filter);
        tvDrawerFilterClear = drawView.findViewById(R.id.tv_drawer_filter_clear);
        tvDrawerFilterConfirm = drawView.findViewById(R.id.tv_drawer_filter_confirm);
        rvDrawerFilter.setLayoutManager(new LinearLayoutManager(context));
        addView(drawView);

        tvDrawerFilterConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                filterAdapter.saveSelectedToModel();
                confirmClick();
            }
        });
    }


    @Override
    protected void bindDataToView(FilterBean filterBean) {
        filterAdapter = new FilterDrawerAdapter(context, filterBean.getSubList());
        rvDrawerFilter.setAdapter(filterAdapter);
    }



}

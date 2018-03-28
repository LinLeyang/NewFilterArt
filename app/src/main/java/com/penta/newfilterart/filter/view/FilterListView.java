package com.penta.newfilterart.filter.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.penta.newfilterart.filter.adapter.FilterBaseAdapter;
import com.penta.newfilterart.filter.adapter.FilterSingleChoiceAdapter;
import com.penta.newfilterart.filter.bean.FilterBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linyueyang on 2018/3/22.
 * <p>
 * 多级列表筛选View
 */

public class FilterListView extends FilterBaseView {

    List<RecyclerView> recyclerViewList = new ArrayList<>();

    public FilterListView(Context context) {
        super(context);
    }

    public FilterListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FilterListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView() {
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    protected void bindDataToView(FilterBean filterBean) {
        this.filterBean = filterBean;
        if (filterBean != null && filterBean.getSubList() != null && filterBean.getSubList().size() > 0) {
            initRecyclerView(filterBean);
        }
    }

    /**
     * 还原原有选中样式 ListFilter特殊处理复写restoreSelectedData
     */
    @Override
    protected void restoreSelectedData() {
        if (recyclerViewList.size() <= 0) {
            return;
        }
        while (recyclerViewList.size() > 1) {
            removeView(recyclerViewList.get(1));
            recyclerViewList.remove(1);
        }
        ((FilterBaseAdapter) recyclerViewList.get(0).getAdapter()).restoreData();
    }

    private void initRecyclerView(FilterBean filterBean) {

        final RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final FilterBaseAdapter filterListAdapter = new FilterSingleChoiceAdapter(getContext(), filterBean.getSubList());
        filterListAdapter.setOnItemClickListener(new FilterSingleChoiceAdapter.onItemClickListener() {
            @Override
            public void onClick(FilterBean subFilterBean) {
                int layer = recyclerViewList.indexOf(recyclerView) + 1;
                while (recyclerViewList.size() > layer) {
                    removeView(recyclerViewList.get(layer));
                    recyclerViewList.remove(layer);
                }
                if (subFilterBean.isParent() && subFilterBean.getSubList() != null && subFilterBean.getSubList().size() > 0) {
                    initRecyclerView(subFilterBean);
                } else {
                    for (RecyclerView rv : recyclerViewList) {
                        ((FilterSingleChoiceAdapter) rv.getAdapter()).saveSelectedToModel();
                    }
                    confirmClick();
                }
            }
        });
        recyclerView.setAdapter(filterListAdapter);

        addView(recyclerView);
        recyclerViewList.add(recyclerView);
    }


}

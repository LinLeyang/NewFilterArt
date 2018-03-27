package com.penta.newfilterart.filter.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.penta.newfilterart.R;
import com.penta.newfilterart.filter.bean.FilterBean;
import com.penta.newfilterart.filter.bean.FilterConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linyueyang on 2018/3/21.
 * <p>
 * Filter容器类
 */

public class FilterContainerView extends LinearLayout {

    Context context;

    /**
     * 筛选Tab栏相关
     */

    FilterTabView filterTabView;//筛选组件Tab栏

    /**
     * 筛选主体相关
     */
    //FilterView filterListContainerView;//当前选中的筛选组件
    Map<Integer, FilterBaseView> filterViewMap = new HashMap<>();//普通筛选项集合

    /**
     * 侧滑筛选项
     */
    DrawerLayout drawerLayout;
    //OnLastTabClick onLastTabClick;

    private static final int FILTER_INDEX = 1;//普通筛选项所在位置

    public FilterContainerView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public FilterContainerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public FilterContainerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    public void bindDataToView(List<FilterBean> filterBeanList) {

        filterTabView.bindDataToView(filterBeanList);

        //筛选Tab点击的回调
        filterTabView.setOnFilterTabClickListener(new FilterTabView.OnFilterTabClickListener() {
            @Override
            public void onClick(int position, FilterBean filterBean) {
                FilterBaseView filterView = filterViewMap.get(position);
                if (null == filterView) {
                    switch (filterBean.getFilterType()) {
                        case FilterConstants.FILTER_TYPE_SINGLE_CHOICE:
                            filterView = new FilterListView(context);
                            break;
                        case FilterConstants.FILTER_TYPE_MULTIPLE_CHOICE:
                            filterView = new FilterGridView(context);
                            break;
                        case FilterConstants.FILTER_TYPE_DRAWER:
                            //TODO:动态加入FilterDrawerView第一次展示有问题,暂时以xml布局替代,应有工号的解决方案
                            filterView = drawerLayout.findViewById(R.id.fdv);
                            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                            break;
                    }
                    filterView.bindDataToView(filterBean);
                    filterViewMap.put(position, filterView);
                }else {
                    filterView.restoreSelectedData();
                }
                changeView(filterView);

            }
        });

    }

    private void initView() {

        //初始化
        setOrientation(VERTICAL);
        heightWrapContent(FilterContainerView.this);
        setBackgroundColor(getResources().getColor(R.color.hy_toast_bg));
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clearView();
            }
        });

        //初始化filterTabView
        filterTabView = new FilterTabView(context);
        filterTabView.setBackgroundColor(getResources().getColor(R.color.white));
        addView(filterTabView);

        //TODO:初始化DrawerLayout

    }

    /**
     * 变换筛选框View
     *
     * @param filterView
     */
    private void changeView(FilterBaseView filterView) {

        if (filterView instanceof FilterDrawerView) {
            clearView();
            if (drawerLayout.isDrawerOpen(filterView)) {
                drawerLayout.closeDrawer(filterView);
            } else {
                drawerLayout.openDrawer(filterView);
            }
        } else {
            View view = getChildAt(FILTER_INDEX);
            if (null != view) {
                clearView();
            }
            if (filterView != view) {
                showView(filterView);
            }
        }
    }

    private void showView(FilterBaseView filterView) {
        addView(filterView);
        heightMatchParent(FilterContainerView.this);
    }

    /**
     * 清空筛选栏
     */
    private void clearView() {
        while (getChildCount() > FILTER_INDEX) {
            removeViewAt(FILTER_INDEX);
        }
        heightWrapContent(FilterContainerView.this);
    }

    private void heightWrapContent(View view) {
        view.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private void heightMatchParent(View view) {
        view.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }

}

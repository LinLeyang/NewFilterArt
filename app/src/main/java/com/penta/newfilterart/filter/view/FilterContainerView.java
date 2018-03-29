package com.penta.newfilterart.filter.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.penta.newfilterart.DpPxUtil;
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
     * 筛选主体View集合
     */
    Map<Integer, FilterBaseView> filterViewMap = new HashMap<>();//普通筛选项集合

    /**
     * 侧滑筛选项
     */
    DrawerLayout drawerLayout;
    FilterDrawerView filterDrawerView;

    private static final int FILTER_INDEX = 2;//普通筛选项所在位置

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
                            //TODO:动态加入FilterDrawerView第一次展示有问题,暂时以xml布局替代,应有更好的解决方案
                            if (null != drawerLayout && null != filterDrawerView) {
                                //drawerLayout的第一个子View必须是filterView
                                filterView = filterDrawerView;

                            }
                            break;
                    }
                    if (null != filterView) {
                        filterView.bindDataToView(filterBean);
                        filterViewMap.put(position, filterView);
                    }
                } else {
                    filterView.restoreSelectedData();
                }
                changeView(position, filterView);

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

        //初始化分割线
        View view = new View(context);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DpPxUtil.dip2px(context, 0.5f)));
        view.setBackgroundColor(getResources().getColor(R.color.hy_common_line_gray));
        addView(view);
    }

    /**
     * 变换筛选框View
     *
     * @param position
     * @param filterView
     */
    private void changeView(int position, FilterBaseView filterView) {

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
                showView(position, filterView);
            }
        }
    }

    private void showView(int position, FilterBaseView filterView) {
        filterTabView.selectTab(position);
        addView(filterView);
        heightMatchParent(FilterContainerView.this);

    }

    /**
     * 清空筛选栏
     */
    private void clearView() {
        filterTabView.restoreTab();
        while (getChildCount() > FILTER_INDEX) {
            removeViewAt(FILTER_INDEX);
        }
        heightWrapContent(FilterContainerView.this);
    }

    private void heightWrapContent(View view) {

        if (null == view.getLayoutParams()) {
            view.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        } else {
            view.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            view.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            view.requestLayout();
        }
    }

    private void heightMatchParent(View view) {
        if (null == view.getLayoutParams()) {
            view.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        } else {
            view.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            view.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            view.requestLayout();
        }
    }

    public void setDrawerLayout(DrawerLayout drawerLayout, int lock_mode, FilterDrawerView filterDrawerView) {
        this.drawerLayout = drawerLayout;
        this.filterDrawerView = filterDrawerView;
        drawerLayout.setDrawerLockMode(lock_mode);
    }

}

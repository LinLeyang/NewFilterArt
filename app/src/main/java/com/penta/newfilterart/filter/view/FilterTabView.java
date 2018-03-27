package com.penta.newfilterart.filter.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.penta.newfilterart.DpPxUtil;
import com.penta.newfilterart.R;
import com.penta.newfilterart.filter.bean.FilterBean;

import java.util.List;

/**
 * Created by linyueyang on 2018/3/22.
 */

public class FilterTabView extends LinearLayout {

    private List<FilterBean> filterBeanList;

    private Context context;
    private OnFilterTabClickListener onFilterTabClickListener;

    public FilterTabView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public FilterTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public FilterTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DpPxUtil.dip2px(context, 44)));
    }

    public void bindDataToView(List<FilterBean> filterBeanList) {
        this.filterBeanList = filterBeanList;
        if (filterBeanList != null && filterBeanList.size() > 0) {
            for (FilterBean filterBean : filterBeanList) {
                addItemView(filterBean, filterBean.isDrawer());
            }
        }
    }

    private void addItemView(final FilterBean filterBean, final boolean isMoreItem) {

        LinearLayout linear = new LinearLayout(context);
        linear.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        linear.setGravity(Gravity.CENTER);
        TextView textView = new TextView(context);
        if (filterBean.isSelected()) {
            textView.setText(filterBean.getText());
            textView.setTextColor(getResources().getColor(R.color.hy_common_orange));
        } else {
            textView.setText(filterBean.getText());
            textView.setTextColor(getResources().getColor(R.color.hy_common_text_black));
        }
        if (isMoreItem) {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.hy_filter), null);
        }
        textView.setGravity(Gravity.CENTER);
        linear.addView(textView);
        linear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onFilterTabClickListener) {
                    onFilterTabClickListener.onClick(filterBeanList.indexOf(filterBean), filterBean);
                }
            }
        });

        addView(linear);

    }

    public void setOnFilterTabClickListener(OnFilterTabClickListener onFilterTabClickListener) {
        this.onFilterTabClickListener = onFilterTabClickListener;
    }

    public interface OnFilterTabClickListener {
        void onClick(int position, FilterBean filterBean);
    }

}

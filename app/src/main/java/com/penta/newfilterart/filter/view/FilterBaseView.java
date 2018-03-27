package com.penta.newfilterart.filter.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.penta.newfilterart.filter.bean.FilterBean;

/**
 * Created by linyueyang on 2018/3/22.
 * <p>
 * 普通筛选项
 */

public abstract class FilterBaseView extends LinearLayout {

    protected Context context;
    protected FilterBean filterBean;
    protected onConfirmClickListener onConfirmClickListener;

    public FilterBaseView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public FilterBaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public FilterBaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    protected abstract void initView();

    protected abstract void bindDataToView(FilterBean filterBean);

    protected abstract void restoreSelectedData();

    protected void confirmClick() {
        if (onConfirmClickListener != null) {
            onConfirmClickListener.onConfirm();
        }
    }

    public void setOnConfirmClickListener(FilterBaseView.onConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
    }

    interface onConfirmClickListener {
        void onConfirm();
    }
}

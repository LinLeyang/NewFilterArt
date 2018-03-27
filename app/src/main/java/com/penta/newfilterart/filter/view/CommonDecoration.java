package com.penta.newfilterart.filter.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.penta.newfilterart.DpPxUtil;


/**
 * Created by linyueyang on 3/9/17.
 * <p>
 * 账号管理列表分割线
 */

public class CommonDecoration extends RecyclerView.ItemDecoration {
    Context context;

    float height = 0.5f;
    float width = 0f;

    public CommonDecoration(Context context) {
        this.context = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, DpPxUtil.dip2px(context, width), DpPxUtil.dip2px(context, height));
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}

package com.penta.newfilterart.filter.bean;

import java.util.List;

/**
 * Created by linyueyang on 2018/3/21.
 */

public class FilterBean {

    private String id; //站位相关id
    private String key; //给服务端的参数key
    private boolean isParent; //判断是否有子集
    private boolean selected; //是否被选中
    private String selectedText;  //选中展示文字
    private String text;  //展示文案
    private String value;  //给服务端的参数value
    private boolean isMultiple;  //是否支持多选
    private String filterType;  //子Item筛选类型1-单级模式 2-多级模式 无子View不穿 或传0
    private List<FilterBean> subList; //子集Filter数据
    private boolean isDrawer; //是否是抽屉筛选Item

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getSelectedText() {
        return selectedText;
    }

    public void setSelectedText(String selectedText) {
        this.selectedText = selectedText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isMultiple() {
        return isMultiple;
    }

    public void setMultiple(boolean multiple) {
        isMultiple = multiple;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public List<FilterBean> getSubList() {
        return subList;
    }

    public void setSubList(List<FilterBean> subList) {
        this.subList = subList;
    }

    public boolean isDrawer() {
        return isDrawer;
    }

    public void setDrawer(boolean drawer) {
        isDrawer = drawer;
    }
}

package com.osiris.food.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<T> extends BaseAdapter {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 实体类集合
     */
    private List<T> mDatas;
    private LayoutInflater mInflater;
    /**
     * 控件id
     */
    private int mlayoutId;

    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mDatas = datas;
        this.mlayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mDatas.size();
    }

    @Override
    public T getItem(int arg0) {
        // TODO Auto-generated method stub
        return mDatas.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        // TODO Auto-generated method stub
        ViewHolder holder = ViewHolder.get(mContext, arg1, arg2, mlayoutId,
                arg0);
        convert(holder, getItem(arg0));

        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T t);
}

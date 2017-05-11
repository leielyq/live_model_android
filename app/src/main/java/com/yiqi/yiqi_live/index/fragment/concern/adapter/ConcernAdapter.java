package com.yiqi.yiqi_live.index.fragment.concern.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiqi.yiqi_live.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9 0009.
 */


public class ConcernAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ConcernAdapter(@Nullable List<String> data) {
        super(R.layout.list_concern, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}


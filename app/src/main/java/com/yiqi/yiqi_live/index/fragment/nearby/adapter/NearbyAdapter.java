package com.yiqi.yiqi_live.index.fragment.nearby.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiqi.yiqi_live.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class NearbyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public NearbyAdapter(@Nullable List<String> data) {
        super(R.layout.list_nearby, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}

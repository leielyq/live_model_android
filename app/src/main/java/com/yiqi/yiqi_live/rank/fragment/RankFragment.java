package com.yiqi.yiqi_live.rank.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiqi.yiqi_live.R;
import com.yiqi.yiqi_live.base.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends BaseFragment {


    public RankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rank, container, false);
    }

}

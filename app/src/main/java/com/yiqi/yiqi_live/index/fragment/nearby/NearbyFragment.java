package com.yiqi.yiqi_live.index.fragment.nearby;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiqi.yiqi_live.R;
import com.yiqi.yiqi_live.base.fragment.BaseFragment;
import com.yiqi.yiqi_live.index.fragment.concern.ConcernFragment;
import com.yiqi.yiqi_live.index.fragment.concern.adapter.ConcernAdapter;
import com.yiqi.yiqi_live.index.fragment.nearby.adapter.NearbyAdapter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends BaseFragment {


    @BindView(R.id.fragment_nearby_list)
    RecyclerView mFragmentNearbyList;
    Unbinder unbinder;
    @BindView(R.id.fragment_nearby_refresh)
    SwipeRefreshLayout mFragmentNearbyRefresh;
    private NearbyAdapter mNearbyAdapter;
    private List<String> mStrings;
    private int mFlag;
    private static final String sFlag = "flag";

    public NearbyFragment() {
    }

    public static NearbyFragment newInstance(int param1) {
        NearbyFragment fragment = new NearbyFragment();
        Bundle args = new Bundle();
        args.putInt(sFlag, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFlag = getArguments().getInt(sFlag);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mFragmentNearbyList.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
        mStrings = new ArrayList<>();
        mStrings.add("6666");
        mNearbyAdapter = new NearbyAdapter(mStrings);
        mNearbyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mFragmentNearbyList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mStrings.add("6666");
                        mStrings.add("6666");
                        mStrings.add("6666");
                        mStrings.add("6666");
                        mStrings.add("6666");
                        mNearbyAdapter.loadMoreComplete();
                    }
                }, 1000);
            }
        }, mFragmentNearbyList);
        mNearbyAdapter.disableLoadMoreIfNotFullPage();

        mFragmentNearbyList.setAdapter(mNearbyAdapter);

        if (mFlag == 0) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.list_square_header, (ViewGroup) mFragmentNearbyList.getParent(), false);
            AutoUtils.autoSize(view);
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_square_header_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new ConcernAdapter(mStrings));
            mNearbyAdapter.addHeaderView(view);
        }

        mFragmentNearbyRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mFragmentNearbyList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mFragmentNearbyRefresh.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

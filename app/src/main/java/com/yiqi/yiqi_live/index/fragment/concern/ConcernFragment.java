package com.yiqi.yiqi_live.index.fragment.concern;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiqi.yiqi_live.R;
import com.yiqi.yiqi_live.base.fragment.BaseFragment;
import com.yiqi.yiqi_live.index.fragment.concern.adapter.ConcernAdapter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConcernFragment extends BaseFragment {


    @BindView(R.id.fragment_concern_list)
    RecyclerView mFragmentConcernList;
    Unbinder unbinder;
    @BindView(R.id.fragment_concern_refresh)
    SwipeRefreshLayout mFragmentConcernRefresh;
    private Context mContext;
    private ConcernAdapter mConcernAdapter;
    private static final String sFlag = "flag";
    private int mFlag;
    private List<String> mMStrings;

    public ConcernFragment() {
    }

    public static ConcernFragment newInstance(int param1) {
        ConcernFragment fragment = new ConcernFragment();
        Bundle args = new Bundle();
        args.putInt(sFlag, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        if (getArguments() != null) {
            mFlag = getArguments().getInt(sFlag);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_concern, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mFragmentConcernList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mMStrings = new ArrayList<>();
        mMStrings.add("6666");
        mConcernAdapter = new ConcernAdapter(mMStrings);
        View view = null;
        switch (mFlag) {
            case 0:
                view = LayoutInflater.from(mContext).inflate(R.layout.list_concern_header, (ViewGroup) mFragmentConcernList.getParent(), false);
                AutoUtils.autoSize(view);
                break;
            case 1:
                view = LayoutInflater.from(mContext).inflate(R.layout.list_square_header, (ViewGroup) mFragmentConcernList.getParent(), false);
                AutoUtils.autoSize(view);
                break;
        }
        if (view != null) {
            mConcernAdapter.addHeaderView(view);
        }

        mConcernAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mFragmentConcernList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMStrings.add("6666");
                        mMStrings.add("6666");
                        mMStrings.add("6666");
                        mMStrings.add("6666");
                        mConcernAdapter.loadMoreComplete();
                    }
                }, 1000);
            }
        }, mFragmentConcernList);
        mConcernAdapter.disableLoadMoreIfNotFullPage();
        mFragmentConcernList.setAdapter(mConcernAdapter);

        mFragmentConcernRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mFragmentConcernList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mFragmentConcernRefresh.setRefreshing(false);
                        mMStrings = new ArrayList<>();
                        mMStrings.add("6666");
                        mMStrings.add("6666");
                        mConcernAdapter.setNewData(mMStrings);
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

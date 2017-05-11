package com.yiqi.yiqi_live.index.fragment;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.transition.Fade;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.yiqi.yiqi_live.R;
import com.yiqi.yiqi_live.base.fragment.BaseFragment;
import com.yiqi.yiqi_live.index.fragment.concern.ConcernFragment;
import com.yiqi.yiqi_live.index.fragment.nearby.NearbyFragment;
import com.yiqi.yiqi_live.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.fragment_index_tab)
    TabLayout mFragmentIndexTab;
    @BindView(R.id.fragment_index_search)
    ImageButton mFragmentIndexSearch;
    @BindView(R.id.fragment_index_pager)
    ViewPager mFragmentIndexPager;
    private String[] mTitle;
    private List<BaseFragment> mFragments;

    public IndexFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle = new String[]{"关注", "广场", "推荐", "附近"};
        mFragments = new ArrayList<>();
        mFragments.add(ConcernFragment.newInstance(0));
        mFragments.add(NearbyFragment.newInstance(0));
        mFragments.add(ConcernFragment.newInstance(2));
        mFragments.add(NearbyFragment.newInstance(1));
    }

    private View imgs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        unbinder = ButterKnife.bind(this, view);
        imgs = view.findViewById(R.id.fragment_index_search);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mFragmentIndexPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mTitle.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle[position];
            }
        });
        mFragmentIndexTab.setupWithViewPager(mFragmentIndexPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fragment_index_search)
    public void onViewClicked() {
        Intent intent = new Intent(_mActivity, SearchActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(_mActivity,
                    Pair.create(imgs, "b1")
            ).toBundle();
            startActivity(intent, bundle);
        } else
            startActivity(intent);
    }
}

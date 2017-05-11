package com.yiqi.yiqi_live.search;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

import com.yiqi.yiqi_live.R;
import com.yiqi.yiqi_live.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @OnClick(R.id.activity_search_back)
    public void onViewClicked() {
        onBackPressed();
    }
}

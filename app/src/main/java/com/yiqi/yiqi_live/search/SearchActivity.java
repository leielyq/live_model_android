package com.yiqi.yiqi_live.search;

import android.app.Activity;
import android.os.Bundle;

import com.yiqi.yiqi_live.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_search_back)
    public void onViewClicked() {
        onBackPressed();
    }
}

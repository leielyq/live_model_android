package com.yiqi.yiqi_live.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yiqi.yiqi_live.R;
import com.yiqi.yiqi_live.activity.BaseActivity;
import com.yiqi.yiqi_live.util.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }


    @BindView(R.id.activity_search_list)
    ListView mActivitySearchList;
    @BindView(R.id.activity_search_view)
    SearchView mActivitySearchView;
    private String[] mSplit;
    private ArrayAdapter<String> mStringArrayAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        initData();

        mActivitySearchView.setSubmitButtonEnabled(true);
        mActivitySearchView.onActionViewExpanded();
        mActivitySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query)) {
                    String o = query + "," + SPUtil.get(SearchActivity.this, SearchActivity.this.getString(R.string.HistoryHeuristic), "");
                    SPUtil.putAndApply(SearchActivity.this, SearchActivity.this.getString(R.string.HistoryHeuristic), o);
                    initData();
                    Toast.makeText(SearchActivity.this, "开始搜索：" + query, Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mActivitySearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                Toast.makeText(SearchActivity.this, "开始搜索：" + textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initData() {
        String o = (String) SPUtil.get(this, this.getString(R.string.HistoryHeuristic), "");
        mSplit = null;
        mSplit = o.split(",");
        if (!TextUtils.isEmpty(mSplit[0])) {
            mStringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mSplit);
            mActivitySearchList.setAdapter(mStringArrayAdapter);
        }

    }

    @OnClick(R.id.activity_search_back)
    public void onViewClicked() {
        onBackPressed();
    }


}
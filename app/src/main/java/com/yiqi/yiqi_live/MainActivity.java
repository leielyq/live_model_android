package com.yiqi.yiqi_live;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.yiqi.yiqi_live.activity.BaseActivity;
import com.yiqi.yiqi_live.base.fragment.BaseFragment;
import com.yiqi.yiqi_live.chat.fragment.ChatFragment;
import com.yiqi.yiqi_live.index.fragment.IndexFragment;
import com.yiqi.yiqi_live.mine.fragment.MineFragment;
import com.yiqi.yiqi_live.rank.fragment.RankFragment;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private TextView mTextMessage;
    private int currentIndex = 0;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                        mTextMessage.setText(R.string.title_home);
                        showHideFragment(mBaseFragments[0], mBaseFragments[currentIndex]);
                        currentIndex = 0;
                        Log.d("MainActivity", "测试该点击次数");
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    showHideFragment(mBaseFragments[1], mBaseFragments[currentIndex]);
                    currentIndex = 1;
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    showHideFragment(mBaseFragments[2], mBaseFragments[currentIndex]);
                    currentIndex = 2;
                    return true;
                case R.id.navigation_mine:
                    mTextMessage.setText(R.string.title_notifications);
                    showHideFragment(mBaseFragments[3], mBaseFragments[currentIndex]);
                    currentIndex = 3;
                    return true;
            }
            return false;
        }

    };
    private BaseFragment[] mBaseFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            mBaseFragments = new BaseFragment[4];
            mBaseFragments[0] = new IndexFragment();
            mBaseFragments[1] = new RankFragment();
            mBaseFragments[2] = new ChatFragment();
            mBaseFragments[3] = new MineFragment();
            loadMultipleRootFragment(R.id.content, 0, mBaseFragments);
        }

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BNVHelper m = new BNVHelper();
        m.disableShiftMode(navigation);
    }

    public class BNVHelper {

        public void disableShiftMode(BottomNavigationView navigationView) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);

                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
                    itemView.setShiftingMode(false);
                    itemView.setChecked(itemView.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                // Log
            } catch (IllegalAccessException e) {
                // Log
            }
        }
    }

}

package com.me.extractgifapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.me.extractgifapp.R;
import com.me.extractgifapp.base.BaseActivity;
import com.me.extractgifapp.utils.TimeUtils;

public class MainActivity extends BaseActivity
		implements BottomNavigationView.OnNavigationItemSelectedListener{

    private RightTimeFragment rtFragment = new RightTimeFragment();
    private RightConditionFragment rcFragment = new RightConditionFragment();
    private RightObjectFragment roFragment = new RightObjectFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

	private void init() {
		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(this);
		/* 添加一个默认的 */
		getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,rtFragment).commit();
	}


	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		replaceContainerFragment(item);
		return true;
	}

	private void replaceContainerFragment(MenuItem item) {
		Fragment currentFragment = null;
		switch (item.getItemId()) {
			case R.id.navigation_home:
				currentFragment = rtFragment;
				break;
			case R.id.navigation_dashboard:
				currentFragment = rcFragment;
				break;
			case R.id.navigation_notifications:
				currentFragment = roFragment;
				break;
			default:
				break;
		}
		if (currentFragment !=null &&!currentFragment.isAdded()){
			getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,currentFragment).commit();
			System.out.println(getResources().getString(
					currentFragment instanceof RightTimeFragment?R.string.hint_t:
							currentFragment instanceof RightConditionFragment?R.string.hint_d:
									R.string.hint_r));
		}
	}


	@Override
	public void onBackPressed() {
		TimeUtils.setInterval(2000l, new TimeUtils.DoCallBack() {
			@Override
			public void outTimeTodo() {
				showToast("再按一次退出应用");
			}

			@Override
			public void inTimeTodo() {
				finish();
			}
		});
	}
}

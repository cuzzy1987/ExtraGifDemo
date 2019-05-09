package com.me.extractgifapp.ui;


import android.support.v4.app.Fragment;

import com.me.extractgifapp.R;
import com.me.extractgifapp.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RightTimeFragment extends BaseFragment {


	@Override
	public void setContentView() {
		setContentView(R.layout.fragment_right_time);
		System.out.println(" ====>  "+ " @override on");
	}

}

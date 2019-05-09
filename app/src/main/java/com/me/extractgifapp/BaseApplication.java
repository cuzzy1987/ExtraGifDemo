package com.me.extractgifapp;

import android.app.Application;

/**
 * Created by cs on 2019/5/9.
 */
public class BaseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		init();
	}

	private void init() {
		System.out.println("-=init=-");
	}
}

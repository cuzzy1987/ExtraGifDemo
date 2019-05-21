package com.me.extractgifapp.ui;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.me.extractgifapp.R;
import com.me.extractgifapp.base.BaseActivity;

public class TestActivity extends BaseActivity {

	private View view;
	private WindowManager windowManager;
	private WindowManager.LayoutParams params;
	private boolean isShowing = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_activity);

		init();
	}

	private void init() {
		initWindowManager();
		findViewById(R.id.hintTv).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				createFloatView();

				/*Intent intent = new Intent(mContext,ChatActivity.class);
				intent.putExtra("from","TestActivity");
				startActivity(intent);*/
			}
		});
	}

	private void createFloatView() {
		view = LayoutInflater.from(this).inflate(R.layout.layout_pop_notification,null);
		if (isShowing){
			windowManager.addView(view,params);
		}else {
			windowManager.updateViewLayout(view,params);
		}

		view.setOnTouchListener((v, event) -> {
			switch (event.getAction()){
				case MotionEvent.ACTION_DOWN:
					windowManager.removeViewImmediate(view);
					view = null;
					break;
				case MotionEvent.ACTION_MOVE:
					break;
			}
			return true;
		});
	}

	private void initWindowManager() {
		windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		params = new WindowManager.LayoutParams();
		params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
		params.format = PixelFormat.TRANSPARENT;
		params.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
				|WindowManager.LayoutParams.FLAG_FULLSCREEN
				|WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
		params.width = windowManager.getDefaultDisplay().getWidth();
		params.height = 200;
		params.gravity = Gravity.TOP;
	}


}

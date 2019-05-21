package com.me.extractgifapp.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.me.extractgifapp.R;
import com.me.extractgifapp.base.BaseActivity;

public class ChatActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		init();

	}

	private void init() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});
	}


	/*@Override
	public void onBackPressed() {
		String from = getIntent().getStringExtra("from");
		if (!TextUtils.isEmpty(from)){
			System.out.println(" from => "+from);
			finish();
			startActivity(new Intent(mContext,MainActivity.class));
		}else {
			super.onBackPressed();
		}
	}*/
}

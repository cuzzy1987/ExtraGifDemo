package com.me.extractgifapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by cs on 2019/5/9.
 */
public class BaseActivity extends AppCompatActivity {

	public Context mContext;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
	}

	public void  showToast(String msg){
		Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
	}

}

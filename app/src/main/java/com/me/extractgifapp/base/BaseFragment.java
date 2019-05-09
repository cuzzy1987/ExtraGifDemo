package com.me.extractgifapp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by cs on 2019/5/9.
 */
public abstract class BaseFragment extends Fragment {

	private LayoutInflater inflater;
	private ViewGroup container;
	private View contentView;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		this.inflater = inflater;
		this.container = container;
		if (contentView == null){
			setContentView();
		}else {
			ViewGroup parent = (ViewGroup) contentView.getParent();
			if (parent!=null){
				parent.removeView(contentView);
			}
		}
		return contentView;
	}

	public abstract void setContentView();

	public void setContentView(int resourceId){
		setContentView(inflater.inflate(resourceId,container,false));
	}

	private void setContentView(View contentView) {
		this.contentView = contentView;
	}

	public void  showToast(String msg){
		Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
	}
}

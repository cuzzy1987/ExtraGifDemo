package com.me.extractgifapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.me.extractgifapp.R;

import java.util.List;

/**
 * Created by cs on 2019/5/23.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

	private Context context;
	private List<Bitmap> list;
	private LayoutInflater inflater;

	public ListAdapter(Context context) {
		this.inflater = LayoutInflater.from(context);
		this.context = context;
	}

	public void setList(List<Bitmap> list) {
		this.list = list;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
		/* 这边写的不一样的话 显示的不一样 */
		/* 另一种写法 => 初始化过了的LayoutInflater  */
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bitmap_item_layout,viewGroup,false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
		System.out.println(" bitmap hash code=> " + list.get(position).hashCode());
		viewHolder.imageView.setImageBitmap(list.get(position));
	}

	@Override
	public int getItemCount() {
		return list == null?0:list.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder{
		public final ImageView imageView;
		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			imageView = itemView.findViewById(R.id.bitmapIv);
		}
	}
}

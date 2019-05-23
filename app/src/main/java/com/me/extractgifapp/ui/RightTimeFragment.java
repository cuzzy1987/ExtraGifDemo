package com.me.extractgifapp.ui;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.extractgifapp.R;
import com.me.extractgifapp.adapter.ListAdapter;
import com.me.extractgifapp.base.BaseFragment;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import wseemann.media.FFmpegMediaMetadataRetriever;

public class RightTimeFragment extends BaseFragment {

	private static final String TAG ="RightTimeFragment";
	private Button randomBtn;
	private TextView centerTv;
	private ImageView bitmapIv;
	private RecyclerView mRecyclerView;
	private ListAdapter mAdapter;

	@Override
	public void setContentView() {
		setContentView(R.layout.fragment_right_time);
		init();
		initRecyclerView();
	}

	private void init() {
		getContentView().findViewById(R.id.randomBtn).setOnClickListener(v->{
//				startActivity(new Intent(getContext(),TestActivity.class));
//			checkNotifyPermission();
//					checkInstallPermission();
//					openAlbum();
			splitVideo();
				}
		);
		mRecyclerView = getContentView().findViewById(R.id.recyclerView);
		centerTv = getContentView().findViewById(R.id.centerTv);
		bitmapIv = getContentView().findViewById(R.id.bitmapIv);
	}

	private void initRecyclerView() {
		mAdapter = new ListAdapter(this.getContext());
		LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRecyclerView.setLayoutManager(layoutManager);
		mRecyclerView.setAdapter(mAdapter);
	}

	/* /storage/emulated/0/Movies/20190522_172408362_adbd36cec29df4dddb22a9253db90e96.mp4 */
	private void splitVideo() {
		String path = "/storage/emulated/0/DCIM/Camera/VID_20190523_151632.mp4";
//		String path = "/storage/emulated/0/Movies/20190522_172408362_adbd36cec29df4dddb22a9253db90e96.mp4";
		FFmpegMediaMetadataRetriever fmr = new FFmpegMediaMetadataRetriever();
		fmr.setDataSource(path);
		/* 使用键名 抽取meta信息 */
//		fmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
//		fmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ARTIST);
		String durationStr = fmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_DURATION);
		MediaPlayer mediaPlayer = new MediaPlayer();

		/* 计算一下视频时长 用时长控制取帧 */
		List<Bitmap> list = new ArrayList<>();
		try {
			mediaPlayer.setDataSource(path);
			mediaPlayer.prepare();
			/* 如果是直播流 这个方法的返回值是-1 */
			int length = mediaPlayer.getDuration();
			System.out.println(String.format("duration is => %d ms",mediaPlayer.getDuration()));
			for (int i = 1; i < length; i = i+800) {
				/* 参数是微秒 */
//				list.add(BitmapUtils.correctBitmap(fmr.getFrameAtTime(i*1000,FFmpegMediaMetadataRetriever.OPTION_CLOSEST_SYNC)));
				list.add(fmr.getFrameAtTime(i*1000,FFmpegMediaMetadataRetriever.OPTION_CLOSEST_SYNC));
				System.out.println("-=length=- "+i);
			}

			mAdapter.setList(list);
			mAdapter.notifyDataSetChanged();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(" what's wrong with setDataSource=> "+e.getMessage());
		}
		fmr.release();
		/*MediaMetadataRetriever mmr = new MediaMetadataRetriever();
		try{
			FFmpegMediaMetadataRetriever fmr = new FFmpegMediaMetadataRetriever();
			fmr.setDataSource(path);
			fmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
			fmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ARTIST);
			Bitmap b = fmr.getFrameAtTime(2000000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST); // frame at 2 seconds
			byte [] artwork = fmr.getEmbeddedPicture();
			System.out.println(" b=> "+b.getRowBytes());
			fmr.release();
			bitmapIv.setImageBitmap(b);
		}catch (Exception e){
			System.out.println(" what's wrong => "+e.getMessage());
		}*/
	}


	private ArrayList mAlbumFiles = new ArrayList();
	private void openAlbum() {
		Album.album(this)
				.multipleChoice()
				.camera(true)
				.columnCount(2)
				.selectCount(6)
				.checkedList(mAlbumFiles)
//				.filterSize()
//				.filterMimeType()
//				.filterDuration()
//				.afterFilterVisibility() // Show the filtered files, but they are not available.
				.onResult(new Action<ArrayList<AlbumFile>>() {
					@Override
					public void onAction(@NonNull ArrayList<AlbumFile> result) {
						if (result.size()!=0)showFileInfo(result.get(0));
						else showToast("未选中文件");

					}
				})
				.onCancel(new Action<String>() {
					@Override
					public void onAction(@NonNull String result) {
					}
				})
				.start();
	}

	private void showFileInfo(AlbumFile albumFile) {
		try{
			centerTv.setText(String.format("mimeType: %s \n path: %s\n BucketName: %s\n",albumFile.getMimeType(),albumFile.getPath(),albumFile.getBucketName()));
			System.out.println("path==> "+albumFile.getPath());
			FFmpegMediaMetadataRetriever fmr = new FFmpegMediaMetadataRetriever();
			fmr.setDataSource(albumFile.getPath());
			fmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
			fmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ARTIST);
			Bitmap b = fmr.getFrameAtTime(2000000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST); // frame at 2 seconds
			byte [] artwork = fmr.getEmbeddedPicture();
			System.out.println("-=artWork.size()=- "+artwork.length);
			fmr.release();
		}catch (Exception e){
			System.out.println(" what's wrong => "+e.getMessage());
		}
	}


	private void checkInstallPermission(){
		AndPermission.with(getContext())
				.install()
				.file(new File("temp"))
				.rationale((context, data, executor) -> {
					showToast("没有安装权限 现在去授权");
					executor.execute();
				}).onGranted(data -> {showToast("用户同意");})
				.onDenied(data -> {showToast("用户拒绝");})
				.start();
	}
	private void checkNotifyPermission() {
		AndPermission.with(getContext())
				.notification()
				.permission()
				.rationale(new Rationale<Void>() {
					@Override
					public void showRationale(Context context, Void data, RequestExecutor executor) {
						showToast(" -=没有权限=- ");
					}
				})
				.onGranted(data -> {
					showToast(" -=获得了权限=- ");
				})
				.onDenied(data -> {showToast(" -=权限拒绝=- ");})
				.start();
	}
	private void showHangNotification() {
		NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
		Notification.Builder builder=new Notification.Builder(getContext());
		Intent intent =new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setClass(getActivity(),TestActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
		builder.setContentIntent(pendingIntent);
		builder.setSmallIcon(R.mipmap.ic_launcher);
		builder.setContentText("悬浮式通知");
		builder.setContentTitle("this app");
		builder.setAutoCancel(true);
		builder.setPriority(Notification.PRIORITY_MAX);//设置最高权限
		builder.setDefaults(Notification.DEFAULT_ALL);//设置声音和震动
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			System.out.println("packageName => "+getActivity().getPackageName());
			NotificationChannel channel = new NotificationChannel(
					getActivity().getPackageName(),
					TAG,
					NotificationManager.IMPORTANCE_DEFAULT

			);
			channel.setImportance(NotificationManager.IMPORTANCE_DEFAULT);
			mNotificationManager.createNotificationChannel(channel);
			builder.setChannelId("com.me.extractgifapp");
		}
		builder.setFullScreenIntent(pendingIntent,true);
		Notification notification = builder.build();
		mNotificationManager.notify(2,notification);
	}

}

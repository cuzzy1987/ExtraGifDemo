package com.me.extractgifapp.ui;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import com.me.extractgifapp.R;
import com.me.extractgifapp.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RightTimeFragment extends BaseFragment {

	private static final String TAG ="RightTimeFragment";
	private Button randomBtn;
	private TextView centerTv;

	@Override
	public void setContentView() {
		setContentView(R.layout.fragment_right_time);

		init();
	}

	private void init() {
		getContentView().findViewById(R.id.randomBtn).setOnClickListener(v->{
				startActivity(new Intent(getContext(),TestActivity.class));
//				showHangNotification();
			}
		);
	}



	/*private void showHangNotification() {
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
	}*/

}

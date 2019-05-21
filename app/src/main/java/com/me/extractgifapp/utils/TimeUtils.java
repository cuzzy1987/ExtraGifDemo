package com.me.extractgifapp.utils;

/**
 * Created by cs on 2019/5/13.
 */
public class TimeUtils {

	private static long[] timeQueue = new long[2];// doubleClick

	public interface DoCallBack {
		void outTimeTodo();//超过给定时间

		void inTimeTodo();//在时间间隔内
	}

	public static void setInterval(long gap, DoCallBack callBack) {
		timeQueue[timeQueue.length - 1] = System.currentTimeMillis();
		if ((timeQueue[timeQueue.length - 1] - timeQueue[0]) > gap) {
			callBack.outTimeTodo();
			System.arraycopy(timeQueue, 1, timeQueue, 0, timeQueue.length - 1);
		} else {
			callBack.inTimeTodo();
		}
	}

}

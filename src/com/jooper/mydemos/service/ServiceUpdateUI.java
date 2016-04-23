package com.jooper.mydemos.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.jooper.mydemos.MainActivity;

public class ServiceUpdateUI extends Service {
	private Timer timer;
	private TimerTask task;
	private int count;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		final Intent intent = new Intent();
		intent.setAction(MainActivity.ACTION_UPDATEUI);

		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				intent.putExtra("count", ++count);
				sendBroadcast(intent);
			}
		};
		timer.schedule(task, 1000, 1000);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		timer.cancel();
	}

}
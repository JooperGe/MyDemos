package com.jooper.mydemos.base;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;

import org.xutils.x;

import com.LibSharedPreferences.Preferences;

import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {

	private LinkedList<Activity> activityList = new LinkedList<Activity>();
	private static MyApplication instance;
	public static ExecutorService LIMITED_TASK_EXECUTOR;

	public Preferences pre;

	@Override
	public void onCreate() {

		instance = this;

		pre = new Preferences(this);

		// 初始化xUtils
		x.Ext.init(this);

	}

	public static synchronized MyApplication getInstance() {
		return instance;
	}

	public void addActivity(Activity activity) {

		activityList.add(activity);
	}

	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
	}
}
package com.jooper.mydemos.MyUtils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * 获取app相关信息
 * 
 * @author Jooper
 *
 */
public class GetAppInfo {
	/**
	 * 获取本app信息
	 * 
	 * @return pkName,versionName,versionCode
	 */
	public static String getAppInfo(Context context) {

		try {

			String pkName = context.getPackageName();

			String versionName = context.getPackageManager().getPackageInfo(
					pkName, 0).versionName;

			int versionCode = context.getPackageManager().getPackageInfo(
					pkName, 0).versionCode;

			return pkName + "," + versionName + "," + versionCode;

		} catch (Exception e) {

			return null;
		}
	}

	/**
	 * 获取全部app信息【没用到，所以没写明确的返回值】
	 * 
	 * @param context
	 */
	public static void getAllAppInfo(Context context) {

		PackageManager packageManager = context.getPackageManager();

		for (int i = 0; i < packageManager.getInstalledApplications(0).size(); i++) {

			Log.v("Jooper", ""
					+ packageManager.getInstalledApplications(0).get(i));
		}
	}
}

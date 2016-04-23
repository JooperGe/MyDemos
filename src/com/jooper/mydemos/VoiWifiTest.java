package com.jooper.mydemos;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jooper.mydemos.base.BaseAct;

/**
 * 音量、震动、Wifi 控制
 * 
 * 网络连接开关、GPS开关
 * 
 * @author Jooper
 *
 */
public class VoiWifiTest extends BaseAct implements OnClickListener {

	private Button btn_mute, btn_shake, btn_normal, btn_3G_open, btn_3G_close,
			btn_GPS_open, btn_GPS_close, btn_get_mobile_state, btn_mobile_open,
			btn_mobile_close;
	private TextView tv_cur_volum;

	public static final int RINGER_MODE_SILENT = 0;// 静音
	public static final int RINGER_MODE_VIBRATE = 1;// 震动
	public static final int RINGER_MODE_NORMAL = 2;// 正常

	private boolean gpsEnabled;

	AudioManager mAudioManager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_voi_wifi_control);

		checkPhoneNet();

		btn_mute = (Button) findViewById(R.id.btn_mute);
		btn_mute.setOnClickListener(this);
		btn_shake = (Button) findViewById(R.id.btn_shake);
		btn_shake.setOnClickListener(this);
		btn_normal = (Button) findViewById(R.id.btn_normal);
		btn_normal.setOnClickListener(this);
		btn_3G_open = (Button) findViewById(R.id.btn_3G_open);
		btn_3G_open.setOnClickListener(this);
		btn_3G_close = (Button) findViewById(R.id.btn_3G_close);
		btn_3G_close.setOnClickListener(this);
		btn_GPS_open = (Button) findViewById(R.id.btn_GPS_open);
		btn_GPS_open.setOnClickListener(this);
		btn_GPS_close = (Button) findViewById(R.id.btn_GPS_close);
		btn_GPS_close.setOnClickListener(this);

		btn_get_mobile_state = (Button) findViewById(R.id.btn_get_mobile_state);
		btn_get_mobile_state.setOnClickListener(this);
		btn_mobile_open = (Button) findViewById(R.id.btn_mobile_open);
		btn_mobile_open.setOnClickListener(this);
		btn_mobile_close = (Button) findViewById(R.id.btn_mobile_close);
		btn_mobile_close.setOnClickListener(this);

		tv_cur_volum = (TextView) findViewById(R.id.tv_cur_volum);

		mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		Log.d("Jooper", "当前Ringer模式：" + mAudioManager.getRingerMode());// 0：静音
																		// 1：震动
																		// 2：响铃
		tv_cur_volum.setText("系统最大音量："
				+ mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING)
				+ "\n" + "当前音量："
				+ mAudioManager.getStreamVolume(AudioManager.STREAM_RING));

		isGPSOpen();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_normal:

			// 不震动+响铃
			mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			mAudioManager.setStreamVolume(AudioManager.RINGER_MODE_NORMAL, 10,
					0);// 第二个参数是音量值 10几乎为一半

			mAudioManager.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER,
					AudioManager.VIBRATE_SETTING_OFF);
			break;
		case R.id.btn_mute:

			// 不震动+静音
			mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);

			break;
		case R.id.btn_shake:

			// 震动+静音

			// mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			// mAudioManager
			// .setStreamVolume(AudioManager.RINGER_MODE_NORMAL, 0, 0);//
			// 第二个参数是音量值,为0的时候表示震动

			mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);

			// ---------震动模式下的附加设置项-----------
			mAudioManager.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER,
					AudioManager.VIBRATE_SETTING_ON);
			mAudioManager.setVibrateSetting(
					AudioManager.VIBRATE_TYPE_NOTIFICATION,
					AudioManager.VIBRATE_SETTING_OFF);
			// ---------震动模式下的附加设置项-----------

			break;
		case R.id.btn_3G_open:

			// 3G网络
			// setMobileDataEnabled(true);
			// setMobileDataEnabled(mContext, true);
			toggleMobileData(mContext, true);

			break;
		case R.id.btn_3G_close:

			// 3G网络
			// setMobileDataEnabled(false);
			// setMobileDataEnabled(mContext, false);
			toggleMobileData(mContext, false);

			break;
		case R.id.btn_GPS_open:

			// toggleGPS();

			// Intent intent = new
			// Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			// startActivityForResult(intent, 0);

			turnGPSOn();

			break;
		case R.id.btn_GPS_close:

			toggleGPS();

			break;
		case R.id.btn_get_mobile_state:

			boolean mobileState = getMobileDataState(mContext, null);
			if (mobileState) {

				Toast.makeText(mContext, "网络开启", Toast.LENGTH_SHORT).show();
			} else {

				Toast.makeText(mContext, "网络关闭", Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.btn_mobile_open:

			// setMobileData(mContext, true);
			mToggleMobileData(mContext, true);

			break;
		case R.id.btn_mobile_close:

			// setMobileData(mContext, false);
			mToggleMobileData(mContext, false);

			break;
		}
	}

	/**
	 * 设置网络连接状态
	 * 
	 * @param isEnable
	 */
	public void setMobileDataEnabled(boolean isEnable) {
		ConnectivityManager cm = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// cm.stopUsingNetworkFeature(networkType, feature)
		/**
		 * GPRS网络开关 反射ConnectivityManager中hide的方法setMobileDataEnabled
		 * 可以开启和关闭GPRS网络
		 * 
		 * @param isEnable
		 * @throws Exception
		 */
		Class<?> cmClass = cm.getClass();
		Class<?>[] argClasses = new Class[1];
		argClasses[0] = boolean.class;

		// 反射ConnectivityManager中hide的方法setMobileDataEnabled，可以开启和关闭GPRS网络
		Method method;
		try {

			method = cmClass.getMethod("setMobileDataEnabled", argClasses);
			method.invoke(cm, isEnable);

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 移动网络开关
	 */
	private void setMobileDataEnabled(Context context, boolean enabled) {
		ConnectivityManager conMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		Class<?> conMgrClass = null; // ConnectivityManager类
		Field iConMgrField = null; // ConnectivityManager类中的字段
		Object iConMgr = null; // IConnectivityManager类的引用
		Class<?> iConMgrClass = null; // IConnectivityManager类
		Method setMobileDataEnabledMethod = null; // setMobileDataEnabled方法

		try {
			// 取得ConnectivityManager类
			conMgrClass = Class.forName(conMgr.getClass().getName());
			// 取得ConnectivityManager类中的对象mService
			iConMgrField = conMgrClass.getDeclaredField("mService");
			// 设置mService可访问
			iConMgrField.setAccessible(true);
			// 取得mService的实例化类IConnectivityManager
			iConMgr = iConMgrField.get(conMgr);
			// 取得IConnectivityManager类
			iConMgrClass = Class.forName(iConMgr.getClass().getName());
			// 取得IConnectivityManager类中的setMobileDataEnabled(boolean)方法
			setMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod(
					"setMobileDataEnabled", Boolean.TYPE);
			// 设置setMobileDataEnabled方法可访问
			setMobileDataEnabledMethod.setAccessible(true);
			// 调用setMobileDataEnabled方法
			setMobileDataEnabledMethod.invoke(iConMgr, enabled);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private void toggleMobileData(Context context, boolean enabled) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		Method setMobileDataEnabl;
		try {
			setMobileDataEnabl = connectivityManager.getClass()
					.getDeclaredMethod("setMobileDataEnabled", boolean.class);
			setMobileDataEnabl.invoke(connectivityManager, enabled);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void toggleGPS() {

		Intent gpsIntent = new Intent();

		gpsIntent.setClassName("com.android.settings",
				"com.android.settings.widget.SettingsAppWidgetProvider");

		gpsIntent.addCategory("android.intent.category.ALTERNATIVE");

		gpsIntent.setData(Uri.parse("custom:3"));

		try {

			PendingIntent.getBroadcast(this, 0, gpsIntent, 0).send();

		} catch (CanceledException e) {
			e.printStackTrace();
		}
	}

	// 用来判断gps是否打开
	private void isGPSOpen() {
		gpsEnabled = Settings.Secure.isLocationProviderEnabled(
				getContentResolver(), LocationManager.GPS_PROVIDER);
		if (gpsEnabled) {

			Toast.makeText(mContext, "gps state: true", 0).show();

		} else {

			Toast.makeText(mContext, "gps state: false", 0).show();

		}
	}

	private void turnGPSOn() {
		Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
		intent.putExtra("enabled", true);
		sendBroadcast(intent);

		String provider = Settings.Secure.getString(getContentResolver(),
				Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		if (!provider.contains("gps")) { // if gps is disabled
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings",
					"com.android.settings.widget.SettingsAppWidgetProvider");
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("3"));
			sendBroadcast(poke);
		}
	}

	/**
	 * 通过反射设置网络状态
	 */
	/**
	 * 设置手机的移动数据
	 */
	public static void setMobileData(Context pContext, boolean pBoolean) {

		ConnectivityManager connectivityManager = null;
		Class connectivityManagerClz = null;
		try {

			connectivityManager = (ConnectivityManager) mContext
					.getSystemService("connectivity");
			connectivityManagerClz = connectivityManager.getClass();
			Method method = connectivityManagerClz.getMethod(
					"setMobileDataEnabled", new Class[] { boolean.class });
			method.invoke(connectivityManager, pBoolean);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("移动数据设置错误: " + e.toString());
		}
	}

	public void mToggleMobileData(Context context, boolean state) {
		ConnectivityManager connectivityManager = null;
		try {
			connectivityManager = (ConnectivityManager) context
					.getSystemService("connectivity");
			Method method = connectivityManager.getClass().getMethod(
					"setMobileDataEnabled", new Class[] { boolean.class });
			method.invoke(connectivityManager, state);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回手机移动数据的状态
	 * 
	 * @param pContext
	 * @param arg
	 *            默认填null
	 * @return true 连接 false 未连接
	 */
	public static boolean getMobileDataState(Context pContext, Object[] arg) {

		try {

			ConnectivityManager mConnectivityManager = (ConnectivityManager) pContext
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			Class ownerClass = mConnectivityManager.getClass();

			Class[] argsClass = null;
			if (arg != null) {
				argsClass = new Class[1];
				argsClass[0] = arg.getClass();
			}

			Method method = ownerClass.getMethod("getMobileDataEnabled",
					argsClass);

			Boolean isOpen = (Boolean) method.invoke(mConnectivityManager, arg);

			return isOpen;

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("得到移动数据状态出错");
			return false;
		}
	}

	/**
	 * 监测SIM卡状态
	 * 
	 * @return
	 */
	private boolean checkPhoneNet() {

		TelephonyManager mTelephonyManager = (TelephonyManager) mContext
				.getSystemService(Service.TELEPHONY_SERVICE);
		if (mTelephonyManager.getSimState() != TelephonyManager.SIM_STATE_READY) // SIM卡没有就绪
		{
			Toast.makeText(mContext, "没有SIM卡", Toast.LENGTH_SHORT).show();
			return false;
		} else {
			ConnectivityManager cManager = (ConnectivityManager) mContext
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = cManager.getActiveNetworkInfo();
			if (info != null && info.isAvailable()) {
				// 能联网
				Toast.makeText(mContext, " 能联网", Toast.LENGTH_SHORT).show();
				return true;
			} else {
				// do something
				// 不能联网
				Toast.makeText(mContext, "不能联网", Toast.LENGTH_SHORT).show();
				return false;
			}
		}
	}
}

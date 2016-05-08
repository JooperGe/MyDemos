package com.jooper.mydemos;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jooper.mydemos.MyUtils.GetAppInfo;
import com.jooper.mydemos.MyUtils.HomeWatcher;
import com.jooper.mydemos.MyUtils.IntentUtils;
import com.jooper.mydemos.MyUtils.LogUtil;
import com.jooper.mydemos.base.BaseAct;
import com.jooper.mydemos.model.TestInfo;
import com.jooper.mydemos.tableshowview.MserServes;

public class MainActivity extends BaseAct implements OnClickListener {

	private Button button1, button2, button3, button4, button5, button6,
			button7, button8, button9, button10, button11, button12, button13,
			button14, button15, button16, button17, button18, button19,
			button20, button21, button22, button23, button24, button25,
			button26, button27, button28, button29, button30, button31,
			button32, button33, button34;

	// 更新UI的广播
	public static final String ACTION_UPDATEUI = "action.updateUI";
	UpdateUIBroadcastReceiver broadcastReceiver;

	private HomeWatcher mHomeWatcher = null;

	private TextView tv_wifi_state, tv_app_info, tv_cur_time;

	private Activity mAct;

	private WifiManager mWifiManager;

	private String[] mAppInfo = new String[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.mAct = MainActivity.this;

		tv_wifi_state = (TextView) findViewById(R.id.tv_wifi_state);
		tv_app_info = (TextView) findViewById(R.id.tv_app_info);
		tv_cur_time = (TextView) findViewById(R.id.tv_cur_time);

		// mHomeWatcher = new HomeWatcher(this);
		// mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
		// @Override
		// public void onHomePressed() {
		//
		// Toast.makeText(mContext, "pressed", 0).show();
		// }
		//
		// @Override
		// public void onHomeLongPressed() {
		//
		// Toast.makeText(mContext, "long pressed", 0).show();
		// }
		// });
		//
		// // 开启Home键监听
		// mHomeWatcher.startWatch();

		Toast.makeText(
				mContext,
				android.os.Build.VERSION.SDK + " - "
						+ android.os.Build.VERSION.RELEASE, 0).show();

		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd    hh:mm:ss");
		String dateStr = sDateFormat.format(new java.util.Date());
		tv_cur_time.setText("当前时间：" + dateStr);

		mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		tv_wifi_state.setText("wifi状态：" + mWifiManager.getWifiState());// 1:未打开
																		// 3：已打开
		String appInfo = GetAppInfo.getAppInfo(mContext);
		if (!TextUtils.isEmpty(appInfo)) {

			for (int i = 0; i < 3; i++) {

				mAppInfo[i] = GetAppInfo.getAppInfo(mContext).split(",")[i];
			}
			tv_app_info.setText("包名：" + mAppInfo[0] + "\n" + "版本名："
					+ mAppInfo[1] + "\n" + "版本号：" + mAppInfo[2]);
		}

		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);

		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);

		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(this);

		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(this);

		button5 = (Button) findViewById(R.id.button5);
		button5.setOnClickListener(this);

		button6 = (Button) findViewById(R.id.button6);
		button6.setOnClickListener(this);

		button7 = (Button) findViewById(R.id.button7);
		button7.setOnClickListener(this);

		button8 = (Button) findViewById(R.id.button8);
		button8.setOnClickListener(this);

		button9 = (Button) findViewById(R.id.button9);
		button9.setOnClickListener(this);

		button10 = (Button) findViewById(R.id.button10);
		button10.setOnClickListener(this);

		button11 = (Button) findViewById(R.id.button11);
		button11.setOnClickListener(this);

		button12 = (Button) findViewById(R.id.button12);
		button12.setOnClickListener(this);

		button13 = (Button) findViewById(R.id.button13);
		button13.setOnClickListener(this);

		button14 = (Button) findViewById(R.id.button14);
		button14.setOnClickListener(this);

		button15 = (Button) findViewById(R.id.button15);
		button15.setOnClickListener(this);

		button16 = (Button) findViewById(R.id.button16);
		button16.setOnClickListener(this);

		button17 = (Button) findViewById(R.id.button17);
		button17.setOnClickListener(this);

		button18 = (Button) findViewById(R.id.button18);
		button18.setOnClickListener(this);

		button19 = (Button) findViewById(R.id.button19);
		button19.setOnClickListener(this);

		button20 = (Button) findViewById(R.id.button20);
		button20.setOnClickListener(this);

		button21 = (Button) findViewById(R.id.button21);
		button21.setOnClickListener(this);

		button22 = (Button) findViewById(R.id.button22);
		button22.setOnClickListener(this);

		button23 = (Button) findViewById(R.id.button23);
		button23.setOnClickListener(this);

		button24 = (Button) findViewById(R.id.button24);
		button24.setOnClickListener(this);

		button25 = (Button) findViewById(R.id.button25);
		button25.setOnClickListener(this);

		button26 = (Button) findViewById(R.id.button26);
		button26.setOnClickListener(this);

		button27 = (Button) findViewById(R.id.button27);
		button27.setOnClickListener(this);

		button28 = (Button) findViewById(R.id.button28);
		button28.setOnClickListener(this);

		button29 = (Button) findViewById(R.id.button29);
		button29.setOnClickListener(this);

		button30 = (Button) findViewById(R.id.button30);
		button30.setOnClickListener(this);

		button31 = (Button) findViewById(R.id.button31);
		button31.setOnClickListener(this);

		button32 = (Button) findViewById(R.id.button32);
		button32.setOnClickListener(this);

		button33 = (Button) findViewById(R.id.button33);
		button33.setOnClickListener(this);

		button34 = (Button) findViewById(R.id.button34);
		button34.setOnClickListener(this);

		// TODO 集中注册点击事件方法

		// 注册广播
		// IntentFilter filter = new IntentFilter();
		// filter.addAction(ACTION_UPDATEUI);
		// broadcastReceiver = new UpdateUIBroadcastReceiver();
		// registerReceiver(broadcastReceiver, filter);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onResume() {
		super.onResume();

		tv_wifi_state.setText("wifi状态：" + mWifiManager.getWifiState());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// unregisterReceiver(broadcastReceiver);// 如果服务没有在运行则此行报错：服务没有注册
		stopService(new Intent(getApplicationContext(), MserServes.class));

		if (mHomeWatcher != null) {

			mHomeWatcher.stopWatch();
			mHomeWatcher = null;
		}
	}

	@Override
	protected void onStop() {

		super.onStop();
		LogUtil.j("onStop");
	}

	@Override
	protected void onPause() {
		super.onPause();
		LogUtil.j("onPause");
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		if (hasFocus) {

			LogUtil.j("hasFocus");
		} else {

			LogUtil.j("not hasFocus");
		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1:

			// IntentUtils.myStartActivity(mAct, ToastTest.class);
			HashMap<String, Integer> data1 = new HashMap<String, Integer>();
			data1.put("No1", 1);
			data1.put("No2", 2);

			IntentUtils.startActivity(mAct, ToastTest.class, data1);

			break;
		case R.id.button2:

			// IntentUtils.myStartActivity(mAct, TaobaoActivity.class);

			HashMap<String, Boolean> data2 = new HashMap<String, Boolean>();
			data2.put("s2", true);
			data2.put("s5", false);

			IntentUtils.startActivity(mAct, TaobaoActivity.class, data2);

			break;
		case R.id.button3:

			// IntentUtils.myStartActivity(mAct, AppinfoAct.class);

			HashMap<String, Object> data3 = new HashMap<String, Object>();

			TestInfo testInfo1 = new TestInfo();
			testInfo1.setAppIcon(12);
			testInfo1.setAppLabel("JLable");
			testInfo1.setPkgName("JName");

			TestInfo testInfo2 = new TestInfo();
			testInfo2.setAppIcon(21);
			testInfo2.setAppLabel("ALable");
			testInfo2.setPkgName("AName");

			data3.put("Jooper", testInfo1);
			data3.put("Aisling", testInfo2);

			data3.put("extra1", 5678);
			data3.put("extra2", "hello");

			IntentUtils.startActivity(mAct, AppinfoAct.class, data3);

			break;
		case R.id.button4:

			IntentUtils.startActivity(mAct, AppinfoTwoAct.class, null);

			break;
		case R.id.button5:

			IntentUtils.startActivity(mAct, MarketDownLoadAct.class, null);

			break;
		case R.id.button6:

			IntentUtils.startActivity(mAct, ActAnimationAct.class, null);

			overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);

			break;
		case R.id.button7:

			IntentUtils.startActivity(mAct, DataBaseAct.class, null);

			overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);

			break;
		case R.id.button8:// 如果设备连接的wifi网络是开启状态，但是与外界网络连接不通，则判断wifi是否可用会一直是

			IntentUtils.startActivity(mAct, CheckNetWork.class, null);

			overridePendingTransition(R.anim.zoom_out, R.anim.zoom_in);

			break;
		case R.id.button9:

			IntentUtils.startActivity(mAct, XUtilsDownLoad.class, null);

			overridePendingTransition(R.anim.zoom_out, R.anim.zoom_in);

			break;
		case R.id.button10:

			IntentUtils.startActivity(mAct, GetTextById.class, null);

			overridePendingTransition(R.anim.zoom_out, R.anim.zoom_in);

			break;
		case R.id.button11:

			IntentUtils.startActivity(mAct, BroadcastTest1.class, null);

			overridePendingTransition(R.anim.zoom_out, R.anim.zoom_in);

			break;
		case R.id.button12:

			// 发送广播
			Intent intent = new Intent();
			intent.setAction(ACTION_UPDATEUI);
			intent.putExtra("count", 110);
			MainActivity.this.sendBroadcast(intent);

			break;
		case R.id.button13:

			IntentUtils.startActivity(mAct, TableShow.class, null);

			break;
		case R.id.button14:

			IntentUtils.startActivity(mAct, GrouponIconTestAct.class, null);

			break;
		case R.id.button15:

			IntentUtils.startActivity(mAct, LVTest.class, null);

			break;
		case R.id.button16:

			IntentUtils.startActivity(mAct, ChartDynamicTest.class, null);

			break;
		case R.id.button17:

			IntentUtils.startActivity(mAct, CameraTest.class, null);

			break;
		case R.id.button18:

			IntentUtils.startActivity(mAct, MICTest.class, null);
		case R.id.button19:

			IntentUtils.startActivity(mAct, VoiWifiTest.class, null);

			break;
		case R.id.button20:

			IntentUtils.startActivity(mAct, XUtils3Test.class, null);

			break;
		case R.id.button21:

			IntentUtils.startActivity(mAct, DrawableTest.class, null);

			break;
		case R.id.button22:

			IntentUtils.startActivity(mAct, OkHttpTest.class, null);

			break;
		case R.id.button23:

			IntentUtils.startActivity(mAct, VideoTest.class, null);

			break;
		case R.id.button24:

			IntentUtils.startActivity(mAct, xUtilsPostAct.class, null);

			break;
		case R.id.button25:

			IntentUtils.startActivity(mAct, GetVersionByPkgNameAct.class, null);

			break;
		case R.id.button26:

			IntentUtils.startActivity(mAct, SocialShareAct.class, null);

			break;
		case R.id.button27:

			IntentUtils.startActivity(mAct, MemoryTestAct.class, null);

			break;
		case R.id.button28:

			IntentUtils.startActivity(mAct, AESAct.class, null);

			break;
		case R.id.button29:

			IntentUtils.startActivity(mAct, DoubleClickAct.class, null);

			break;
		case R.id.button30:

			IntentUtils.startActivity(mAct, RecyclerViewAct.class, null);

			break;
		case R.id.button31:

			IntentUtils.startActivity(mAct, HeartDynamicAct.class, null);

			break;
		case R.id.button32:

			IntentUtils.startActivity(mAct, PerfectDialogAct.class, null);

			break;
		case R.id.button33:

			IntentUtils.startActivity(mAct, AutoTextViewAct.class, null);

			break;
		case R.id.button34:

			IntentUtils.startActivity(mAct, HomeKeyAct.class, null);

			break;
		}
	}

	/**
	 * 定义广播接收器（内部类）
	 * 
	 * 
	 */
	private class UpdateUIBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			button11.setText("自定义广播更新UI ："
					+ String.valueOf(intent.getExtras().getInt("count")));
		}
	}
}

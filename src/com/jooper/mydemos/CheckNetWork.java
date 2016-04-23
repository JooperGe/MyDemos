package com.jooper.mydemos;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.jooper.mydemos.base.BaseAct;

/**
 * 判断网络状态-是否可用、是否有
 * 
 * @author Jooper
 *
 */
public class CheckNetWork extends BaseAct {

	private TextView tv_isnetworkconnected;
	private TextView tv_isWifiConnected;
	private TextView tv_isMobileConnected;
	private TextView tv_getConnectedType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_network_check);

		tv_isnetworkconnected = (TextView) findViewById(R.id.tv_isnetworkconnected);
		tv_isWifiConnected = (TextView) findViewById(R.id.isWifiConnected);
		tv_isMobileConnected = (TextView) findViewById(R.id.isMobileConnected);
		tv_getConnectedType = (TextView) findViewById(R.id.getConnectedType);

		boolean isNetworkConnected = isNetworkConnected(this);
		boolean isWifiConnected = isWifiConnected(this);
		boolean isMobileConnected = isMobileConnected(this);
		int getConnectedType = getConnectedType(this);

		tv_isnetworkconnected.setText("是否有网络链接：" + isNetworkConnected);
		tv_isWifiConnected.setText("wifi否有可用：" + isWifiConnected);
		tv_isMobileConnected.setText("MOBILE网络是否可用：" + isMobileConnected);
		tv_getConnectedType.setText("网络类型：" + getConnectedType);
	}

	public boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public static int getConnectedType(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
				return mNetworkInfo.getType();
			}
		}
		return -1;
	}
}

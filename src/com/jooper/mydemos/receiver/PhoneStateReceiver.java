package com.jooper.mydemos.receiver;

import com.jooper.mydemos.base.MyApplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class PhoneStateReceiver extends BroadcastReceiver {

	private static int lastCallState = TelephonyManager.CALL_STATE_IDLE;

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		String action = arg1.getAction();
		Log.d("PhoneStateReceiver", action);
		TelephonyManager telephonyManager = (TelephonyManager) arg0
				.getSystemService(Context.TELEPHONY_SERVICE);
		int currentCallState = telephonyManager.getCallState();
		Log.d("PhoneStateReceiver", "currentCallState=" + currentCallState);
		if (currentCallState == TelephonyManager.CALL_STATE_IDLE) {// 空闲
			// TODO
		} else if (currentCallState == TelephonyManager.CALL_STATE_RINGING) {// 响铃
			// TODO
		} else if (currentCallState == TelephonyManager.CALL_STATE_OFFHOOK) {// 接听
			// TODO
		}
		if (lastCallState == TelephonyManager.CALL_STATE_RINGING
				&& currentCallState == TelephonyManager.CALL_STATE_IDLE) {

			int oldData = MyApplication.getInstance().pre
					.getInt("missed_calls");
			MyApplication.getInstance().pre
					.saveInt("missed_calls", oldData + 1);
			Toast.makeText(
					arg0,
					"有未接来电："
							+ MyApplication.getInstance().pre
									.getInt("missed_calls"), Toast.LENGTH_LONG)
					.show();
		}

		lastCallState = currentCallState;

	}
}

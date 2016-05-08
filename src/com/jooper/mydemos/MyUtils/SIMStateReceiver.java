package com.jooper.mydemos.MyUtils;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * SIMCard状态监听
 * 
 * @author Jooper
 *
 */
public class SIMStateReceiver extends BroadcastReceiver {

	private final static String ACTION_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";
	private final static int SIM_VALID = 0;
	private final static int SIM_INVALID = 1;
	private int simState = SIM_INVALID;

	public int getSimState() {
		return simState;
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals(ACTION_SIM_STATE_CHANGED)) {

			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Service.TELEPHONY_SERVICE);
			int state = tm.getSimState();

			switch (state) {

			case TelephonyManager.SIM_STATE_READY:

				Toast.makeText(context, "sim卡已识别", 0).show();
				simState = SIM_VALID;
				break;
			case TelephonyManager.SIM_STATE_UNKNOWN:
			case TelephonyManager.SIM_STATE_ABSENT:
			case TelephonyManager.SIM_STATE_PIN_REQUIRED:
			case TelephonyManager.SIM_STATE_PUK_REQUIRED:
			case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
			default:

				Toast.makeText(context, "sim卡失效", 0).show();
				simState = SIM_INVALID;
				break;
			}
		}
	}
}

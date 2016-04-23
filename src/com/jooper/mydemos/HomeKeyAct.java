package com.jooper.mydemos;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.jooper.mydemos.base.BaseAct;

/**
 * Home键监听、禁用
 * 
 * @author Jooper
 *
 */
public class HomeKeyAct extends BaseAct {

	public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED,
				FLAG_HOMEKEY_DISPATCHED);

		setContentView(R.layout.act_home_key);
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {

		Toast.makeText(HomeKeyAct.this, "" + keyCode, Toast.LENGTH_LONG).show();
		if (keyCode == KeyEvent.KEYCODE_BACK) {// 监听并取代长按返回键
			return true;
		}
		return super.onKeyLongPress(keyCode, event);
	}
	// @Override
	// public boolean onKeyDown(int keyCode, KeyEvent event) {
	//
	// Log.d("Jooper", "" + keyCode);
	// Toast.makeText(HomeKeyAct.this, "" + keyCode, 0).show();
	//
	// // switch (keyCode) {
	// // case KeyEvent.KEYCODE_SOFT_LEFT:
	// //
	// // break;
	// //
	// // default:
	// // break;
	// // }
	// //
	// if (keyCode == KeyEvent.KEYCODE_MENU) {
	// return true;
	// } else if (keyCode == KeyEvent.KEYCODE_HOME) {
	// return true;
	// } else if (keyCode == KeyEvent.KEYCODE_BACK) {
	// return true;
	// }
	// return super.onKeyDown(keyCode, event);
	//
	// }
}

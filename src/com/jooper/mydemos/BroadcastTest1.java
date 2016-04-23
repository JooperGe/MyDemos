package com.jooper.mydemos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jooper.mydemos.base.BaseAct;

public class BroadcastTest1 extends BaseAct implements OnClickListener {

	private Button btn_go;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_broadcast_test);

		btn_go = (Button) findViewById(R.id.btn_go);
		btn_go.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_go:

			// 发送广播
			Intent intent = new Intent();
			intent.setAction(MainActivity.ACTION_UPDATEUI);
			intent.putExtra("count", 111);
			BroadcastTest1.this.sendBroadcast(intent);

			break;
		}
	}
}

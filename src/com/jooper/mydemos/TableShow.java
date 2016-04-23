package com.jooper.mydemos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jooper.mydemos.base.BaseAct;
import com.jooper.mydemos.tableshowview.MserServes;

/**
 * 打开悬浮窗
 * 
 * @author Jooper
 *
 */
public class TableShow extends BaseAct implements OnClickListener {

	private Button btn_start;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_tableshow_view);

		btn_start = (Button) findViewById(R.id.btn_start);
		btn_start.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_start:

			this.startService(new Intent(getApplicationContext(),
					MserServes.class));

//			finish();

			break;
		}
	}
}

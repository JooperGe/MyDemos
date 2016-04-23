package com.jooper.mydemos;

import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import com.jooper.mydemos.base.BaseAct;

/**
 * 麦克风测试
 * 
 * @author Jooper
 *
 */
public class MICTest extends BaseAct {

	private ImageView iv_test;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_mic_test);

		iv_test = (ImageView) findViewById(R.id.iv_test);

		String path = Environment.getExternalStorageDirectory() + "/DCIM/"
				+ "test.png";

	}
}

package com.jooper.mydemos;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jooper.mydemos.MyUtils.CopyOfHttpXUtils;
import com.jooper.mydemos.MyUtils.HttpXUtils;
import com.jooper.mydemos.MyUtils.JsonUtil;
import com.jooper.mydemos.MyUtils.OnHttpBack;
import com.jooper.mydemos.base.BaseAct;

/**
 * xUtils的post请求
 * 
 * @author Jooper
 *
 */
public class xUtilsPostAct extends BaseAct implements OnHttpBack {

	private TextView tv24_test;

	String mJsonContent = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_xutlis_post);

		tv24_test = (TextView) findViewById(R.id.tv24_test);

		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		//
		// CopyOfHttpXUtils.okHttpPost(null, null);
		// }
		// }).start();
	}

	public void doPost(View view) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", "8626bd2e-85f7-4bf3-9b68-04c435ba45da");
		mJsonContent = JsonUtil.toJson(map);
		new HttpXUtils(
				"http://192.168.1.109:15004/youlifeapp/app/user/cart/query/v2",
				mJsonContent, this, 1);
		// String result = HttpXUtils.httpClientPost(
		// "http://192.168.1.109:15004/youlifeapp/app/user/cart/query/v2",
		// mJsonContent);

		// tv24_test.setText(result);
	}

	@Override
	public void upUI(String str, int num) throws Exception {
		System.out.println("Threadname" + Thread.currentThread().getName()
				+ "--upUI" + str);
	}

	@Override
	public void loadDate(String str, int num) throws Exception {
		System.out.println("Threadname" + Thread.currentThread().getName()
				+ "--loadDate" + str);
	}
}

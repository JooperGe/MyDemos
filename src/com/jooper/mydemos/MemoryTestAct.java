package com.jooper.mydemos;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.jooper.mydemos.base.BaseAct;

/**
 * 内存测试
 * 
 * android:largeHeap="true"设置在Manifest文件中的application节点下
 * 
 * 注：在设置“android:largeHeap="true"”之前，点击测试按钮第二次的时候出现OOM
 * 在设置“android:largeHeap="true"”之后，点击测试按钮第六次的时候出现OOM
 * 在设置前和设置后，监测出的当前内存和最大内存都分别是192M和512M【华为P6】
 * 
 * @author Jooper
 *
 */
public class MemoryTestAct extends BaseAct implements OnClickListener {

	private TextView tv27_cur_memory, tv27_max_memory;
	private Button btn27_check, btn27_test;

	List<byte[]> mLeakyContainer = new ArrayList<byte[]>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_memory_test);

		tv27_cur_memory = (TextView) findViewById(R.id.tv27_cur_memory);
		tv27_max_memory = (TextView) findViewById(R.id.tv27_max_memory);

		btn27_check = (Button) findViewById(R.id.btn27_check);
		btn27_check.setOnClickListener(this);
		btn27_test = (Button) findViewById(R.id.btn27_test);
		btn27_test.setOnClickListener(this);

	}

	/**
	 * 内存检测
	 */
	@SuppressLint("NewApi")
	private void checkMemory() {

		ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

		int largeMemoryClass = activityManager.getLargeMemoryClass();
		int memoryClass = activityManager.getMemoryClass();

		ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
		activityManager.getMemoryInfo(info);

		Log.d("Jooper", "largeMemoryClass = " + largeMemoryClass);
		Log.d("Jooper", "memoryClass = " + memoryClass);

		tv27_cur_memory.setText("当前内存：" + memoryClass);
		tv27_max_memory.setText("最大内存：" + largeMemoryClass);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn27_check:

			checkMemory();

			break;
		case R.id.btn27_test:

			// 每次创建100M的内存对象
			byte[] b = new byte[100 * 1000 * 1000];
			mLeakyContainer.add(b);

			checkMemory();

			break;
		}
	}
}

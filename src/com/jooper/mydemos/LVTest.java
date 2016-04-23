package com.jooper.mydemos;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.ListView;

import com.jooper.mydemos.adapter.TestItemAdapter;
import com.jooper.mydemos.base.BaseAct;

/**
 * 测试：ListView中变更item数据
 * 
 * @author Jooper
 *
 */
public class LVTest extends BaseAct {

	private ListView lv_test_item;
	private List<String> datas = new ArrayList<String>();
	private TestItemAdapter mTestItemAdapter;
	public static LVTest mLVTest;

	public static LVTest getInstance() {

		return mLVTest;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_lv_item_test);

		mLVTest = this;

		lv_test_item = (ListView) findViewById(R.id.lv_test_item);

		for (int i = 0; i < 100; i++) {

			datas.add(i + "");
		}

		mTestItemAdapter = new TestItemAdapter(LVTest.this, datas);
		lv_test_item.setAdapter(mTestItemAdapter);
	}

	public void udpate(int location) {

		// datas.set(location, datas.get(location) + " - " + location);

		mTestItemAdapter.notifyDataSetChanged();
	}
}

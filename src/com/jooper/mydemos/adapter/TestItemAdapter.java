package com.jooper.mydemos.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jooper.mydemos.LVTest;
import com.jooper.mydemos.R;

/**
 * 测试ListView中item数据变化的adapter
 * 
 * @author Jooper
 *
 */
public class TestItemAdapter extends BaseAdapter {

	private List<String> mData = null;
	private Context mContext;

	LayoutInflater infater = null;
	private ViewHolder holder;

	public TestItemAdapter(Context context, List<String> data) {
		infater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mData = data;
		this.mContext = context;
	}

	@Override
	public int getCount() {

		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertview, ViewGroup arg2) {

		View view = null;
		holder = null;
		if (convertview == null || convertview.getTag() == null) {
			view = infater.inflate(R.layout.activity_lv_item_test_item, null);
			holder = new ViewHolder(view);

			view.setTag(holder);
		} else {
			view = convertview;
			holder = (ViewHolder) convertview.getTag();
		}
		final String data1 = (String) getItem(position);
		holder.tv_test_item.setText(data1);
		holder.btn_item_test.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				mData.remove(position);
				LVTest.getInstance().udpate(position);
			}
		});
		return view;
	}

	class ViewHolder {
		TextView tv_test_item;
		Button btn_item_test;

		public ViewHolder(View view) {
			this.tv_test_item = (TextView) view.findViewById(R.id.tv_test_item);
			this.btn_item_test = (Button) view.findViewById(R.id.btn_item_test);
		}
	}
}
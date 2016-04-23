package com.jooper.mydemos;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jooper.mydemos.base.BaseAct;
import com.jooper.mydemos.view.RunningTextView;

/**
 * 自动增加/减少的TextView测试
 * 
 * @author Jooper
 *
 */
public class AutoTextViewAct extends BaseAct implements OnClickListener {

	private Button mBtrmb;
	private Button mBtdollar;
	private Button mBturo;
	private RunningTextView mRunningtextview;
	private EditText mEdit;
	private Button mBtplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_auto_textview);
		bindViews();
		// 设置数字格式，具体查DecimalFormat类的api
		// 00.00保留两位小数
		mRunningtextview.setFormat("00");
		mBtplay.setOnClickListener(this);
		mBtrmb.setOnClickListener(this);
		mBtdollar.setOnClickListener(this);
		mBturo.setOnClickListener(this);
	}

	private void bindViews() {

		mBtrmb = (Button) findViewById(R.id.btrmb);
		mBtdollar = (Button) findViewById(R.id.btdollar);
		mBturo = (Button) findViewById(R.id.bturo);
		mRunningtextview = (RunningTextView) findViewById(R.id.runningtextview);
		mEdit = (EditText) findViewById(R.id.edit);
		mBtplay = (Button) findViewById(R.id.btplay);
	}

	private Handler mHandler = new Handler() {

		public void handleMessage(Message msg) {

			switch (msg.what) {
			case 1:

				Toast.makeText(mContext, "2s", 0).show();
				break;
			}
		};
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btplay:
			// 播放数字动画
			String temp = mEdit.getText().toString();
			Log.v("temp:", temp);
			if (!temp.equals("")) {
				double number = Double.parseDouble(temp);

				mRunningtextview.setFrames(300);
				mRunningtextview.playNumber(100, number);
				mHandler.sendEmptyMessageDelayed(1, 2000);
			}
			break;

		case R.id.btrmb:
			Toast.makeText(this, "use the symbol ￥ ", Toast.LENGTH_SHORT)
					.show();
			mRunningtextview.setFormat("￥00.00");
			break;
		case R.id.bturo:
			Toast.makeText(this, "use the symbol € ", Toast.LENGTH_SHORT)
					.show();
			mRunningtextview.setFormat("€00.00");
			break;
		case R.id.btdollar:
			Toast.makeText(this, "use the symbol $ ", Toast.LENGTH_SHORT)
					.show();
			mRunningtextview.setFormat("$00.00");
			break;
		}

	}
}

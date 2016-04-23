package com.jooper.mydemos;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.jooper.mydemos.base.BaseAct;

/**
 * 单双击测试
 * 
 * @author Jooper
 *
 */
public class DoubleClickAct extends BaseAct {

	private Button btn_test_doubleclick;

	private long mCurentTime;

	private boolean isClicked;

	// 这里500ms是区分单击和双击的标准
	private int timeSpac = 300;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_doubleclick);

		btn_test_doubleclick = (Button) findViewById(R.id.btn_test_doubleclick);
		btn_test_doubleclick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// 双击事件
				if (System.currentTimeMillis() - mCurentTime < timeSpac) {
					isClicked = false;
					Toast.makeText(mContext, "双击", Toast.LENGTH_LONG).show();
				} else {
					isClicked = true;
					Message message = new Message();
					message.what = 1;
					message.arg1 = 10;
					handler.sendMessageDelayed(message, timeSpac);
				}
				mCurentTime = System.currentTimeMillis();
			}
		});
	}

	Handler handler = new Handler(new Callback() {
		@Override
		public boolean handleMessage(Message arg0) {

			if (arg0.what == 1 && isClicked) {

				Toast.makeText(mContext, "单击", Toast.LENGTH_SHORT).show();
			}
			return false;
		}
	});
}

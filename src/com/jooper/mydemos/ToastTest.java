package com.jooper.mydemos;

import com.jooper.mydemos.MyUtils.LogUtil;
import com.jooper.mydemos.base.BaseAct;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * �Զ���Toast
 * 
 * @author Jooper
 *
 */
public class ToastTest extends BaseAct implements OnClickListener {

	private Button button1, button2, button3, button4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toast_test);

		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);

		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);

		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(this);

		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(this);

		int a = getIntent().getExtras().getInt("No1");
		int b = getIntent().getExtras().getInt("No2");

		LogUtil.j("No1 " + a);
		LogUtil.j("No2 " + b);

	}

	Toast mToast2 = null;
	Toast mToast3;

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1:

			Toast.makeText(ToastTest.this, "��ͳ��ʽ", Toast.LENGTH_SHORT).show();
			break;
		case R.id.button2:

			if (mToast3 != null) {

				mToast3.cancel();// 关闭已有Toast，避免多个Toast显示时间过长
			}
			mToast2 = Toast.makeText(ToastTest.this, "λ�þ�����ʽ",
					Toast.LENGTH_SHORT);

			mToast2.setGravity(Gravity.CENTER, 0, 0);

			mToast2.show();

			break;
		case R.id.button3:

			mToast3 = Toast.makeText(ToastTest.this, "��ͼ��ʽ",
					Toast.LENGTH_SHORT);

			mToast3.setGravity(Gravity.CENTER, 0, 0);

			LinearLayout toastView = (LinearLayout) mToast3.getView();

			ImageView toastImage = new ImageView(ToastTest.this);

			toastImage.setImageResource(R.drawable.ic_launcher);

			toastView.addView(toastImage, 0);

			mToast3.show();

			break;
		case R.id.button4:

			Toast mToast4;

			LayoutInflater inflater = getLayoutInflater();

			View layout = inflater.inflate(R.layout.custom_toast, null);

			ImageView image = (ImageView) layout.findViewById(R.id.iv_custom);
			image.setImageResource(R.drawable.ic_launcher);

			TextView title = (TextView) layout
					.findViewById(R.id.tv_custom_title);
			title.setText("Attention");
			TextView text = (TextView) layout.findViewById(R.id.tv_custom);
			text.setText("��ȫ�Զ����Toast");

			mToast4 = new Toast(getApplicationContext());
			mToast4.setGravity(Gravity.RIGHT | Gravity.TOP, 12, 40);
			mToast4.setDuration(Toast.LENGTH_SHORT);
			mToast4.setView(layout);
			mToast4.show();

			break;
		}
	}
}

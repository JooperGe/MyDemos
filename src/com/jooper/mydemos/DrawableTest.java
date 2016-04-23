package com.jooper.mydemos;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jooper.mydemos.base.BaseAct;

/**
 * 文字的drawable属性设置
 * 
 * @author Jooper
 *
 */
public class DrawableTest extends BaseAct {

	private TextView tv21_test;
	private ImageView iv21_test;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_drawable);

		tv21_test = (TextView) findViewById(R.id.tv21_test);
		iv21_test = (ImageView) findViewById(R.id.iv21_test);

		Drawable drawable = getResources().getDrawable(
				R.drawable.composer_place);
		// drawable.setBounds(left, top, right, bottom);left：距左边距离 top距上边距离
		// right、bottom：宽
		drawable.setBounds(10, 30, 200, 100);

		tv21_test.setCompoundDrawables(drawable, null, null, null);

		iv21_test.setBackgroundResource(R.drawable.path2_default_homebg);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		finish();
	}
}

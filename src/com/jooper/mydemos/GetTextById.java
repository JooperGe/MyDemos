package com.jooper.mydemos;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;
import android.widget.Toast;

import com.jooper.mydemos.MyUtils.LogUtil;
import com.jooper.mydemos.base.BaseAct;

public class GetTextById extends BaseAct {

	private TextView tv_test;

	private Layout mLayout;

	private boolean isChecked = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_gettext_by_line);
		tv_test = (TextView) findViewById(R.id.tv_test);

	}

	@Override
	protected void onResume() {
		super.onResume();
		getTextByLine();
	}

	public void getTextByLine() {

		// try {

		getMyLayout();

		// Layout layout = tv_test.getLayout();
		// layout.draw(canvas);
		// int line = tv_test.getLayout().getLineCount();
		// int line = mLayout.getLineCount();
		// String result = "";
		// String text = mLayout.getText().toString();
		// for (int i = 0; i < line - 1; i++) {
		// int start = mLayout.getLineStart(i);
		// int end = mLayout.getLineEnd(i);
		// result += text.substring(start, end) + "\\n";
		// }
		// int start = mLayout.getLineStart(line - 1);
		// int end = mLayout.getLineEnd(line - 1);
		// result += text.substring(start, end);
		// System.out.println("result" + result);
		// } catch (Exception e) {
		//
		// LogUtil.j(e.toString());
		// }
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();

	}

	public void getMyLayout() {

		ViewTreeObserver vto = tv_test.getViewTreeObserver();

		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				mLayout = tv_test.getLayout();

				if (mLayout != null && !isChecked) {

					getLineText();

					isChecked = true;
				}
			}
		});
	}

	public void getLineText() {
		try {

			int line = mLayout.getLineCount();
			String result = "";
			String text = mLayout.getText().toString();
			for (int i = 0; i < line; i++) {
				int start = mLayout.getLineStart(i);
				int end = mLayout.getLineEnd(i);
				result += text.substring(start, end) + "\\n";

				LogUtil.j(text.substring(start, end));
				Toast.makeText(GetTextById.this, text.substring(start, end), 0)
						.show();
			}
			int start = mLayout.getLineStart(line - 1);
			int end = mLayout.getLineEnd(line - 1);
			result += text.substring(start, end);
			System.out.println("result" + result);

			tv_test.setVisibility(View.VISIBLE);

		} catch (Exception e) {

			LogUtil.j(e.toString());
		}
	}
}

package com.jooper.mydemos;

import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;

import com.jooper.mydemos.base.BaseAct;

/**
 * 测试团购图标(相对位置)
 * 
 * @author A1
 *
 */
public class GrouponIconTestAct extends BaseAct {

	private TextView good_detail_tv_name, good_detail_tv_name_last,
			good_detail_tv_type, good_detail_tv_type_one,
			good_detail_tv_name_test, good_detail_tv_name1,
			good_detail_tv_type_two;
	// private TextView good_detail_tv_type_inner;

	// String good_name =
	// "一二 三四五六七八九十在想才不你一二三四五六七 八九十在想才不你一二三四五六七 八九十在想才不你一二三四五六七 八九十在想才不你";
	// String good_name = "：三堂合365   三合堂：sht    e享寿：exs】";
	String good_name = "【会员价】章鱼红酒 赤霞珠红酒章鱼会员价【会员价】章鱼红酒 赤霞珠红酒章鱼会员价";

	// 吗了看就好个方的是啊

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groupon_icon_test2);

		initView();
	}

	private void initView() {

		good_detail_tv_name = (TextView) findViewById(R.id.good_detail_tv_name);
		good_detail_tv_name_test = (TextView) findViewById(R.id.good_detail_tv_name_test);
		good_detail_tv_type_one = (TextView) findViewById(R.id.good_detail_tv_type_one);
		good_detail_tv_name1 = (TextView) findViewById(R.id.good_detail_tv_name1);
		good_detail_tv_type_two = (TextView) findViewById(R.id.good_detail_tv_type_two);
		// good_detail_tv_name_last = (TextView)
		// findViewById(R.id.good_detail_tv_name_last);
		// good_detail_tv_type = (TextView)
		// findViewById(R.id.good_detail_tv_type);
		// good_detail_tv_type_one = (TextView)
		// findViewById(R.id.good_detail_tv_type_one);

		initData();
	}

	private void initData() {

		calculate();

		good_detail_tv_name_test.setText(good_name);
		good_detail_tv_name_test.setTextSize(22);

		// good_detail_tv_name.setText(good_name);
		// good_detail_tv_name.setVisibility(View.VISIBLE);

		// getMyLayout();

	}

	private Layout mLayout;

	private boolean isChecked = false;

	public void getMyLayout() {

		ViewTreeObserver vto = good_detail_tv_name.getViewTreeObserver();

		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				mLayout = good_detail_tv_name.getLayout();

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
			String text = mLayout.getText().toString();
			String result = "";

			if (line > 1) {// 多行

				int start = mLayout.getLineStart(line - 1);
				int end = mLayout.getLineEnd(line - 1);
				// text.substring(start, end);

				good_detail_tv_name_last.setText(text.substring(start, end));

				good_detail_tv_name.setLines(line - 1);
				good_detail_tv_name.setText(good_name);

				good_detail_tv_name.setVisibility(View.VISIBLE);
				good_detail_tv_name_last.setVisibility(View.VISIBLE);
				good_detail_tv_type.setVisibility(View.VISIBLE);

			} else {// 单行

				good_detail_tv_name.setVisibility(View.VISIBLE);
				good_detail_tv_type_one.setVisibility(View.VISIBLE);

				// good_detail_tv_type_inner.setVisibility(View.VISIBLE);
			}

		} catch (Exception e) {

			System.out.println("异常了：" + e.toString());
		}
	}

	public void calculate() {

		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int displayWidth = displayMetrics.widthPixels;
		int displayHeight = displayMetrics.heightPixels;
		String str1 = good_name.replaceAll(" ", "  ");
		int strlength = good_name.length();

		int count = (int) ((displayWidth - 60) / (good_detail_tv_name
				.getTextSize() - 1));
		int yushu = (strlength % count) > 0 ? 1 : 0;
		int hangshu = (strlength / count);
		int zonghangshu = (strlength / count) + yushu;
		String textStr = good_name.substring(0, count * hangshu);
		String textEnd = good_name.substring(count * hangshu,
				good_name.length());

		String textStr1 = "";
		String textEnd1 = "";

		// if (zonghangshu == 1) {
		if (strlength < count) {

			int extraNum = count - strlength;

			if (extraNum <= 2) {// 如果单行剩余文字个数小于4个，则多余的字另起一行

				textStr1 = textEnd.substring(0, count - 4);
				textEnd1 = textEnd.substring(count - 4);

				textStr = textStr1;
				textEnd = textEnd1;

				good_detail_tv_name.setText(textStr);
				good_detail_tv_name1.setText(textEnd);
				good_detail_tv_name.setTextSize(22);
				good_detail_tv_name1.setTextSize(22);
				good_detail_tv_name.setVisibility(View.VISIBLE);
				good_detail_tv_name1.setVisibility(View.VISIBLE);
				good_detail_tv_type_two.setVisibility(View.VISIBLE);

			} else {

				good_detail_tv_name.setText(textEnd);
				good_detail_tv_name.setTextSize(22);
				good_detail_tv_name.setVisibility(View.VISIBLE);
				good_detail_tv_type_one.setVisibility(View.VISIBLE);
			}
		} else {

			good_detail_tv_name.setText(textStr);
			good_detail_tv_name1.setText(textEnd);
			good_detail_tv_name.setTextSize(22);
			good_detail_tv_name1.setTextSize(22);
			good_detail_tv_name.setVisibility(View.VISIBLE);
			good_detail_tv_name1.setVisibility(View.VISIBLE);
			good_detail_tv_type_two.setVisibility(View.VISIBLE);

		}

		// LinearLayout layout = new LinearLayout(mContext);
		// layout.setOrientation(LinearLayout.VERTICAL);
		//
		// // good_detail_tv_name.setLines(zonghangshu - 1);
		// // good_detail_tv_name.setText(good_name);
		// // good_detail_tv_name.setVisibility(View.VISIBLE);
		//
		// TextView textView11 = new TextView(mContext);
		// textView11.setTextSize(22);
		// textView11.setText(textStr);
		// layout.addView(textView11);
		// LinearLayout layoutBottom = new LinearLayout(mContext);
		// layoutBottom.setOrientation(LinearLayout.HORIZONTAL);
		// TextView textView22 = new TextView(mContext);
		// textView22.setTextSize(22);
		// textView22.setText(textEnd);
		// // TextView textView33 = new TextView(mContext);
		// // textView33.setTextSize(22);
		// // textView33.setText("哈哈");
		// // textView33.setBackgroundResource(R.drawable.ic_launcher);
		// // layoutBottom.addView(textView22);
		// // layoutBottom.addView(textView33);
		// // layout.addView(layoutBottom);
		// good_detail_tv_type_one.setVisibility(View.VISIBLE);
		// LinearLayout count_lay = (LinearLayout)
		// findViewById(R.id.ll_good_name);
		// count_lay.addView(layout);
	}
}

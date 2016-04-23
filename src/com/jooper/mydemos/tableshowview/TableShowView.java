package com.jooper.mydemos.tableshowview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.jooper.mydemos.R;
import com.jooper.mydemos.TaoBaoRelative.ComposerLayout;

public class TableShowView extends View {
	// 如果是想显示歌词则继承TextView并复写ondraw方法。
	// 开启一个线程不断的调用ondraw方法去更改你所写的继承自TextView的内容
	// 这里随便写了个集成自view的= =这个不是重点

	Context c;
	WindowManager mWM; // WindowManager
	WindowManager.LayoutParams mWMParams; // WindowManager参数
	View win;
	int tag = 0;
	int oldOffsetX;
	int oldOffsetY;

	private ComposerLayout tb_show;

	public TableShowView(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		c = context;
	}

	public void fun() {
		// 设置载入view WindowManager参数
		mWM = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
		win = LayoutInflater.from(c).inflate(
				R.layout.activity_imitate_widget_ctrl_window, null);
		win.setBackgroundColor(Color.TRANSPARENT);
		// 这里是随便载入的一个布局文件

//		tb_show = (ComposerLayout) win.findViewById(R.id.tb_show);

//		initView();
//		setListener();

		win.setOnTouchListener(new OnTouchListener() {
			// 触屏监听
			float lastX, lastY;

			public boolean onTouch(View v, MotionEvent event) {
				final int action = event.getAction();

				float x = event.getX();
				float y = event.getY();

				if (tag == 0) {
					oldOffsetX = mWMParams.x; // 偏移量
					oldOffsetY = mWMParams.y; // 偏移量
				}

				if (action == MotionEvent.ACTION_DOWN) {
					lastX = x;
					lastY = y;

				} else if (action == MotionEvent.ACTION_MOVE) {
					mWMParams.x += (int) (x - lastX); // 偏移量
					mWMParams.y += (int) (y - lastY); // 偏移量

					tag = 1;
					mWM.updateViewLayout(win, mWMParams);
				}

				else if (action == MotionEvent.ACTION_UP) {
					int newOffsetX = mWMParams.x;
					int newOffsetY = mWMParams.y;
					if (oldOffsetX == newOffsetX && oldOffsetY == newOffsetY) {

						// Toast.makeText(c, "你点到我了……疼！！！！", 1).show();
						//
						// initView();
						// setListener();

					} else {
						tag = 0;
					}
				}
				return true;
			}
		});

		WindowManager wm = mWM;
		WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
		mWMParams = wmParams;
		wmParams.type = 2002; // type是关键，这里的2002表示系统级窗口，你也可以试试2003。
		wmParams.flags = 40;// 这句设置桌面可控

		wmParams.width = 50;
		wmParams.height = 50;
		wmParams.format = -3; // 透明

		wm.addView(win, wmParams);// 这句是重点 给WindowManager中丢入刚才设置的值
									// 只有addview后才能显示到页面上去。
		// 注册到WindowManager win是要刚才随便载入的layout，wmParams是刚才设置的WindowManager参数集
		// 效果是将win注册到WindowManager中并且它的参数是wmParams中设置饿

	}

	public void initView() {
		// ���ÿؼ�
		tb_show.init(new int[] { R.drawable.composer_camera,
				R.drawable.composer_music, R.drawable.composer_place,
				R.drawable.composer_sleep, R.drawable.composer_thought,
				R.drawable.composer_with }, R.drawable.composer_button,
				R.drawable.composer_icn_plus, ComposerLayout.RIGHTCENTER, 180,
				300);

	}

	public void setListener() {
		// ����¼�������100+0��Ӧcomposer_camera��100+1��Ӧcomposer_music��������������л�����ť�ͼӼ�����ť���С�
		OnClickListener clickit = new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v.getId() == 100 + 0) {
					Toast.makeText(c, "�����...", 0).show();
				} else if (v.getId() == 100 + 1) {
					Toast.makeText(c, "������...", 0).show();
				} else if (v.getId() == 100 + 2) {
					Toast.makeText(c, "�򿪵���λ��...", 0).show();
				} else if (v.getId() == 100 + 3) {
					Toast.makeText(c, "��˯��ģʽ...", 0).show();
				} else if (v.getId() == 100 + 4) {
					Toast.makeText(c, "�򿪶Ի�ģʽ...", 0).show();
				} else if (v.getId() == 100 + 5) {
					Toast.makeText(c, "������..", 0).show();
				}
			}
		};
		tb_show.setButtonsOnClickListener(clickit);
	}
}

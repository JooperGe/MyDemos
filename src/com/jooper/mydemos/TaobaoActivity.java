package com.jooper.mydemos;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.jooper.mydemos.R;
import com.jooper.mydemos.MyUtils.LogUtil;
import com.jooper.mydemos.TaoBaoRelative.ComposerLayout;
import com.jooper.mydemos.base.BaseAct;

/**
 * �߷��Ա���ť��ҳ��
 * 
 * @author
 *
 */
public class TaobaoActivity extends BaseAct {

	private ComposerLayout clayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imitate_taobao);
		initView();
		setListener();

		boolean a = getIntent().getExtras().getBoolean("s2");
		boolean b = getIntent().getExtras().getBoolean("s5");

		LogUtil.j("s2 " + a);
		LogUtil.j("s5 " + b);
	}

	public void initView() {
		// ���ÿؼ�
		clayout = (ComposerLayout) findViewById(R.id.test);
		clayout.init(new int[] { R.drawable.composer_camera,
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
					Toast.makeText(TaobaoActivity.this, "�����...", 0).show();
				} else if (v.getId() == 100 + 1) {
					Toast.makeText(TaobaoActivity.this, "������...", 0).show();
				} else if (v.getId() == 100 + 2) {
					Toast.makeText(TaobaoActivity.this, "�򿪵���λ��...", 0)
							.show();
				} else if (v.getId() == 100 + 3) {
					Toast.makeText(TaobaoActivity.this, "��˯��ģʽ...", 0).show();
				} else if (v.getId() == 100 + 4) {
					Toast.makeText(TaobaoActivity.this, "�򿪶Ի�ģʽ...", 0).show();
				} else if (v.getId() == 100 + 5) {
					Toast.makeText(TaobaoActivity.this, "������..", 0).show();
				}
			}
		};
		clayout.setButtonsOnClickListener(clickit);
	}
}

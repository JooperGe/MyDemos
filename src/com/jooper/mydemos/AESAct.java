package com.jooper.mydemos;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jooper.mydemos.MyUtils.AES;
import com.jooper.mydemos.MyUtils.LogUtil;
import com.jooper.mydemos.base.BaseAct;

/**
 * AES加密、解密测试
 * 
 * @author Jooper
 *
 */
public class AESAct extends BaseAct implements OnClickListener {

	private TextView tv_cur_key, tv_encrypted, tv_decrypted;
	private EditText et_original_text;
	private Button btn_en, btn_de;

	private String originalStr = null;
	private byte[] mBytes = null;

	private AES mAes = new AES();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_aes_test);

		tv_cur_key = (TextView) findViewById(R.id.tv_cur_key);
		tv_encrypted = (TextView) findViewById(R.id.tv_encrypted);
		tv_decrypted = (TextView) findViewById(R.id.tv_decrypted);

		tv_cur_key.setText("当前key：" + mAes.humanPassphrase);

		et_original_text = (EditText) findViewById(R.id.et_original_text);
		btn_en = (Button) findViewById(R.id.btn_en);
		btn_en.setOnClickListener(this);
		btn_de = (Button) findViewById(R.id.btn_de);
		btn_de.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_en:

			originalStr = et_original_text.getText().toString().trim();
			try {

				mBytes = originalStr.getBytes("UTF8");

			} catch (Exception e) {

				LogUtil.j("明文转码出错：" + e.toString());
				Toast.makeText(mContext, "明文转码出错：" + e.toString(), 0).show();
			}

			if (!TextUtils
					.isEmpty(et_original_text.getText().toString().trim())
					&& mBytes != null) {

				tv_encrypted.setText("" + mAes.encrypt(mBytes));
			}
			break;
		case R.id.btn_de:

			if (!TextUtils.isEmpty(tv_encrypted.getText().toString().trim())
					&& mBytes != null) {

				tv_decrypted.setText(""
						+ mAes.decrypt(tv_encrypted.getText().toString()));
			}

			break;
		}
	}
}

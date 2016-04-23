package com.jooper.mydemos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.jooper.mydemos.MyUtils.LogUtil;
import com.jooper.mydemos.base.BaseAct;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * 社会化平台分享测试
 * 
 * 1.分享至QQ好友官方资料：http://wiki.open.qq.com/wiki/mobile/Android_SDK%E4%BD%BF%E7%94%
 * A8%E8%AF%B4%E6%98%8E
 * 
 * 2.分享至微信好友+朋友圈：
 * 
 * @author Jooper
 *
 */
public class SocialShareAct extends BaseAct implements OnClickListener {

	private EditText tv_share_content;
	private Button btn_to_QQ, btn_to_WXFriend, btn_to_WXLife;
	private String shareContent = "";

	private String qqAppId = "1105156698";
	private String qqAppKey = "Uq0PdoEBjH0Du75H";

	private Tencent mTencent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_social_share);

		// 初始化QQ分享主要实现类Tencent
		mTencent = Tencent
				.createInstance(qqAppId, this.getApplicationContext());

		tv_share_content = (EditText) findViewById(R.id.tv_share_content);
		btn_to_QQ = (Button) findViewById(R.id.btn_to_QQ);
		btn_to_QQ.setOnClickListener(this);
		btn_to_WXFriend = (Button) findViewById(R.id.btn_to_WXFriend);
		btn_to_WXFriend.setOnClickListener(this);
		btn_to_WXLife = (Button) findViewById(R.id.btn_to_WXLife);
		btn_to_WXLife.setOnClickListener(this);

		shareContent = tv_share_content.getText().toString();
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_to_QQ:

			break;
		case R.id.btn_to_WXFriend:

			break;
		case R.id.btn_to_WXLife:

			break;
		}
	}

	private class BaseUiListener implements IUiListener {
		@Override
		public void onComplete(Object response) {

			// V2.0版本，参数类型由JSONObject 改成了Object,具体类型参考api文档

			// mBaseMessageText.setText("onComplete:");
			doComplete(response);
		}

		protected void doComplete(Object values) {

			LogUtil.j("onComplete:" + values.toString());
		}

		@Override
		public void onError(UiError e) {
			// showResult("onError:", "code:" + e.errorCode + ", msg:"
			// + e.errorMessage + ", detail:" + e.errorDetail);
			LogUtil.j("onError:" + "code:" + e.errorCode + ", msg:"
					+ e.errorMessage + ", detail:" + e.errorDetail);
		}

		@Override
		public void onCancel() {
			// showResult("onCancel", "");
			LogUtil.j("onCancel:");
		}
	}

}

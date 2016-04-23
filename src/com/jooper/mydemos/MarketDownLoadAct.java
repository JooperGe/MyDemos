package com.jooper.mydemos;

import com.jooper.mydemos.base.BaseAct;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * 根据包名判断是否已下载安装，没有安装则提示去下载
 * 
 * @author Jooper
 *
 */
public class MarketDownLoadAct extends BaseAct {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_market_download);

		AlertDialog.Builder builder_exit = new Builder(this);
		builder_exit.setMessage("您的手机还未安装XXX，现在去下载？");
		builder_exit.setTitle("提示");
		builder_exit.setPositiveButton("下载",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {

						Uri uri = Uri
								.parse("market://details?id=com.ikang.web");// id为包名
						// Uri uri = Uri
						// .parse("market://details?id=com.baidu.BaiduMap");//
						// id为包名
						Intent intentToMarket = new Intent(Intent.ACTION_VIEW,
								uri);
						startActivity(intentToMarket);

						arg0.dismiss();
					}
				});
		builder_exit.setNegativeButton("取消",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						arg0.dismiss();
					}
				});
		builder_exit.show();
	}

}

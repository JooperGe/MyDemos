package com.jooper.mydemos;

import com.jooper.mydemos.base.BaseAct;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * ���ݰ����ж��Ƿ������ذ�װ��û�а�װ����ʾȥ����
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
		builder_exit.setMessage("�����ֻ���δ��װXXX������ȥ���أ�");
		builder_exit.setTitle("��ʾ");
		builder_exit.setPositiveButton("����",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {

						Uri uri = Uri
								.parse("market://details?id=com.ikang.web");// idΪ����
						// Uri uri = Uri
						// .parse("market://details?id=com.baidu.BaiduMap");//
						// idΪ����
						Intent intentToMarket = new Intent(Intent.ACTION_VIEW,
								uri);
						startActivity(intentToMarket);

						arg0.dismiss();
					}
				});
		builder_exit.setNegativeButton("ȡ��",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						arg0.dismiss();
					}
				});
		builder_exit.show();
	}

}

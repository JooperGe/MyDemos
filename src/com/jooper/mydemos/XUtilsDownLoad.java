package com.jooper.mydemos;

import java.io.File;

import android.os.Bundle;
import android.widget.Toast;

import com.jooper.mydemos.base.BaseAct;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * XUtils-2.6.14下载图片
 * 
 * @author Jooper
 *
 */
public class XUtilsDownLoad extends BaseAct {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		download();
	}

	public void download() {

		HttpUtils httpUtils = new HttpUtils();

		httpUtils
				.download(
						"http://image.sifude.com/image/print/4ba4563fb8a0c0a64cebf1e13404340e.png",
						"/sdcard/ajooper.png", true, true,
						new RequestCallBack<File>() {

							@Override
							public void onStart() {
								super.onStart();
							}

							@Override
							public void onLoading(long total, long current,
									boolean isUploading) {
								super.onLoading(total, current, isUploading);
							}

							@Override
							public void onSuccess(ResponseInfo<File> arg0) {

								Toast.makeText(XUtilsDownLoad.this, "下载成功", 0)
										.show();
							}

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								Toast.makeText(XUtilsDownLoad.this, "失败", 0)
										.show();

							}
						});
	}
}

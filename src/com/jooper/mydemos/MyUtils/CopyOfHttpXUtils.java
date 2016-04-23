package com.jooper.mydemos.MyUtils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.xutils.x;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import android.os.Handler;
import android.util.Log;

/**
 * 结合xUtils
 * 
 * @author Jooper
 *
 */
public class CopyOfHttpXUtils {

	String result = "";
	OnHttpBack back;
	String stringBuilder = null;
	int num;
	String urlStr;
	String jsonContent;

	private final static OkHttpClient client = new OkHttpClient();

	public CopyOfHttpXUtils(String urlStr, String jsonContent, OnHttpBack back,
			int num) {
		super();
		this.back = back;
		this.num = num;
		httpClientPost(urlStr, jsonContent);
	}

	// public static void httpClientPost(String urlStr, HashMap<String, Object>
	// map) {
	public void httpClientPost(String urlStr, String jsonContent) {

		// HttpPost hp = null;
		// HttpClient hc = null;
		// HttpResponse hr = null;

		// RequestParams requestParams = new RequestParams(
		// "http://192.168.1.109:15004/youlifeapp/app/gm/detail/v1/d116e074-a38b-4cd6-8a6b-2c93244c1bf1");
		RequestParams requestParams = new RequestParams(urlStr);

		// setHeader(name, value)：如果Header中没有定义则添加，如果已定义则用新的value覆盖原用value值。
		// addHeader(name, value)：如果Header中没有定义则添加，如果已定义则保持原有value不改变。
		requestParams.setHeader("safetyId", "100088");
		requestParams.setHeader("token", "7035b949-37a6-89efed2c-7efd02f4");

		// requestParams.addBodyParameter(name, value);
		requestParams.addBodyParameter("userId",
				"8626bd2e-85f7-4bf3-9b68-04c435ba45da");
		// requestParams.setBodyContent(jsonContent);

		x.http().post(requestParams, new Callback.CommonCallback<String>() {

			@Override
			public void onCancelled(CancelledException arg0) {
				System.out.println("TNCancel："
						+ Thread.currentThread().getName());
				Log.d("Jooper", "被取消：" + arg0.toString());
				// result = arg0.toString();
				stringBuilder = arg0.toString();
				try {
					back.loadDate(stringBuilder, num);
				} catch (Exception e) {
					e.printStackTrace();
				}
				handler.sendEmptyMessage(1);

			}

			@Override
			public void onError(Throwable arg0, boolean arg1) {
				System.out.println("TNError："
						+ Thread.currentThread().getName());
				Log.d("Jooper", "出错：" + arg0.toString());
				stringBuilder = arg0.toString();
				try {
					back.loadDate(stringBuilder, num);
				} catch (Exception e) {
					e.printStackTrace();
				}
				handler.sendEmptyMessage(1);
			}

			@Override
			public void onFinished() {

				Log.d("Jooper", "完成");
			}

			@Override
			public void onSuccess(String arg0) {
				System.out.println("TNSuccess："
						+ Thread.currentThread().getName());
				Log.d("Jooper", "成功：" + arg0.toString());
				// result = arg0.toString();
				stringBuilder = arg0.toString();
				try {
					back.loadDate(stringBuilder, num);
				} catch (Exception e) {
					e.printStackTrace();
				}
				handler.sendEmptyMessage(1);
			}
		});
		// System.out.println("result:" + result);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {

				try {
					back.upUI(stringBuilder, num);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		};
	};

	/**
	 * 上传文件-未验证
	 * 
	 * @param urlStr
	 * @param fileName
	 */
	public void xUtilsUpLoad(String urlStr, String fileName) {

		RequestParams requestParams = new RequestParams(urlStr);
		requestParams.setMultipart(true);
		requestParams.addBodyParameter("msgType", "1");
		requestParams.addBodyParameter("logFile", fileName);// 如果文件没有扩展名，最好设置contentType参数
		x.http().post(requestParams, new Callback.CommonCallback<String>() {

			@Override
			public void onCancelled(CancelledException arg0) {
			}

			@Override
			public void onError(Throwable arg0, boolean arg1) {
			}

			@Override
			public void onFinished() {
			}

			@Override
			public void onSuccess(String arg0) {
			}
		});
	}

	/**
	 * okHttp的post请求测试
	 * 
	 * @param urlStr
	 * @param fileName
	 */
	public static void okHttpPost(String urlStr, String fileName) {

		Request request = new Request.Builder()
				.url("http://192.168.1.109:15004/youlifeapp/app/gm/detail/v1/d116e074-a38b-4cd6-8a6b-2c93244c1bf1")
				// .header("User-Agent", "OkHttp Headers.java")
				.addHeader("safetyId", "100088")
				.addHeader("token", "7035b949-37a6-89efed2c-7efd02f4").build();

		try {

			Response response = client.newCall(request).execute();

			if (response.isSuccessful()) {

				Log.v("Jooper", "访问成功：" + response.code() + " --- "
						+ response.body().string());
			} else {
				Log.v("Jooper", "访问失败");
			}

		} catch (IOException e) {

			Log.v("Jooper", "访问失败e：" + e.toString());
			e.printStackTrace();
		}
	}
}

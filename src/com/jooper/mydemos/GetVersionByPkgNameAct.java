package com.jooper.mydemos;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jooper.mydemos.base.BaseAct;

/**
 * 根据指定包名获取版本信息
 * 
 * @author Jooper
 *
 */
public class GetVersionByPkgNameAct extends BaseAct {

	private EditText et_pkg;
	private TextView tv_version_info;
	private Button btn_get_version;

	private String versionName = "mnull";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_version_by_pkg);

		et_pkg = (EditText) findViewById(R.id.et_pkg);
		tv_version_info = (TextView) findViewById(R.id.tv_version_info);
		btn_get_version = (Button) findViewById(R.id.btn_get_version);
		btn_get_version.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {

					versionName = getVersionName(GetVersionByPkgNameAct.this,
							et_pkg.getText().toString());

					tv_version_info.setText(versionName);

				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});

		String pkg = "comm.cchong.BloodAssistant";

		et_pkg.setText(pkg);

	}

	/**
	 * 根据应用的包名获取应用的版本号
	 * 
	 * @param context
	 * @param packagename
	 * @return
	 * @throws Exception
	 */
	public static String getVersionName(Context context, String packagename)
			throws Exception {
		// 获取packagemanager的实例
		PackageManager packageManager = context.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = packageManager.getPackageInfo(packagename, 0);
		String version = packInfo.versionName;
		return version;
	}
}

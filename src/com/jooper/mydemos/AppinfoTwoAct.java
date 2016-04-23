package com.jooper.mydemos;

import java.util.List;

import com.jooper.mydemos.base.BaseAct;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.TextView;
/**
 * ��ȡ�����Ѱ�װapp��Ϣ
 * @author Jooper
 *
 */
public class AppinfoTwoAct extends BaseAct {

	private TextView main_info_tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_app_info_two);
		initView();
		initValues();
	}

	private void initView() {
		main_info_tv = (TextView) findViewById(R.id.main_info_tv);
	}

	private void initValues() {
		main_info_tv.setText(getAppInfos());
	}

	private String getAppInfos() {
		String result = "��demo��;����Ҫ���ڲ鿴�ֻ���Ӧ�ó���İ��������Activity�Ͱ汾��Ϣ��^_^\n\n";

		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> mApps = getPackageManager().queryIntentActivities(
				mainIntent, 0);

		for (int i = 0; i < mApps.size(); i++) {
			try {
				ResolveInfo info = mApps.get(i);
				String appName = info.loadLabel(getPackageManager()).toString();
				String packagename = info.activityInfo.packageName;
				String appname = info.activityInfo.name;
				PackageInfo packageInfo = getPackageManager().getPackageInfo(
						packagename, 0);
				int appVersionCode = packageInfo.versionCode;
				String appVersionName = packageInfo.versionName;

				result += "NO. " + i + " " + appName + " apk��\n" + "�������: "
						+ packagename + "\n��Ӧ���Activity: " + appname
						+ "\n�汾��: " + appVersionCode + "\n�汾����: "
						+ appVersionName + "\n\n";
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				// can't reach
				e.printStackTrace();
			}
		}

		return result;
	}

}

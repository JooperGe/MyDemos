package com.jooper.mydemos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.jooper.mydemos.MyUtils.LogUtil;
import com.jooper.mydemos.adapter.BrowseApplicationInfoAdapter;
import com.jooper.mydemos.base.BaseAct;
import com.jooper.mydemos.model.Appinfo;
import com.jooper.mydemos.model.TestInfo;

/**
 * ��ȡ�����Ѱ�װapp��Ϣ
 * 
 * @author Jooper
 *
 */
public class AppinfoAct extends BaseAct implements OnItemClickListener {

	private ListView listview = null;

	private List<Appinfo> mlistAppInfo = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broswe_app_list);

		listview = (ListView) findViewById(R.id.listviewApp);
		mlistAppInfo = new ArrayList<Appinfo>();
		queryAppInfo(); // ��ѯ����Ӧ�ó�����Ϣ
		BrowseApplicationInfoAdapter browseAppAdapter = new BrowseApplicationInfoAdapter(
				this, mlistAppInfo);
		listview.setAdapter(browseAppAdapter);
		listview.setOnItemClickListener(this);

		TestInfo a = (TestInfo) getIntent().getExtras().getSerializable(
				"Jooper");
		TestInfo b = (TestInfo) getIntent().getExtras().getSerializable(
				"Aisling");
		int c = getIntent().getExtras().getInt("extra1");
		String d = getIntent().getExtras().getString("extra2");

		LogUtil.j("Jooper " + a.getAppLabel() + " - " + a.getPkgName() + " - "
				+ a.getAppIcon());
		LogUtil.j("Aisling " + b.getAppLabel() + " - " + b.getPkgName() + " - "
				+ b.getAppIcon());
		LogUtil.j("extra1 " + c);
		LogUtil.j("extra2 " + d);
	}

	// �����ת����Ӧ�ó���
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long arg3) {
		// TODO Auto-generated method stub
		Intent intent = mlistAppInfo.get(position).getIntent();
		startActivity(intent);
	}

	// �����������Activity����Ϣ��������Launch����
	public void queryAppInfo() {
		PackageManager pm = this.getPackageManager(); // ���PackageManager����
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		// ͨ����ѯ���������ResolveInfo����.
		List<ResolveInfo> resolveInfos = pm
				.queryIntentActivities(mainIntent, 0);
		// List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent,
		// PackageManager.MATCH_DEFAULT_ONLY);//��ʾ��ȫ
		// ����ϵͳ���� �� ����name����
		// ���������Ҫ������ֻ����ʾϵͳӦ�ã��������г�������Ӧ�ó���
		Collections.sort(resolveInfos,
				new ResolveInfo.DisplayNameComparator(pm));
		if (mlistAppInfo != null) {
			mlistAppInfo.clear();
			for (ResolveInfo reInfo : resolveInfos) {
				String activityName = reInfo.activityInfo.name; // ��ø�Ӧ�ó��������Activity��name
				String pkgName = reInfo.activityInfo.packageName; // ���Ӧ�ó���İ���
				String appLabel = (String) reInfo.loadLabel(pm); // ���Ӧ�ó����Label
				Drawable icon = reInfo.loadIcon(pm); // ���Ӧ�ó���ͼ��
				// ΪӦ�ó��������Activity ׼��Intent
				Intent launchIntent = new Intent();
				launchIntent.setComponent(new ComponentName(pkgName,
						activityName));
				// ����һ��AppInfo���󣬲���ֵ
				Appinfo Appinfo = new Appinfo();
				Appinfo.setAppLabel(appLabel);
				Appinfo.setPkgName(pkgName);
				Appinfo.setAppIcon(icon);
				Appinfo.setIntent(launchIntent);
				mlistAppInfo.add(Appinfo); // ������б���
			}
		}
	}
}

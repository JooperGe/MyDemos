package com.jooper.mydemos.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jooper.mydemos.R;
import com.jooper.mydemos.model.Appinfo;

public class BrowseApplicationInfoAdapter extends BaseAdapter {

	private List<Appinfo> mlistAppInfo = null;
	private Context mContext;

	LayoutInflater infater = null;

	public BrowseApplicationInfoAdapter(Context context, List<Appinfo> apps) {
		infater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mlistAppInfo = apps;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		System.out.println("size" + mlistAppInfo.size());
		return mlistAppInfo.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mlistAppInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertview, ViewGroup arg2) {
		System.out.println("getView at " + position);
		View view = null;
		ViewHolder holder = null;
		if (convertview == null || convertview.getTag() == null) {
			view = infater.inflate(R.layout.browse_app_item, null);
			holder = new ViewHolder(view);
			view.setTag(holder);
		} else {
			view = convertview;
			holder = (ViewHolder) convertview.getTag();
		}
		Appinfo appInfo = (Appinfo) getItem(position);
		holder.appIcon.setImageDrawable(appInfo.getAppIcon());
		holder.tvAppLabel.setText(appInfo.getAppLabel());
		if (appInfo.getAppLabel().equals("°®¿µÌå¼ì±¦")) {

			holder.tvPkgName.setTextColor(Color.RED);
		}else{
			
			holder.tvPkgName.setTextColor(mContext.getResources().getColor(R.color.orange));
		}
		holder.tvPkgName.setText(appInfo.getPkgName());
		return view;
	}

	class ViewHolder {
		ImageView appIcon;
		TextView tvAppLabel;
		TextView tvPkgName;

		public ViewHolder(View view) {
			this.appIcon = (ImageView) view.findViewById(R.id.imgApp);
			this.tvAppLabel = (TextView) view.findViewById(R.id.tvAppLabel);
			this.tvPkgName = (TextView) view.findViewById(R.id.tvPkgName);
		}
	}
}
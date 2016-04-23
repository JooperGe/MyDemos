package com.jooper.mydemos.MyUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Intent工具类
 * 
 * @author Jooper
 *
 */
public class IntentUtils {
	private static Intent intent;

	/**
	 * 开启Intent 如果不需要传入data，则直接传null
	 * 
	 * @param context
	 * @param cls
	 * @param data
	 */
	public static void startActivity(Context context, Class<?> cls, HashMap data) {

		if (null == intent) {

			intent = new Intent();
		}
		if (data != null) {

			addData(intent, data);
		}

		intent.setClass(context, cls);
		context.startActivity(intent);

		intent = null;
	}

	/**
	 * 开启Intent(附带请求码) 如果不需要传入data，则直接传null
	 * 
	 * @param activity
	 * @param cls
	 * @param requestCode
	 * @param data
	 */
	public static void startActivityForResult(Activity activity, Class<?> cls,
			int requestCode, HashMap data) {

		if (null == intent) {

			intent = new Intent();
		}
		if (data != null) {

			addData(intent, data);
		}

		intent.setClass(activity, cls);
		activity.startActivityForResult(intent, requestCode);

		intent = null;
	}

	/**
	 * 向Intent中添加数据
	 * 
	 * @param intentx
	 * @param data
	 */
	private static void addData(Intent intentx, HashMap data) {

		Set<Map.Entry<String, Object>> entrySet = data.entrySet();

		for (Map.Entry<String, Object> entry : entrySet) {

			if (entry.getValue() instanceof Boolean) {

				intentx.putExtra(entry.getKey(), (Boolean) entry.getValue());

			} else if (entry.getValue() instanceof String) {

				intentx.putExtra(entry.getKey(), (String) entry.getValue());

			} else if (entry.getValue() instanceof Integer) {

				intentx.putExtra(entry.getKey(), (Integer) entry.getValue());

			} else if (entry.getValue() instanceof Serializable) {

				intentx.putExtra(entry.getKey(),
						(Serializable) entry.getValue());

			}

		}
	}
}

package com.jooper.mydemos.MyUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;

import com.jooper.mydemos.interfaces.InjectContentView;
import com.jooper.mydemos.interfaces.InjectView;

/**
 * ע����Ĵ���
 * 
 * @author Jooper
 *
 */
public class InjectUtils {

	private static final String METHOD_SET_CONTENTVIEW = "setContentView";
	private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

	public static void inject(Activity activity) {

		injectContentView(activity);
		injectViews(activity);
	}

	/**
	 * ע�����еĿؼ�
	 * 
	 * @param activity
	 */
	private static void injectViews(Activity activity) {

		Class<? extends Activity> clazz = activity.getClass();
		Field[] fields = clazz.getDeclaredFields();

		// �������г�Ա����
		for (Field field : fields) {

			InjectView viewInjectAnnotation = field
					.getAnnotation(InjectView.class);

			if (viewInjectAnnotation != null) {

				int viewId = viewInjectAnnotation.value();
				if (viewId != -1) {

					// ��ʼ��View
					try {

						Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID,
								int.class);
						Object resView = method.invoke(activity, viewId);
						field.setAccessible(true);
						field.set(activity, resView);

					} catch (Exception e) {

						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * ע���������ļ�
	 * 
	 * @param activity
	 */
	private static void injectContentView(Activity activity) {

		Class<? extends Activity> clazz = activity.getClass();

		// ��ѯ�����Ƿ����ContentViewע��
		InjectContentView contentView = clazz
				.getAnnotation(InjectContentView.class);
		if (contentView != null) {// ����

			int contentViewLayoutId = contentView.value();
			try {

				Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW,
						int.class);
				method.setAccessible(true);
				method.invoke(activity, contentViewLayoutId);

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}
}

package com.jooper.mydemos.base;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.jooper.mydemos.interfaces.InjectView;

public class BaseAct extends Activity {

	public static Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		mContext = this;

		// autoInjectAllField();
	}

	/**
	 * ����ע��
	 */
	public void autoInjectAllField() {
		try {
			Class<?> clazz = this.getClass();
			Field[] fields = clazz.getDeclaredFields();// ���Activity���������ֶ�
			for (Field field : fields) {
				// �鿴����ֶ��Ƿ��������Զ����ע�����־��
				if (field.isAnnotationPresent(InjectView.class)) {
					InjectView inject = field.getAnnotation(InjectView.class);
					int id = inject.value();
					if (id > 0) {
						field.setAccessible(true);
						field.set(this, this.findViewById(id));// ������Ҫ�ҵ��ֶ�����ֵ
					}
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}

package com.jooper.mydemos.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ע����(�ӿ�)�����ڽ���ÿ������
 * 
 * ��Ҫ��Activity�н���
 * 
 * ����Ĺؼ���@interface ; @Target��ʾ��ע���������ʲô�ط�����߱�ʶ���ܵ����ͣ�TYPE���ࣩ,FIELD����Ա������
 * 						 @Retention��ʾ����ʾ��Ҫ��ʲô���𱣴��ע����Ϣ����������Ϊ����ʱ
 * 
 * @author Jooper
 *
 */

// ��ʾͨ���ֶ�
@Target(ElementType.TYPE)

// ��ʾ���������ڴ���ʱ
@Retention(RetentionPolicy.RUNTIME)

public @interface InjectContentView {

	int value() default 0;
}

package com.jooper.mydemos.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解类(接口)，用于解析每个布局
 * 
 * 需要在Activity中解析
 * 
 * 定义的关键字@interface ; @Target表示该注解可以用于什么地方，后边标识可能的类型：TYPE（类）,FIELD（成员变量）
 * 						 @Retention表示：表示需要在什么级别保存该注解信息；这里设置为运行时
 * 
 * @author Jooper
 *
 */

// 表示通过字段
@Target(ElementType.TYPE)

// 表示在生命周期存在时
@Retention(RetentionPolicy.RUNTIME)

public @interface InjectContentView {

	int value() default 0;
}

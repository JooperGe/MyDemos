package com.jooper.mydemos.MyUtils;

import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {
	/**
	 * 将对象序列化为Json字符
	 * 
	 * @param obj
	 *            对象
	 * @return String Json字符
	 */
	public static final String toJson(Object obj) {
		return JSON.toJSONString(obj, SerializerFeature.WriteNullStringAsEmpty);
	}

	/**
	 * 将Json字符串反序列化为对象
	 * 
	 * @param jsonString
	 *            Json字符
	 * @param clazz
	 *            要转换到的对象类
	 * @return <T>对象
	 */
	public static final <T> T toObj(String jsonString, Class<T> clazz) {
		// 注释：编码问题 %
		// jsonString = CodingUtil.paramDecoding(jsonString);
		return JSON.parseObject(jsonString, clazz);
	}

	/**
	 * 将Json字符串反序列化为数组对象
	 * 
	 * @param jsonString
	 *            Json字符
	 * @param clazz
	 *            要转换到的对象类（数组内的对象实体）
	 * @return List<T>数组对象
	 */
	public static final <T> List<T> toList(String jsonString, Class<T> clazz) {
		// 注释：编码问题 %
		// jsonString = CodingUtil.paramDecoding(jsonString);
		return JSON.parseArray(jsonString, clazz);

	}

	/**
	 * 将Map转化为对象
	 * 
	 * @param jsonString
	 *            Json字符
	 * @param clazz
	 *            要转换到的对象类
	 * @return <T>对象
	 */
	public static final <T> T toObj(Object map, Class<T> clazz) {
		return JSON.parseObject(
				JSON.toJSONString(map, SerializerFeature.WriteMapNullValue),
				clazz);
	}
}

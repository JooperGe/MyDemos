package com.jooper.mydemos.MyUtils;

public interface OnHttpBack {
	/**
	 * 获取数据刷新UI
	 * 
	 * @param str
	 * @param num
	 * @throws Exception
	 */
	public void upUI(String str, int num) throws Exception;

	/**
	 * 在线程中解析数据
	 * 
	 * @param str
	 * @param num
	 * @throws Exception
	 */
	public void loadDate(String str, int num) throws Exception;
}

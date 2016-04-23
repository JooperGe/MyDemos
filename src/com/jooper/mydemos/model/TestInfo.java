package com.jooper.mydemos.model;

import java.io.Serializable;

public class TestInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String appLabel;
	private int appIcon;
	private String pkgName;

	public TestInfo() {
	}

	public String getAppLabel() {
		return appLabel;
	}

	public void setAppLabel(String appName) {
		this.appLabel = appName;
	}

	public int getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(int appIcon) {
		this.appIcon = appIcon;
	}

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

}

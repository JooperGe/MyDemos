package com.jooper.mydemos.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * XUtils3测试用的实体
 * 
 * @author Jooper
 *
 */
@Table(name = "myXDB3")
public class XUtilsEntity {

	@Column(name = "id", isId = true)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "city")
	private String city;
	@Column(name = "age")
	private int age;
	@Column(name = "addTime")
	private String addTime;
	@Column(name = "extra6")
	private String extra6;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getExtra6() {
		return extra6;
	}

	public void setExtra6(String extra6) {
		this.extra6 = extra6;
	}

}

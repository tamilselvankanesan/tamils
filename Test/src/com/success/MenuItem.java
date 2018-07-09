package com.success;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "MenuItem")
@XmlAccessorType (XmlAccessType.FIELD)
public class MenuItem implements Cloneable{
	private String name;
	private String code;
	private String navigateTo;
	@XmlElement(name = "MenuItem")
	private List<MenuItem> children;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the navigateTo
	 */
	public String getNavigateTo() {
		return navigateTo;
	}
	/**
	 * @param navigateTo the navigateTo to set
	 */
	public void setNavigateTo(String navigateTo) {
		this.navigateTo = navigateTo;
	}
	/**
	 * @return the menuItem
	 */
	public List<MenuItem> getChildren() {
		return children;
	}
	/**
	 * @param menuItem the menuItem to set
	 */
	public void setChildren(List<MenuItem> children) {
		this.children = children;
	}
}

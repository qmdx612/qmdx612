package cn.qb.store.entity;

import java.io.Serializable;

public class District implements Serializable {

	private static final long serialVersionUID = -2478762515707132449L;
	
	private String parent;
	private String code;
	private String name;
	
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "District [parent=" + parent + ", code=" + code + ", name=" + name + "]";
	}	
}

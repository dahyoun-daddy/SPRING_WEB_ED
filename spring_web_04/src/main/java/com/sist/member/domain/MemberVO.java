package com.sist.member.domain;

import com.sist.common.DTO;

/**
 * MemberVO.java
 * @author sist_
 *
 */
public class MemberVO  extends DTO {
	private String id     ;
	private String name   ;
	private String email  ;
	private String passwd ;
	
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", name=" + name + ", email=" + email + ", passwd=" + passwd + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}

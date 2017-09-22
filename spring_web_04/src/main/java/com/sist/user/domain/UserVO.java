package com.sist.user.domain;

import com.sist.common.DTO;

/**
 * 
 * @author sist_
 *
 */
public class UserVO extends DTO  {

	private String id       ;
	private String name     ;
	private String password ;	
	private int    ulevel  ;
	private int    login    ;
	private int    recommend;
	private String regDt   ;
	

	public int getUlevel() {
		return ulevel;
	}

	public void setUlevel(int ulevel) {
		this.ulevel = ulevel;
	}
	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	private int    levelIntValue;//Level int 값
	private String mail ;

	

	
	public int getLevelIntValue() {
		return levelIntValue;
	}

	public void setLevelIntValue(int levelIntValue) {
		this.levelIntValue = levelIntValue;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public UserVO() {
		
	}


	@Override
	public String toString() {
		return "UserVO [id=" + id + ", name=" + name + ", password=" + password + ", ulevel=" + ulevel + ", login="
				+ login + ", recommend=" + recommend + ", reg_dt=" + regDt + ", levelIntValue=" + levelIntValue
				+ ", mail=" + mail + "]"; 
	}

	
	
	public UserVO(String id, String name, String password, int ulevel, int login, int recommend, String regDt,
			int levelIntValue, String mail) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.ulevel = ulevel;
		this.login = login; 
		this.recommend = recommend;
		this.regDt = regDt;
		this.levelIntValue = levelIntValue;
		this.mail = mail;
	}



	public int getLogin() {
		return login;
	}

	public void setLogin(int login) {
		this.login = login;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * next Level set
	 */
//	public void upgradeLevel() {
//		Level nextLevel = this.level.getNext();
//		if(nextLevel == null) {
//			throw new IllegalStateException(this.level+"은 업그레이드 블가능");
//		}else {
//			this.level = nextLevel;
//		}
//	}

}


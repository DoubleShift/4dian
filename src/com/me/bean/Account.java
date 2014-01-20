package com.me.bean;

public class Account {
	//account : uid,name,email,pass,avatar,gender 性别,agerange 年龄层,domain,location,homepage,rp,chances
	private String uid;
	private String name;
	private String email;
	private String pass;
	private String avatar ;
	private String gender;
	private String agerange;
	private String domain;
	private String location;
	private String homepage;
	private String rp;
	private String chances; //挑战
	
	//archive:uid,target,level,needtime,limittime,averagetime
	private String target;
	private String level;
	private String needtime;
	private String limittime;
	private String averagetime;
	
	
	public Account(String uid,String name,String email,String pass,String avatar,String gender,String agerange,
			String domain,String location,String homepage,String rp,String chances) {
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.pass =pass;
		this.avatar = avatar ;
		this.gender = gender;
		this.agerange = agerange;
		this.domain = domain;
		this.location = location;
		this.homepage = homepage;
		this.rp = rp;
		this.chances = chances;
		
		// TODO Auto-generated constructor stub
	}
	
	public Account() {
		
	}

	public void setArchive(String target,String level,String needtime,String limittime,String averagetime){
		this.target=target;
		this.level = level;
		this.needtime =needtime;
		this.limittime = limittime;
		this.averagetime = averagetime;
	}
	public String getUid() {
		return uid;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPass() {
		return pass;
	}
	public String getAvatar() {
		return avatar;
	}
	public String getGender() {
		return gender;
	}
	public String getAgerange() {
		return agerange;
	}
	public String getDomain() {
		return domain;
	}
	public String getLocation() {
		return location;
	}
	public String getHomepage() {
		return homepage;
	}
	public String getRp() {
		return rp;
	}
	public String getChances() {
		return chances;
	}
	public String getTarget() {
		return target;
	}
	public String getLevel() {
		return level;
	}
	public String getNeedtime() {
		return needtime;
	}
	public String getLimittime() {
		return limittime;
	}
	public String getAveragetime() {
		return averagetime;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setAgerange(String agerange) {
		this.agerange = agerange;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public void setRp(String rp) {
		this.rp = rp;
	}
	public void setChances(String chances) {
		this.chances = chances;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setNeedtime(String needtime) {
		this.needtime = needtime;
	}
	public void setLimittime(String limittime) {
		this.limittime = limittime;
	}
	public void setAveragetime(String averagetime) {
		this.averagetime = averagetime;
	}
}

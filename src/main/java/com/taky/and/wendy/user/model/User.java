package com.taky.and.wendy.user.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 유저 정보 객체
 * 
 * @author SeongTak.Yoon
 *
 */
@Alias("user")
@SuppressWarnings("serial")
public class User implements UserDetails {
	/** 유저의 상태가 등록 */
	public static final String RECORD = "record";
	/** 유저의 상태가 일반 */
	public static final String NORMAL = "normal";
	
	private String email;
	private String name;
	private String password;
	private Date regdate;
	private Date moddate;
	/** User의 등급 */
	private long level;
	/** email 인증과 같은 본인 인증 확인 데이터 */
	private String certification;
	/** 상태 (정상, 인증전, 차단, 삭제, 휴먼 등) */
	private String condition;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getModdate() {
		return moddate;
	}

	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(Long.toString(level))); 
		
		return grantedAuthorities;
	}

	@Override
	public String getUsername() {
		return name;
	}

	/**
	 * 계정이 만료되지 않았는가?
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 계정이 잠겨있지 않는가?
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 계정의 패스워드가 만료되지 않았는가?
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 계정이 사용가능한가?
	 */
	@Override
	public boolean isEnabled() {
		return StringUtils.equals(NORMAL, condition);
	}
}

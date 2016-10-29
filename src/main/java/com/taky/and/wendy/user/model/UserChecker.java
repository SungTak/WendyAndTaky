package com.taky.and.wendy.user.model;

/**
 * 중복 id 값을 체크한 데이터를 저장하는 모델
 * 
 * @author SeongTak.Yoon
 *
 */
public class UserChecker {
	/** 유저 ID */
	private String id;
	/** 유저 상태 (대기자,유저) */
	private String status;
	/** ID가 이미 존재하는지 확인 */
	private boolean isExist;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isExist() {
		return isExist;
	}

	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}
}

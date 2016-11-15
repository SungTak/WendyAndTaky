package com.taky.and.wendy.date.model;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

/**
 * 데이트 코스 포스팅 모델
 * 
 * @author seongtak.yoon (zergyst1988@naver.com)
 *
 */
@Alias("datePosting")
public class DatePosting {
	private long id;
	private String title;
	/** 섬네일 */
	private MultipartFile thumbnail;
	/** 섬네일 경로 */
	private String thumbnailPath;
	/** 데이트 지역 */
	private String region;
	/** 데이트 코스 소개 본문 */
	private String body;
	/** 포스팅 작성자 */
	private String editor;
	
	public MultipartFile getThumbnail() {
		return thumbnail;
	}
	
	public void setThumbnail(MultipartFile thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}
}

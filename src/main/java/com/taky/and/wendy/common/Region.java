package com.taky.and.wendy.common;

import org.apache.commons.lang3.StringUtils;

public enum Region {
	SEOUL_JONGNO("서울/종로", "/seoul/jongno");
	
	private String name;
	private String imagePath;

	private Region(String name, String imagePath) {
		this.name = name;
		this.imagePath = imagePath;
	}
	
	/**
	 * 지역 이름으로 지역 섬네일 고유 경로를 찾습니다.
	 * 
	 * @param name
	 * @return
	 */
	public static String findImagePath(String name) {
		Region[] regions = Region.values();
		for (Region region : regions) {
			if (StringUtils.equals(region.getName(), name)) {
				return region.getImagePath();
			}
		}
		return "";
	}
	
	public String getName() {
		return name;
	}

	public String getImagePath() {
		return imagePath;
	}
}

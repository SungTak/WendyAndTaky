package com.taky.and.wendy.date.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taky.and.wendy.common.Region;
import com.taky.and.wendy.date.model.DatePosting;

@Service
public class DatePostingServiceImpl implements DatePostingService {
	private static final Logger logger = LoggerFactory.getLogger(DatePostingServiceImpl.class);
	
	@Value("${wat.file.home}")
	private String fileHome;
	
	@Override
	public boolean saveDatePosting(DatePosting datePosting) throws Exception {
		MultipartFile thumbnail = datePosting.getThumbnail();
		if (thumbnail == null || thumbnail.isEmpty()) {
			throw new IllegalArgumentException("### 업로드한 섬네일이 존재하지 않습니다!");
		}
		
		String thumbnailPath = fileHome + "/static/image/upload" + Region.findImagePath(datePosting.getRegion());
		datePosting.setThumbnailPath(thumbnailPath);
		
		File directory = new File(datePosting.getThumbnailPath());
		if (directory.isDirectory() == false) {
			directory.mkdirs();
		}

		File upload = new File(directory.getAbsolutePath() + "/" + thumbnail.getOriginalFilename());
		thumbnail.transferTo(upload);
		
		return true;
	}
}

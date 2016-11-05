package com.taky.and.wendy.upload.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Value("${wat.file.home}")
	private String fileHome;
	
	@PreAuthorize("hasRole('1')")
	@RequestMapping(path = "/upload/date-course", method = RequestMethod.GET)
	public String showUploadDateCourse() {
		return "/upload/date";
	}
	
	@RequestMapping(path = "/upload/date-course", method = RequestMethod.POST)
	@ResponseBody
	public String uploadThumbnail(@RequestParam("thumbnail") MultipartFile thumbnail) {
		logger.debug("파일업로드시작");
		
		if (thumbnail.isEmpty()) {
			return "파일 업로드에 실패하였습니다.";
		} 
		
		try {
			logger.debug(thumbnail.getOriginalFilename());
			
			File file = new File(fileHome + "/static/image/upload");
			
			if (file.isDirectory() == false) {
				file.mkdirs();
			}
			logger.debug(file.getAbsolutePath());
			logger.debug(file.toURI().toString());
			
			File upload = new File(file.getAbsolutePath() + "/" + thumbnail.getOriginalFilename());
			
			logger.debug(upload.getAbsolutePath());
			thumbnail.transferTo(upload);
			
		} catch(Exception e) {
			logger.error("### 파일을 업로드하는 과정에서 문제가 발생하였습니다!", e);
		}
		return "업로드 성공";
	}
}

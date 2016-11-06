package com.taky.and.wendy.upload.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taky.and.wendy.common.Region;
import com.taky.and.wendy.date.model.DatePosting;
import com.taky.and.wendy.date.service.DatePostingService;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private DatePostingService datePostingService;
	
	@PreAuthorize("hasRole('1')")
	@RequestMapping(path = "/upload/date-course", method = RequestMethod.GET)
	public String showUploadDateCourse(Model model) {
		model.addAttribute("regions", Region.values());
		return "/upload/date";
	}
	
	@RequestMapping(path = "/upload/date-course", method = RequestMethod.POST)
	@ResponseBody
	public String uploadThumbnail(DatePosting datePosting) throws Exception {
		logger.debug("파일업로드시작");
		
		datePostingService.saveDatePosting(datePosting);
		
//		if (thumbnail.isEmpty()) {
//			return "파일 업로드에 실패하였습니다.";
//		} 
//		
//		logger.debug(thumbnail.getOriginalFilename());
//		
//		File file = new File(fileHome + "/static/image/upload");
//		
//		if (file.isDirectory() == false) {
//			file.mkdirs();
//		}
//		logger.debug(file.getAbsolutePath());
//		logger.debug(file.toURI().toString());
//		
//		File upload = new File(file.getAbsolutePath() + "/" + thumbnail.getOriginalFilename());
//		
//		logger.debug(upload.getAbsolutePath());
//		thumbnail.transferTo(upload);
			
		return "업로드 성공";
	}
}

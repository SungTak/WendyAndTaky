package com.taky.and.wendy.upload.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.taky.and.wendy.common.Region;
import com.taky.and.wendy.date.model.DatePosting;
import com.taky.and.wendy.date.service.DatePostingService;
import org.springframework.web.multipart.MultipartFile;

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
	public String saveDatePosting(DatePosting datePosting) throws Exception {
		logger.debug("파일업로드시작");
		
		datePostingService.saveDateImage(datePosting);
		datePostingService.save(datePosting);
        return "업로드 성공";
    }

    @RequestMapping(path = "/upload/date-course/content-image", method = RequestMethod.POST)
    public String uploadImage(@RequestParam("image") MultipartFile image, @RequestParam("region") String region, Model model) throws Exception {
        try {
            String resultPath = datePostingService.saveDateImage(image, region);

            if (StringUtils.isEmpty(resultPath)) {
                // TODO 프러포터리 빼쟈
                model.addAttribute("src", "http://localhost:8080/error-image");
            } else {
                model.addAttribute("src", "http://localhost:8080" + resultPath);
            }
        } catch (Exception e) {
            logger.error("### 데이트코스 본문 이미지 올리기 실패", e);
            model.addAttribute("src", "http://localhost:8080/error-image");
        }

        return "make/img";
    }
}

package com.taky.and.wendy.upload.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.taky.and.wendy.common.Condition;
import com.taky.and.wendy.common.Region;
import com.taky.and.wendy.date.model.DateCoursePost;
import com.taky.and.wendy.date.service.DateCoursePostService;
import com.taky.and.wendy.sign.service.SignService;
import com.taky.and.wendy.user.model.User;

import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Value("${wat.domain}")
	private String domain;
	
	@Autowired
	private DateCoursePostService datePostingService;
	
	@Autowired
	private SignService signService;
	
	@PreAuthorize("hasRole('1')")
	@RequestMapping(path = "/upload/date-course", method = RequestMethod.GET)
	public String showUploadDateCourse(Model model) {
		model.addAttribute("regions", Region.values());
		return "/upload/date";
	}
	
	@RequestMapping(path = "/upload/date-course", method = RequestMethod.POST)
	@ResponseBody
	public String saveDatePosting(DateCoursePost datePosting) throws Exception {
		User user = signService.getSigninUser();
		String editor = user.getName();
		
		if (StringUtils.isEmpty(editor) || StringUtils.equals(User.GUEST, editor)) {
			return "등록자 이름을 알 수 없습니다. 로그인을 해주세요";
		}
		
		datePosting.setEditor(editor);
		datePosting.setCondition(Condition.NORMAL.getCondition());
		
		datePostingService.saveDateImage(datePosting);
		datePostingService.save(datePosting);
        return "업로드 성공";
    }

    @RequestMapping(path = "/upload/date-course/content-image", method = RequestMethod.POST)
    public String uploadImage(@RequestParam("image") MultipartFile image, @RequestParam("region") String region, Model model) throws Exception {
        try {
            String resultPath = datePostingService.saveDateImage(image, region);

            if (StringUtils.isEmpty(resultPath)) {
                model.addAttribute("src", domain + "/error-image");
            } else {
                model.addAttribute("src", domain + resultPath);
            }
        } catch (Exception e) {
            logger.error("### 데이트코스 본문 이미지 올리기 실패", e);
            model.addAttribute("src", domain + "/error-image");
        }

        return "make/img";
    }
}

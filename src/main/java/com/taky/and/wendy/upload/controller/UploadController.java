package com.taky.and.wendy.upload.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UploadController {
	@PreAuthorize("hasRole('1')")
	@RequestMapping(path = "/upload/date-course", method = RequestMethod.GET)
	public String showUploadDateCourse() {
		return "/upload/date";
	}
}

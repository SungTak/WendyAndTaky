package com.taky.and.wendy.region.seoul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SeoulController {
	@RequestMapping(path = "/seoul", method = RequestMethod.GET)
	public String showSeoul() {
		return "region/seoul";
	}

	@RequestMapping(path = "/seoul/jongno", method = RequestMethod.GET)
	public String showSeoulJongno() {
		return "board/albumlist";
	}
}

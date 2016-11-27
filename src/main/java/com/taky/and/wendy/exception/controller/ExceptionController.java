package com.taky.and.wendy.exception.controller;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(FileSizeLimitExceededException.class)
	@ResponseBody
	public String fileSizeException() {
		return "파일이 용량이 커서 업로드 할 수 없습니다.";
	}
}

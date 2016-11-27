package com.taky.and.wendy.date.service;

import com.taky.and.wendy.date.model.DateCoursePost;
import org.springframework.web.multipart.MultipartFile;

public interface DateCoursePostService {
	public boolean saveDateImage(DateCoursePost datePosting) throws Exception;

    public String saveDateImage(MultipartFile image, String region) throws Exception;
    
    public boolean save(DateCoursePost datePosting) throws Exception;
}

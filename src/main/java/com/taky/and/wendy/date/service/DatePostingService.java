package com.taky.and.wendy.date.service;

import com.taky.and.wendy.date.model.DatePosting;
import org.springframework.web.multipart.MultipartFile;

public interface DatePostingService {
	public boolean saveDateImage(DatePosting datePosting) throws Exception;

    public String saveDateImage(MultipartFile image, String region) throws Exception;
    
    public boolean save(DatePosting datePosting) throws Exception;
}

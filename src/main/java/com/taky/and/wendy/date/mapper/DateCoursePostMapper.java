package com.taky.and.wendy.date.mapper;

import org.springframework.stereotype.Repository;

import com.taky.and.wendy.date.model.DateCoursePost;

@Repository
public interface DateCoursePostMapper {
	public long insertDateCoursePost(DateCoursePost datePosting) throws Exception;
}

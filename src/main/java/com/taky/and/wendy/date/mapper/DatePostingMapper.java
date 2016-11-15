package com.taky.and.wendy.date.mapper;

import org.springframework.stereotype.Repository;

import com.taky.and.wendy.date.model.DatePosting;

@Repository
public interface DatePostingMapper {
	public long insertDatePosting(DatePosting datePosting) throws Exception;
}

package com.bbms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bbms.dto.ActivityDto;
import com.bbms.repository.ActivityRepository;

public class ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;
	
	public void insert(ActivityDto dto) {

		activityRepository.save(dto);
	}
	
	public Optional<ActivityDto> selectActivityByTaskId(Long taskId) {
		
		return activityRepository.findById(taskId);
	}
	
}
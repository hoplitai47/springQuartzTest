package com.example.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Configuration
@EnableSpringConfigured
public class FutureTestService {

	public static final Logger Log = Logger.getLogger(FutureTestService.class);
	
	public FutureTestService() {
		Log.info("FutureTestService ctor()");
	}
	
	@Autowired
	private FutureTest futureTest;
	
}

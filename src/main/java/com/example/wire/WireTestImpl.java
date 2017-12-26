package com.example.wire;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.example.util.TableBuilder;

@Configurable
public class WireTestImpl implements WireTest {

	private static final Logger Log = LoggerFactory.getLogger(WireTestImpl.class);
	
	@Autowired
	TableBuilder builder;
	
	@Override
	public void test() {
		Log.info("launch test() from WireTestImpl");
		
		if(builder == null)
			Log.info("builder is null");
		else
			Log.info("builder is autowired");
	}
}

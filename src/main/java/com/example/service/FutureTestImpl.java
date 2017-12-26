package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.wire.WireTest;
import com.example.wire.WiringTestFactory;

@Service
public class FutureTestImpl implements FutureTest {
	
	public static final Logger Log = Logger.getLogger(FutureTestImpl.class);

	
	
	public FutureTestImpl() {
		Log.info("FutureTestImpl ctor()");
		
		test();
		
		WiringTestFactory factory = new WiringTestFactory();
		WireTest wireTest = factory.get();
		wireTest.test();
	}
	
	@Override
	public void test() {	
		CompletableFuture<String> future = 
				CompletableFuture.supplyAsync(() -> { return "Success"; } );
		
		future.completeExceptionally(new Exception("bad thing happend"));
		future = future.exceptionally(e -> { return "Success (Exceptional)";});
	
		
		String result;
		try {
			result = future.get();
		} catch (Exception e) {
			result = "Exeption handled.";
		} finally {
			
		}
		
		Log.info("result: " + result);
	}
	
	
	
}

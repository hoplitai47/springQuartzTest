package com.example;

import org.apache.log4j.Logger;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;


@SpringBootApplication
public class Program {

	public static final Logger Log = Logger.getLogger(Program.class);
	
	public static void main(String[] args) {
		Log.info("Starting program ---");
		SpringApplication.run(Program.class, args);
	}
	
}

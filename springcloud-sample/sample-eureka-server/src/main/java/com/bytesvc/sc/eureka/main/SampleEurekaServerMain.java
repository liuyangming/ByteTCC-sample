package com.bytesvc.sc.eureka.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SampleEurekaServerMain {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SampleEurekaServerMain.class).web(true).run(args);
	}

}

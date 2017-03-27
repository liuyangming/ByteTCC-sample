package com.bytesvc.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider4SpringDataJPAMain {

	static ClassPathXmlApplicationContext context = null;

	public static void main(String... args) throws Throwable {
		context = new ClassPathXmlApplicationContext("application-spring-data-jpa.xml");
		context.start();

		System.out.println("sample-provider(spring-data-jpa) started!");
	}

}

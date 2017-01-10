package com.bytesvc.service.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider4JPAMain {

	static ClassPathXmlApplicationContext context = null;

	public static void main(String... args) throws Throwable {
		context = new ClassPathXmlApplicationContext("application-hibernate.xml");
		context.start();

		System.out.println("sample-provider(hibernate) started!");
	}

}

package com.bytesvc.main;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = { "com.bytesvc.service", "com.bytesvc.config" })
public class ProviderMain {

	public static void main(String... args) throws Throwable {
		SpringApplication application = new SpringApplication(ProviderMain.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);

		System.out.println("sample-provider started!");
	}

}

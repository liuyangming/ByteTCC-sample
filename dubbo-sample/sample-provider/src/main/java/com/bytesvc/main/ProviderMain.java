package com.bytesvc.main;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = { "com.bytesvc.service", "com.bytesvc.config" })
@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class }) // 使用文件存储时, 不需要配置mongodb
public class ProviderMain {

	public static void main(String... args) throws Throwable {
		SpringApplication application = new SpringApplication(ProviderMain.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);

		System.out.println("sample-provider started!");
	}

}

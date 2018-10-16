package com.bytesvc.main;

import org.springframework.beans.BeansException;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 无业务逻辑, 启动应用以便ByteTCC执行恢复操作.
 */
@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = { "com.bytesvc.service", "com.bytesvc.config" })
public class RecoverConsumerMain implements ApplicationContextAware {

	static ApplicationContext context = null;

	public static void main(String... args) throws Throwable {
		SpringApplication application = new SpringApplication(RecoverConsumerMain.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);

		System.out.println("recover consumer started...");
	}

	public static void shutdown() {
		waitForMillis(1000 * 60);
		System.exit(0);
	}

	public static void waitForMillis(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

}

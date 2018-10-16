package com.bytesvc.main;

import org.springframework.beans.BeansException;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.bytesvc.service.ITransferService;

/**
 * 远程调用场景
 */
@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = { "com.bytesvc.service", "com.bytesvc.config" })
public class GenericConsumerMain implements ApplicationContextAware {
	static ApplicationContext context = null;

	public static void main(String... args) throws Throwable {
		SpringApplication application = new SpringApplication(GenericConsumerMain.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);

		try {
			ITransferService transferService = (ITransferService) context.getBean("genericTransferService");
			transferService.transfer("1001", "2001", 1.00d);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			shutdown();
		}
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

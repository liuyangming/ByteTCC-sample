package com.bytesvc.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bytesvc.service.ITransferService;

/**
 * 多数据源场景
 */
public class MultiDsConsumerMain {

	static ClassPathXmlApplicationContext context = null;

	public static void main(String... args) throws Throwable {
		startup();

		ITransferService transferSvc = (ITransferService) context.getBean("multiDsTransferService");
		try {
			transferSvc.transfer("1001", "2001", 1.00d);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			shutdown();
		}

	}

	public static void startup() {
		context = new ClassPathXmlApplicationContext("standalone.xml");
		context.start();
		waitForMillis(1000);
	}

	public static void waitForMillis(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public static void shutdown() {
		waitForMillis(1000 * 60);
		if (context != null) {
			context.close();
		}
		System.exit(0);
	}

}

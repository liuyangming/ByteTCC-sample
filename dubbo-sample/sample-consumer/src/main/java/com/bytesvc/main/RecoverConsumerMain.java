package com.bytesvc.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 无业务逻辑, 启动应用以便ByteTCC执行恢复操作.
 */
public class RecoverConsumerMain {

	static ClassPathXmlApplicationContext context = null;

	public static void main(String... args) throws Throwable {
		startup();

		try {
			waitForMillis(1000 * 3600);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			shutdown();
		}

	}

	public static void startup() {
		context = new ClassPathXmlApplicationContext("application.xml");
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
		if (context != null) {
			context.close();
		}
		System.exit(0);
	}

}

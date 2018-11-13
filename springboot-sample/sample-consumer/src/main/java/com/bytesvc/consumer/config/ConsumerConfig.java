package com.bytesvc.consumer.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.bytesoft.bytetcc.supports.springboot.config.SpringBootSecondaryConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 按请求粒度负载均衡(使用MongoDB存储事务日志):需引入SpringBootConfiguration; <br />
 * 按事务粒度负载均衡(使用文件系统存储事务日志):需引入SpringBootSecondaryConfiguration;
 */
@Import(SpringBootSecondaryConfiguration.class)
@Configuration
public class ConsumerConfig {

	/**
	 * 使用'按请求粒度负载均衡'策略时需要添加该配置.
	 */
	// @Bean // 使用文件存储事务日志时, 不需要配置zookeeper
	public CuratorFramework curatorFramework() throws InterruptedException {
		CuratorFramework curatorFramework = CuratorFrameworkFactory.builder() //
				.connectString("127.0.0.1:2181") //
				.sessionTimeoutMs(1000 * 3).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		curatorFramework.start();
		curatorFramework.blockUntilConnected();
		return curatorFramework;
	}

}

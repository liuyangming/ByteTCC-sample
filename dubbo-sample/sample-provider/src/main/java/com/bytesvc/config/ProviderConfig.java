package com.bytesvc.config;

import org.apache.curator.retry.ExponentialBackoffRetry;
import org.bytesoft.bytetcc.supports.dubbo.config.DubboSupportConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

@Import(DubboSupportConfiguration.class)
@Configuration
@DubboComponentScan(basePackages = { "com.bytesvc.service.impl", "com.bytesvc.service.confirm", "com.bytesvc.service.cancel" })
@EnableDubbo(scanBasePackages = { "com.bytesvc.service", "com.bytesvc.config" })
public class ProviderConfig {

	@Bean
	public org.apache.curator.framework.CuratorFramework curatorFramework() {
		org.apache.curator.framework.CuratorFramework curatorFramework = //
				org.apache.curator.framework.CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
						.sessionTimeoutMs(1000 * 6).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		curatorFramework.start();
		return curatorFramework;
	}

}

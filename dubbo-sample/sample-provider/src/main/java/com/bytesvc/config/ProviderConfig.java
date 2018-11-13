package com.bytesvc.config;

import org.apache.curator.retry.ExponentialBackoffRetry;
import org.bytesoft.bytetcc.supports.dubbo.config.DubboSupportConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

/**
 * 按请求粒度负载均衡(使用MongoDB存储事务日志):需引入DubboSupportConfiguration; <br />
 * 按事务粒度负载均衡(使用文件系统存储事务日志):需引入DubboSecondaryConfiguration;
 */
@Import(DubboSupportConfiguration.class)
@Configuration
@DubboComponentScan(basePackages = { "com.bytesvc.service.impl", "com.bytesvc.service.confirm", "com.bytesvc.service.cancel" })
@EnableDubbo(scanBasePackages = { "com.bytesvc.service", "com.bytesvc.config" })
public class ProviderConfig {

	/**
	 * 使用'按请求粒度负载均衡'策略时需要添加该配置.
	 */
	@Bean
	public org.apache.curator.framework.CuratorFramework curatorFramework() {
		org.apache.curator.framework.CuratorFramework curatorFramework = //
				org.apache.curator.framework.CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
						.sessionTimeoutMs(1000 * 6).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		curatorFramework.start();
		return curatorFramework;
	}

}

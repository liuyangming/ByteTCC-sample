package com.bytesvc.consumer.config;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.bytesoft.bytetcc.supports.springboot.config.SpringBootConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClients;

@Import(SpringBootConfiguration.class)
@Configuration
public class ConsumerConfig {

	@ConditionalOnMissingBean(com.mongodb.client.MongoClient.class)
	@Bean
	public com.mongodb.client.MongoClient mongoClient(@Autowired com.mongodb.MongoClient mongoClient) {
		List<ServerAddress> addressList = mongoClient.getAllAddress();
		StringBuilder ber = new StringBuilder();
		for (int i = 0; addressList != null && i < addressList.size(); i++) {
			ServerAddress address = addressList.get(i);
			String host = address.getHost();
			int port = address.getPort();
			if (i == 0) {
				ber.append(host).append(":").append(port);
			} else {
				ber.append(",").append(host).append(":").append(port);
			}
		}
		return MongoClients.create(String.format("mongodb://%s", ber.toString()));
	}

	@Bean
	public CuratorFramework curatorFramework() throws InterruptedException {
		CuratorFramework curatorFramework = CuratorFrameworkFactory.builder() //
				.connectString("127.0.0.1:2181") //
				.sessionTimeoutMs(1000 * 3).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		curatorFramework.start();
		curatorFramework.blockUntilConnected();
		return curatorFramework;
	}

	@Primary
	@Bean(name = "dataSource")
	public DataSource invokeGetDataSource() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setUrl("jdbc:mysql://127.0.0.1:3306/inst02");
		bds.setUsername("root");
		bds.setPassword("123456");
		bds.setMaxTotal(50);
		bds.setInitialSize(20);
		bds.setMaxWaitMillis(60000);
		bds.setMinIdle(6);
		bds.setLogAbandoned(true);
		bds.setRemoveAbandonedOnBorrow(true);
		bds.setRemoveAbandonedOnMaintenance(true);
		bds.setRemoveAbandonedTimeout(1800);
		bds.setTestWhileIdle(true);
		bds.setTestOnBorrow(false);
		bds.setTestOnReturn(false);
		bds.setValidationQuery("select 'x' ");
		bds.setValidationQueryTimeout(1);
		bds.setTimeBetweenEvictionRunsMillis(30000);
		bds.setNumTestsPerEvictionRun(20);
		return bds;
	}

	@Bean(name = "dataSource2")
	public DataSource invokeGetDataSource2() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setUrl("jdbc:mysql://127.0.0.1:3306/inst01");
		bds.setUsername("root");
		bds.setPassword("123456");
		bds.setMaxTotal(50);
		bds.setInitialSize(20);
		bds.setMaxWaitMillis(60000);
		bds.setMinIdle(6);
		bds.setLogAbandoned(true);
		bds.setRemoveAbandonedOnBorrow(true);
		bds.setRemoveAbandonedOnMaintenance(true);
		bds.setRemoveAbandonedTimeout(1800);
		bds.setTestWhileIdle(true);
		bds.setTestOnBorrow(false);
		bds.setTestOnReturn(false);
		bds.setValidationQuery("select 'x' ");
		bds.setValidationQueryTimeout(1);
		bds.setTimeBetweenEvictionRunsMillis(30000);
		bds.setNumTestsPerEvictionRun(20);
		return bds;
	}

	@Primary
	@Bean
	public JdbcTemplate getJdbcTemplate(@Autowired @Qualifier("dataSource") DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate2(@Autowired @Qualifier("dataSource2") DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}

}

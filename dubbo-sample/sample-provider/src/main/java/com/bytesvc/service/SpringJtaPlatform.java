package com.bytesvc.service;

import org.bytesoft.bytetcc.supports.dubbo.CompensableBeanRegistry;
import org.bytesoft.compensable.CompensableBeanFactory;
import org.hibernate.service.jta.platform.internal.AbstractJtaPlatform;

public class SpringJtaPlatform extends AbstractJtaPlatform {
	private static final long serialVersionUID = 1L;

	protected javax.transaction.TransactionManager locateTransactionManager() {
		CompensableBeanRegistry registry = CompensableBeanRegistry.getInstance();
		CompensableBeanFactory beanFactory = registry.getBeanFactory();
		return beanFactory == null ? null : beanFactory.getTransactionManager();
	}

	protected javax.transaction.UserTransaction locateUserTransaction() {
		return null;
	}
}
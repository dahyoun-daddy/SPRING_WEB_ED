package com.sist.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionAdvice implements MethodInterceptor {
private Logger log = LoggerFactory.getLogger(TransactionAdvice.class);	
PlatformTransactionManager transactionManager;
    
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	} 

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		TransactionStatus status=
		this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			log.debug("1.^^^^^^^^"+invocation.getMethod());
			Object ret = invocation.proceed();
			this.transactionManager.commit(status);
			log.debug("2.^^^^^^^^ Commit");
			return ret;
		}catch(RuntimeException e) {
			log.debug("2.^^^^^^^^ rollback");
			this.transactionManager.rollback(status);
			log.debug("2.1^^^^^^^^ rollback");
			throw e;
		}
	}

}

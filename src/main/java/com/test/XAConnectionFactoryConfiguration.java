package com.test;

import org.apache.activemq.artemis.jms.client.ActiveMQXAConnectionFactory;
import org.jboss.narayana.jta.jms.ConnectionFactoryProxy;
import org.jboss.narayana.jta.jms.TransactionHelperImpl;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.transaction.TransactionManager;

@Dependent()
public class XAConnectionFactoryConfiguration {

    @Produces()
    @Named(value = "xaConnectionFactory")
    public ConnectionFactoryProxy getXAConnectionFactory(TransactionManager tm) {
        ActiveMQXAConnectionFactory cf = new ActiveMQXAConnectionFactory("tcp://localhost:49561", "admin", "admin123.");
        return new ConnectionFactoryProxy(cf, new TransactionHelperImpl(tm));
    }
}

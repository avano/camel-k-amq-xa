package com.test;

import org.apache.activemq.artemis.jms.client.ActiveMQXAConnectionFactory;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.narayana.jta.jms.ConnectionFactoryProxy;
import org.jboss.narayana.jta.jms.TransactionHelperImpl;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.transaction.TransactionManager;

@Dependent
// TODO: to be removed as soon as it's possible to add `quarkus.arc.remove-unused-beans=framework` to Quarkus build configuration in Camel K
@io.quarkus.arc.Unremovable
public class XAConnectionFactoryConfiguration {
    @ConfigProperty(name = "broker.url")
    String url;
    @ConfigProperty(name = "broker.username")
    String user;
    @ConfigProperty(name = "broker.password")
    String password;

    @Produces()
    @Named(value = "xaConnectionFactory")
    public ConnectionFactoryProxy getXAConnectionFactory(TransactionManager tm) {
        ActiveMQXAConnectionFactory cf = new ActiveMQXAConnectionFactory(url, user, password);
        return new ConnectionFactoryProxy(cf, new TransactionHelperImpl(tm));
    }
}

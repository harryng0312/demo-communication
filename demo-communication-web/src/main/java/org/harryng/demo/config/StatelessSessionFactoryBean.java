//package org.harryng.demo.config;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.hibernate.SessionFactory;
//import org.hibernate.StatelessSession;
//import org.springframework.aop.framework.ProxyFactory;
//import org.springframework.beans.factory.FactoryBean;
//
//public class StatelessSessionFactoryBean implements FactoryBean<StatelessSession> {
//    private final EntityManagerFactory entityManagerFactory;
//    private final SessionFactory sessionFactory;
//
//    public StatelessSessionFactoryBean(EntityManagerFactory entityManagerFactory) {
//        this.entityManagerFactory = entityManagerFactory;
//        this.sessionFactory = (SessionFactory) entityManagerFactory;
//    }
//
//    @Override
//    public StatelessSession getObject() throws Exception {
//        final StatelessSessionInterceptor statelessSessionInterceptor =
//                new StatelessSessionInterceptor(entityManagerFactory, sessionFactory);
//        return ProxyFactory.getProxy(StatelessSession.class, statelessSessionInterceptor);
//    }
//
//    @Override
//    public Class<?> getObjectType() {
//        return StatelessSession.class;
//    }
//
//    @Override
//    public boolean isSingleton() {
//        return true;
//    }
//}

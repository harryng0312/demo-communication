//package org.harryng.demo.config;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.StatelessSession;
//import org.springframework.transaction.support.TransactionSynchronization;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//import static org.springframework.orm.jpa.EntityManagerFactoryUtils.ENTITY_MANAGER_SYNCHRONIZATION_ORDER;
//
//public class StatelessSessionSynchronization implements TransactionSynchronization {
//    private final SessionFactory sessionFactory;
//    private final StatelessSession statelessSession;
//
//    public StatelessSessionSynchronization(SessionFactory sessionFactory, StatelessSession statelessSession) {
//        this.sessionFactory = sessionFactory;
//        this.statelessSession = statelessSession;
//    }
//
//    @Override
//    public int getOrder() {
//        return ENTITY_MANAGER_SYNCHRONIZATION_ORDER - 100;
//    }
//
//    @Override
//    public void beforeCommit(boolean readOnly) {
//        if (!readOnly) {
////            ((TransactionContext) statelessSession).managedFlush();
//        }
//    }
//
//    @Override
//    public void beforeCompletion() {
//        TransactionSynchronizationManager.unbindResource(sessionFactory);
//        statelessSession.close();
//    }
//
//}

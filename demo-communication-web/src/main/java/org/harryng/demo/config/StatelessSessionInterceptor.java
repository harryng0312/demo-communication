//package org.harryng.demo.config;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import org.aopalliance.intercept.MethodInterceptor;
//import org.aopalliance.intercept.MethodInvocation;
//import org.hibernate.SessionFactory;
//import org.hibernate.StatelessSession;
//import org.hibernate.internal.SessionImpl;
//import org.springframework.orm.jpa.EntityManagerFactoryUtils;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import static org.springframework.util.ReflectionUtils.invokeMethod;
//
//public class StatelessSessionInterceptor implements MethodInterceptor {
//    private final EntityManagerFactory entityManagerFactory;
//    private final SessionFactory sessionFactory;
//
//    public StatelessSessionInterceptor(EntityManagerFactory entityManagerFactory, SessionFactory sessionFactory) {
//        this.entityManagerFactory = entityManagerFactory;
//        this.sessionFactory = sessionFactory;
//    }
//
//    private StatelessSession getCurrentSession() throws SQLException {
//        if (!TransactionSynchronizationManager.isActualTransactionActive()) {
//            throw new IllegalStateException(
//                    "There should be an active transaction for the current thread.");
//        }
//        StatelessSession statelessSession = null;//(StatelessSession) TransactionSynchronizationManager.getResource(sessionFactory);
////        if (statelessSession == null) {
//        statelessSession = openNewStatelessSession();
//        bindWithTransaction(statelessSession);
////        }
//        return statelessSession;
//    }
//
//    private StatelessSession openNewStatelessSession() throws SQLException {
//        final Connection connection = obtainPhysicalConnection();
//        return sessionFactory.openStatelessSession(connection);
//    }
//
//    /**
//     * It is important we obtain the physical (real) connection otherwise it
//     * will be double proxied and there will be problems releasing the
//     * connection.
//     */
//    private Connection obtainPhysicalConnection() throws SQLException {
//        EntityManager entityManager = EntityManagerFactoryUtils
//                .getTransactionalEntityManager(entityManagerFactory);
////        SessionImplementor sessionImplementor = (SessionImplementor) entityManager
////                .getDelegate();
////        return sessionImplementor.getTransactionCoordinator().getJdbcCoordinator()
////                .getLogicalConnection().getConnection();
////        sessionImplementor.getTransactionCoordinator()
//        return ((SessionImpl) entityManager.getDelegate()).getJdbcConnectionAccess().obtainConnection();
//    }
//
//    private void bindWithTransaction(StatelessSession statelessSession) {
//        TransactionSynchronizationManager
//                .registerSynchronization(new StatelessSessionSynchronization(sessionFactory, statelessSession));
//        TransactionSynchronizationManager.unbindResourceIfPossible(sessionFactory);
//        TransactionSynchronizationManager.bindResource(sessionFactory, statelessSession);
//    }
//
//    @Override
//    public Object invoke(MethodInvocation invocation) throws Throwable {
//        final StatelessSession statelessSession = getCurrentSession();
//        return invokeMethod(invocation.getMethod(), statelessSession, invocation.getArguments());
//    }
//}

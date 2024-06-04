//package org.harryng.demo.config;
//
////@EnableAspectJAutoProxy(proxyTargetClass = true)
////@Aspect
////@Component
////@Slf4j
//public class AopConfig {
//    private static final String persistenceOperationExpr = "execution(public * org.harryng.demo..persistence.*.*(..)) "
//            + "&& !execution(public * getEntityManager(..)) &amp;&amp; !execution(public * getEntityClass())";
//    private static final String serviceOperationExpr = "execution(public * org.harryng.demo..service.*.*(org.harryng.demo.api.util.SessionHolder,..)) "
//            + "&& !@annotation(org.springframework.transaction.annotation.Transactional)";
//    private static final String rsControllerOperationExpr = "execution(public * org.harryng.demo.controller.rs.*.*(org.harryng.demo.api.util.SessionHolder,..))";
//    private static final String grpcControllerOperationExpr = "execution(public * org.harryng.demo.controller.grpc.*.*(..))";
//
//
//}

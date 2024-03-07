package org.harryng.demo.config;

import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

//@Configurable
//@EnableTransactionManagement
//@EnableJpaRepositories("org.harryng.demo.**.persistences")
public class StatelessSessionConfig implements TransactionManagementConfigurer {
    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return null;
    }

//    @Bean
    public StatelessSessionFactoryBean statelessSessionFactory() {
//        return new StatelessSessionFactoryBean();
        return null;
    }
}

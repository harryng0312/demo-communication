package org.harryng.demo.config;

import org.harryng.demo.config.typeresolver.ResultTypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLConfig {
    private final ResultTypeResolver resultTypeResolver;

    public GraphQLConfig(ResultTypeResolver resultTypeResolver) {
        this.resultTypeResolver = resultTypeResolver;
    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .type("Result", typeWiring -> typeWiring.typeResolver(resultTypeResolver));
    }
}

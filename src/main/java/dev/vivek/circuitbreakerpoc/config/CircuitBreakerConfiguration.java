package dev.vivek.circuitbreakerpoc.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerConfiguration {
    @Bean
    public CircuitBreakerConfig cbConfigTRW() {
        return CircuitBreakerConfig.custom()
                .minimumNumberOfCalls(3)
                .failureRateThreshold(50.0f)
                .waitDurationInOpenState(java.time.Duration.ofMillis(1000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(2)
                .build();
    }

    @Bean
    public CircuitBreakerRegistry getCircuitBreakerRegistry() {
        return CircuitBreakerRegistry.of(cbConfigTRW());
    }

    @Bean
    public CircuitBreaker circuitBreakerTRW() {
        return getCircuitBreakerRegistry().circuitBreaker("abc",cbConfigTRW());
    }
    /*@Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "circuit-breaker-poc");
    }*/
}
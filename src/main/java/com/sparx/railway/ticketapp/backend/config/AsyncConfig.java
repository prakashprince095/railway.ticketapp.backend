package com.sparx.railway.ticketapp.backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);  // Set the core number of threads
        executor.setMaxPoolSize(10);  // Set the maximum number of threads
        executor.setQueueCapacity(25);  // Set the capacity of the queue
        executor.setThreadNamePrefix("Async-");  // Prefix for thread names
        executor.initialize();
        return executor;
    }
}

package com.anakin.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import java.util.concurrent.Executor;

/**
 * @description: 异步线程配置
 * @author: Anakin
 * @date 2021.9.6 17:12
 */
@Configuration
public class AsyncConfig {

    @Value("${spring.application.name}")
    private String applicationName;
    public static final String ASYNC_EXECUTOR_NAME = "asyncExecutor";

    @Bean(name = ASYNC_EXECUTOR_NAME)
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 复制上下文信息
        executor.setTaskDecorator(new ContextCopyingDecorator());
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(100);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix(applicationName + "::AsyncThread-");
        executor.initialize();
        return executor;
    }

    private static class ContextCopyingDecorator implements TaskDecorator {
        @Override
        public Runnable decorate(Runnable runnable) {
            RequestAttributes context = RequestContextHolder.currentRequestAttributes();
            return () -> {
                try {
                    RequestContextHolder.setRequestAttributes(context);
                    runnable.run();
                } finally {
                    RequestContextHolder.resetRequestAttributes();
                }
            };
        }
    }
}

package testorio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import testorio.core.events.EventSource;

/**
 * Created by def on 05.01.17.
 */

@Configuration
@ComponentScan(basePackages = "testorio.modules",
        includeFilters = @ComponentScan.Filter({EventSource.class}))
public class ApplicationConfig {
    @Bean
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(5);
        pool.setMaxPoolSize(10);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }
}

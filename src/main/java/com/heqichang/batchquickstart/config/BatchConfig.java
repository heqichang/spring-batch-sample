package com.heqichang.batchquickstart.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@MapperScan("com.heqichang.batchquickstart.mapper")
public class BatchConfig {

    @Bean
    public BatchConfigurer batchConfigurer(DataSource dataSource) {
        return new DefaultBatchConfigurer(dataSource) {

            @Override
            protected JobLauncher createJobLauncher() throws Exception {
                SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
                jobLauncher.setJobRepository(getJobRepository());
                jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
                jobLauncher.afterPropertiesSet();
                return jobLauncher;
            }
        };
    }

    /**
     * 注册JobRegistryBeanPostProcessor bean
     * 用于将任务名称和实际的任务关联起来
     */
    @Bean
    public JobRegistryBeanPostProcessor processor(JobRegistry jobRegistry, ApplicationContext applicationContext) {
        JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
        postProcessor.setJobRegistry(jobRegistry);
        postProcessor.setBeanFactory(applicationContext.getAutowireCapableBeanFactory());
        return postProcessor;
    }

}

package com.heqichang.batchquickstart.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 完全映射
 */
@Component
public class ExampleJob6 {


    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((cs, cc) -> {
                    System.out.println("step1");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("exampleJob6")
                .start(step1())
                .build();
    }


}

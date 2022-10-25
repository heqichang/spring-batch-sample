package com.heqichang.batchquickstart.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExampleJob3 {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;


    @Autowired
    public void setJobBuilderFactory(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Autowired
    public void setStepBuilderFactory(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    public Job exampleJob() {
        return jobBuilderFactory.get("exampleJob3")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .next(step2())
                .build();
    }

    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((c,b) -> {

                    System.out.println("example3 step1");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((c,b) -> {

                    System.out.println("example step2");
                    return RepeatStatus.FINISHED;

                }).build();
    }

}

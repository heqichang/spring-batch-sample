package com.heqichang.batchquickstart.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class ExampleJob4 {


    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    private List<String> exampleList = new ArrayList<>(4);

    private Integer index = 0;

    @Autowired
    public ExampleJob4(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;

        this.exampleList.addAll(Arrays.asList("a", "b", "c", "d"));
    }


    public ItemReader<String> itemReader() {
        return () -> {
            if (index < exampleList.size()) {
                System.out.println("reader: " + exampleList.get(index));
                return exampleList.get(index++);
            } else {
                index = 0;
            }

            return null;
        };
    }

    public ItemProcessor<String, String> processor() {
        return i -> {
            System.out.println("processor");
            Thread.sleep(1000);
            return i.toUpperCase();
        };
    }

    public ItemWriter<String> itemWriter() {
        return list -> {
            for (String str: list) {
                System.out.println("writer: " + str);
            }
        };
    }

    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(2)
                .reader(itemReader())
                .processor(processor())
                .writer(itemWriter())
                .build();
    }

    public Job job() {
        return jobBuilderFactory.get("exampleJob4")
                .start(step1())
                .build();
    }


}

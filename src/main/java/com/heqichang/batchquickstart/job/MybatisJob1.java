package com.heqichang.batchquickstart.job;

import com.heqichang.batchquickstart.entity.Admin;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
// 如果开启 modular 这里可以不用注解 Configuration，如果要的话需要在 web 容器下排除这里的扫描，不然会重复 bean 错误
public class MybatisJob1 {


    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public MyBatisCursorItemReader<Admin> itemReader() {
        return new MyBatisCursorItemReaderBuilder<Admin>()
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("com.heqichang.batchquickstart.mapper.AdminMapper.getAll")
                .build();
    }

    public ItemWriter<Admin> itemWriter() {
        return list -> {
            for (Admin admin: list) {
                System.out.println(admin);
            }
        };
    }

    @Bean("step2")
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Admin, Admin> chunk(2)
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Job job(@Qualifier("step2") Step step) {
        return jobBuilderFactory.get("mybatisJob2")
                .start(step)
                .build();
    }

}

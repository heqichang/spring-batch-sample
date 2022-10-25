package com.heqichang.batchquickstart.job;

import com.heqichang.batchquickstart.entity.Admin;
import com.heqichang.batchquickstart.rowmapper.AdminRowMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

/**
 * 数据库读取
 */
@Configuration
public class ExampleJob5 {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    private DataSource dataSource;

    @Autowired
    public void setJobBuilderFactory(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Autowired
    public void setStepBuilderFactory(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JdbcCursorItemReader<Admin> itemReader() {
        return new JdbcCursorItemReaderBuilder<Admin>()
                .dataSource(dataSource)
                .name("adminReader")
                .sql("select * from admin")
                .rowMapper(new AdminRowMapper())
                .build();
    }

    public ItemWriter<Admin> itemWriter() {
        return list -> {
            System.out.println("writer");
            for(Admin admin: list) {
                System.out.println(admin);
            }
        };
    }


    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Admin, Admin> chunk(2)
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }

    public Job job() {
        return jobBuilderFactory.get("exampleJob5")
                .start(step1())
                .build();
    }

}

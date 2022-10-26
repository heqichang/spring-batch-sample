package com.heqichang.batchquickstart.controller;

import com.heqichang.batchquickstart.entity.Admin;
import com.heqichang.batchquickstart.job.ExampleJob2;
import com.heqichang.batchquickstart.job.ExampleJob3;
import com.heqichang.batchquickstart.job.ExampleJob4;
import com.heqichang.batchquickstart.job.ExampleJob5;
import com.heqichang.batchquickstart.mapper.AdminMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class JobController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    ExampleJob2 job2;

    @Autowired
    ExampleJob3 job3;

    @Autowired
    ExampleJob4 job4;

//    @Autowired
//    ExampleJob5 job5;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    private JobOperator jobOperator;

    @Autowired
    private JobRegistry jobRegistry;

    @Value("@{source.}")
    private String name;

    @RequestMapping("/simple")
    public void simpleJob() throws Exception {
        jobLauncher.run(job2.job(), new JobParameters());
        System.out.println("simple");
    }

    @RequestMapping("/job3")
    public void simpleJob3() throws Exception {
        jobLauncher.run(job3.exampleJob(), new JobParameters());
        System.out.println("job3");
    }

    @RequestMapping("/job4")
    public void simpleJob4() throws Exception {
        JobParametersBuilder builder = new JobParametersBuilder();
        Date date = new Date();
        System.out.println(date);
        builder.addDate("date", date);
        jobLauncher.run(job4.job(), builder.toJobParameters());
    }

    @RequestMapping("/map")
    public void map() {

        var list = adminMapper.getAll();
        for (Admin admin :
                list) {
            System.out.println(admin);
        }

//        Integer insert = adminMapper.insert("shit", "123");

        System.out.println(list);

//        System.out.println(insert);

    }

    @RequestMapping("/job5")
    public void simpleJob5() throws Exception {

//        JobParametersBuilder builder = new JobParametersBuilder();
//        Date date = new Date();
//        System.out.println(date);
//        builder.addDate("date", date);
//        jobLauncher.run(job5.job(), builder.toJobParameters());
    }

    @RequestMapping("/job6")
    public String simpleJob6() throws Exception {
        jobOperator.start("exampleJob6", "");
        return "success";
    }

    @RequestMapping("/job7")
    public String simpleJob7() throws Exception {
        JobParametersBuilder builder = new JobParametersBuilder();
        Date date = new Date();
        System.out.println(date);
        builder.addDate("date", date);

        Job job2 = jobRegistry.getJob("exampleJob6");
        System.out.println(job2);
        jobLauncher.run(job2, builder.toJobParameters());
        return "success";
    }

    @RequestMapping("/mjob1")
    public String mJob1() throws Exception {
        JobParametersBuilder builder = new JobParametersBuilder();
        Date date = new Date();
        System.out.println(date);
        builder.addDate("date", date);

        Job job2 = jobRegistry.getJob("mybatisJob2");
        System.out.println(job2);
        jobLauncher.run(job2, builder.toJobParameters());
        return "success";
    }

}

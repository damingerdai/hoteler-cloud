package org.daming.hoteler.worker.config;

import org.daming.hoteler.worker.task.AuthPingJob;
import org.quartz.*;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class QuartzConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public JobDetail jobDetail() {
        JobDetail jobDetail = JobBuilder.newJob(AuthPingJob.class)
                .withIdentity("AuthPingJob")
                .storeDurably(true)
                .build();

        return  jobDetail;
    }

    @Bean
    public Trigger pingTrigger(JobDetail jobDetail) {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/20 * * * * ?");
        // CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 */1 * * ?");

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("AuthPingJob-Trigger")
                .withSchedule(scheduleBuilder)
                .startNow()
                .build();

        return trigger;
    }
}

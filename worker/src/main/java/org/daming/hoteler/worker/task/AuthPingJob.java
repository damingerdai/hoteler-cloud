package org.daming.hoteler.worker.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthPingJob extends QuartzJobBean {

    private final Logger logger = LoggerFactory.getLogger(AuthPingJob.class);

    private RestTemplate restTemplate;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        var authUrl = "http://auth-service/ping";
        var response = this.restTemplate.getForEntity(authUrl, String.class);
        Assert.isTrue(response.getStatusCode() == HttpStatus.OK, response.toString());
        this.logger.info("http://auth-service/ping ==》" + response.toString());
        System.out.println("http://auth-service/ping ==》" + response.getBody());
    }

    public AuthPingJob(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}

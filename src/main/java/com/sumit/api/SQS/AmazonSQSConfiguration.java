package com.sumit.api.SQS;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 Configuration class to get an object of the Queue Messaging template
 By using this template we can communicate with our Amazon SQS queue.
 */
@Configuration
public class AmazonSQSConfiguration {

    private final Logger log = LoggerFactory.getLogger(AmazonSQSConfiguration.class);

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.credentials.access-key}")
    private String awsAccessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String awsSecretKey;

    @Bean
    @Primary
    public QueueMessagingTemplate queueMessagingTemplate() {
        return new QueueMessagingTemplate(buildAmazonSQSAsync());
    }

    public AmazonSQSAsync buildAmazonSQSAsync() {
        log.info("Entry AwsSQSConfig ...");
        log.info("region = "+region);
        log.info("awsAccessKey = "+awsAccessKey);
        log.info("awsSecretKey = "+awsSecretKey);
        AmazonSQSAsync amazonSQSAsync = AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .build();
        log.info("amazonSQSAsync = "+amazonSQSAsync);
        log.info("Exit AwsSQSConfig !!!");
        return amazonSQSAsync;
    }



}
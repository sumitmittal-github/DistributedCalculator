package com.sumit.api.controller;

import com.sumit.api.SQS.AmazonSQSMessage;
import com.sumit.api.service.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/calculator")
public class CalculatorController {

    private final Logger log = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    private CalculatorService calculatorService;

    @SqsListener("SQS-Queue")
    public void loadMessagesFromSQSQueue(String message){
        log.info("Entry CalculatorController.loadMessagesFromSQSQueue() ...");
        log.info("Amazon SQS message :"+message);
        calculatorService.calculateAndStoreInDB(message);
        log.info("Exit CalculatorController.loadMessagesFromSQSQueue() !!!");
    }

}
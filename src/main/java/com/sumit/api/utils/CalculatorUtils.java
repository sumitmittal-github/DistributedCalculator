package com.sumit.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumit.api.SQS.AmazonSQSMessage;
import com.sumit.api.entity.OperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CalculatorUtils {

    private final Logger log = LoggerFactory.getLogger(CalculatorUtils.class);

    public Double calculate(AmazonSQSMessage amazonSQSMessage){
        log.info("Entry CalculatorUtils.calculate() ...");
        log.info("AmazonSQSMessage :"+amazonSQSMessage);
        Double result = 0.0;
        if(OperationType.ADD == amazonSQSMessage.getOperationType()) {
            result = amazonSQSMessage.getInputs().stream().reduce(0.0, Double::sum);
            log.info("Result of addition is : " + result);
        }
        else if(OperationType.MULTIPLY == amazonSQSMessage.getOperationType()) {
            result = amazonSQSMessage.getInputs().stream().reduce(1.0, (a, b) -> a * b);
            log.info("Result of Multiplication is : " + result);
        }
        else if(OperationType.SUBTRACT == amazonSQSMessage.getOperationType()) {
            result = amazonSQSMessage.getInputs().get(0) - amazonSQSMessage.getInputs().get(1);
            log.info("Result of Subtraction is : " + result);
        }
        else if(OperationType.DIVIDE == amazonSQSMessage.getOperationType()) {
            result = amazonSQSMessage.getInputs().get(0) / amazonSQSMessage.getInputs().get(1);
            log.info("Result of Division is : " + result);
        }
        log.info("Exit CalculatorService.calculate() !!!");
        return result;
    }

    public AmazonSQSMessage parseJsonIntoAmazonSQSMessage(String jsonMessage) {
        log.info("Entry CalculatorUtils.parseStringIntoAmazonSQSMessage() ...");
        log.info("AmazonSQSMessage JSON :"+jsonMessage);
        AmazonSQSMessage amazonSQSMessage = null;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            amazonSQSMessage = objectMapper.readValue(jsonMessage, AmazonSQSMessage.class);
            log.info("AmazonSQSMessage Object :"+amazonSQSMessage);
        } catch(Exception e){
            log.error("Exception occurred while parsing JSON into Object :"+e.getMessage());
        }
        log.info("Exit CalculatorService.validateAndSendSQSMessage() !!!");
        return amazonSQSMessage;
    }
}
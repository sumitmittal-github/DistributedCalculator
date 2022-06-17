package com.sumit.api.utils;

import com.sumit.api.SQS.AmazonSQSMessage;
import com.sumit.api.entity.OperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CalculatorUtils {

    private final Logger log = LoggerFactory.getLogger(CalculatorUtils.class);

    public Double calculate(AmazonSQSMessage amazonSQSMessage){
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
        return result;
    }

    public AmazonSQSMessage parseStringIntoAmazonSQSMessage(String message) {
        AmazonSQSMessage amazonSQSMessage = new AmazonSQSMessage();

        return amazonSQSMessage;
    }
}
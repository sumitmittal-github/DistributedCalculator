package com.sumit.api.service;

import com.sumit.api.entity.Calculation;
import com.sumit.api.SQS.AmazonSQSMessage;
import com.sumit.api.repository.CalculatorRepository;
import com.sumit.api.utils.CalculatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CalculatorService {

    private final Logger log = LoggerFactory.getLogger(CalculatorService.class);

    @Autowired
    private CalculatorRepository calculatorRepository;

    @Autowired
    private CalculatorUtils calculatorUtils;

    public void calculateAndStoreInDB(String message){
        log.info("Entry CalculatorService.calculateAndStoreInDB() ...");
        //STEP-1 : parse String message to Amazon SQS message
        AmazonSQSMessage amazonSQSMessage = calculatorUtils.parseJsonIntoAmazonSQSMessage(message);

        //STEP-2 : calculate the result
        Double result = calculatorUtils.calculate(amazonSQSMessage);
        log.info("Calculation result :"+result);

        //STEP-3 : Prepare Calculation Entity object for Database
        Calculation calculation = new Calculation();
        calculation.setOperationType(amazonSQSMessage.getOperationType().toString());
        String commaSeparatedInputs = amazonSQSMessage.getInputs().stream()
                                                                  .map(a->a.toString())
                                                                  .collect(Collectors.joining(","));
        calculation.setInputStr(commaSeparatedInputs);
        calculation.setResult(result);
        log.info("Calculation DB Object :"+calculation);

        //STEP-4 : Save result in DB
        log.info("Saving Calculation Object in DB ..");
        calculatorRepository.save(calculation);
        log.info("Saved Calculation Object in DB !!");
        log.info("Exit CalculatorService.calculateAndStoreInDB() !!!");
    }


}
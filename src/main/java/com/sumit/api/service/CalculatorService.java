package com.sumit.api.service;

import com.sumit.api.entity.Calculation;
import com.sumit.api.SQS.AmazonSQSMessage;
import com.sumit.api.repository.CalculatorRepository;
import com.sumit.api.utils.CalculatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculatorService {

    private final Logger log = LoggerFactory.getLogger(CalculatorService.class);

    @Autowired
    private CalculatorRepository calculatorRepository;

    @Autowired
    private CalculatorUtils calculatorUtils;

    private static List<Calculation> databaseCalculationsList;

    public void calculateAndStoreInDB(String message){
        log.info("Entry CalculatorService.calculateAndStoreInDB() ...");
        //STEP-1 : parse String message to Amazon SQS message
        AmazonSQSMessage amazonSQSMessage = calculatorUtils.parseJsonIntoAmazonSQSMessage(message);

        //STEP-2 : If we have previously calculated this calculation then reuse the same result
        Calculation calculation = null;
        Calculation previousCalculation = calculatorUtils.checkAndReturnIfAlreadyCalculated(amazonSQSMessage, databaseCalculationsList);
        if(previousCalculation == null){
            log.info("===> PreviousCalculation is Null hence calculate the results");
            //STEP-2.1 : calculate the result only if previously not calculated (means not in the database)
            Double result = calculatorUtils.calculate(amazonSQSMessage);
            log.info("Calculation result :"+result);

            //STEP-2.2 : Prepare Calculation Entity object for Database
            calculation = createCalculationDBObject(amazonSQSMessage, result);
            log.info("Calculation DB Object :"+calculation);
        } else{
            log.info("===> PreviousCalculation is not Null hence reuse the results");
            //STEP-2.1 : Prepare Calculation Entity object for Database
            calculation = new Calculation(previousCalculation.getOperationType(), previousCalculation.getInputStr(), previousCalculation.getResult());
        }

        //STEP-3 : Save result in DB
        log.info("Saving Calculation Object in DB ..");
        calculatorRepository.save(calculation);
        databaseCalculationsList.add(calculation);  // also save in cached Map so that next time do not calculate again
        log.info("Saved Calculation Object in DB !!");
        log.info("Exit CalculatorService.calculateAndStoreInDB() !!!");
    }

    @PostConstruct
    public void loadCalculationsFromDB() {
        log.info("==========================================");
        databaseCalculationsList = calculatorRepository.findAll();
        log.info("Database calculationList : "+databaseCalculationsList.size());
        log.info("##########################################");
    }

    private Calculation createCalculationDBObject(AmazonSQSMessage amazonSQSMessage, Double result) {
        Calculation calculation = new Calculation();
        calculation.setOperationType(amazonSQSMessage.getOperationType().toString());
        String commaSeparatedInputs = amazonSQSMessage.getInputs().stream()
                                                                  .map(a->a.toString())
                                                                  .collect(Collectors.joining(","));
        calculation.setInputStr(commaSeparatedInputs);
        calculation.setResult(result);
        return calculation;
    }




}
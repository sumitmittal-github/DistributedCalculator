package com.sumit.api.service;

import com.sumit.api.entity.OperationType;
import com.sumit.api.entity.SQSMessage;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public void processMessage(SQSMessage sqsMessage){

        //STEP-1 : calculate the result
        Long result = 0L;
        if(OperationType.ADD == sqsMessage.getOperationType()){
            result = sqsMessage.getInputs().stream().reduce(0L, Long::sum);
            System.out.println("SUM = " + result);
        }

        //STEP-2 : Save result in DB


    }


}
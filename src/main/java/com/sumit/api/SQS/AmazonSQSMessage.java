package com.sumit.api.SQS;

import com.sumit.api.entity.OperationType;

import java.util.List;

public class AmazonSQSMessage {

    private OperationType operationType;
    private List<Double> inputs;

    public AmazonSQSMessage() {
    }

    public AmazonSQSMessage(OperationType operationType, List<Double> inputs) {
        this.operationType = operationType;
        this.inputs = inputs;
    }

    @Override
    public String toString() {
        return "AmazonSQSMessage { operationType=" + operationType + ", inputs=" + inputs + '}';
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public List<Double> getInputs() {
        return inputs;
    }

    public void setInputs(List<Double> inputs) {
        this.inputs = inputs;
    }

}
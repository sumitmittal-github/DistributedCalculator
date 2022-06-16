package com.sumit.api.entity;

import java.util.List;

public class SQSMessage {

    private List<Long> inputs;
    private OperationType operationType;


    public SQSMessage() {
    }

    public SQSMessage(List<Long> inputs, OperationType operationType) {
        this.inputs = inputs;
        this.operationType = operationType;
    }

    @Override
    public String toString() {
        return "SQSMessage{" +
                "inputs=" + inputs +
                ", operationType=" + operationType +
                '}';
    }

    public List<Long> getInputs() {
        return inputs;
    }

    public void setInputs(List<Long> inputs) {
        this.inputs = inputs;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}

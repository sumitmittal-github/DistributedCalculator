package com.sumit.api.entity;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Calculation {

    private List<Long> inputs;
    private OperationType operationType;
    private Long result;

    public Calculation() {
    }

    public Calculation(List<Long> inputs, OperationType operationType, Long result) {
        this.inputs = inputs;
        this.operationType = operationType;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Calculation{" +
                "inputs=" + inputs +
                ", operationType=" + operationType +
                ", result=" + result +
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

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}

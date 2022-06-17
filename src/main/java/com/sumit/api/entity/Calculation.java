package com.sumit.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String operationType;
    private String inputStr;
    private Double result;

    public Calculation() {
    }

    public Calculation(Long id, String operationType, String inputStr, Double result) {
        this.id=id;
        this.operationType = operationType;
        this.inputStr = inputStr;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Calculation{ id=" + id + ", operationType=" + operationType + ", inputStr=" + inputStr + ", result=" + result + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getInputStr() {
        return inputStr;
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}

package ru.netology.netologySpringBootCourseProject.model;

import ru.netology.netologySpringBootCourseProject.log.LogBuilder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class Operation {
    private String operationId;
    private String cardFromNumber;
    private String cardToNumber;
    private Amount amount;
    private Amount commission;
    //Сделать генерацию кода, когда будет возможность отправить код и принять его через форму в front
    private final String secretCode;
    private Amount sum;
    private BigDecimal balance;


    public Operation(LogBuilder logBuilder) {
        this.operationId = logBuilder.getOperationId();
        this.cardFromNumber = logBuilder.getCardNumberFrom();
        this.cardToNumber = logBuilder.getCardNumberTo();
        this.amount = logBuilder.getAmount();
        this.commission = logBuilder.getCommission();
        this.sum = logBuilder.getSum();
        this.balance = logBuilder.getBalance();
        //todo сейчас front создает только 0000, потом сделать генерацию
        this.secretCode = "0000";
    }

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public void setCardFromNumber(String cardFromNumber) {
        this.cardFromNumber = cardFromNumber;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public void setCardToNumber(String cardToNumber) {
        this.cardToNumber = cardToNumber;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Amount getCommission() {
        return commission;
    }

    public void setCommission(Amount commission) {
        this.commission = commission;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public Amount getSum() {
        return sum;
    }

    public void setSum(Amount sum) {
        this.sum = sum;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    @Override
    public String toString() {
        return "cardFromNumber='" + cardFromNumber + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                ", amount=" + amount +
                ", commission=" + commission +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(cardFromNumber, operation.cardFromNumber) && Objects.equals(cardToNumber, operation.cardToNumber) && Objects.equals(amount, operation.amount) && Objects.equals(commission, operation.commission) && Objects.equals(secretCode, operation.secretCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardFromNumber, cardToNumber, amount, commission, secretCode);
    }
}

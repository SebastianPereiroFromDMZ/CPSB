package ru.netology.netologySpringBootCourseProject.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Card {

    private String cardNumber;

    private String cardValidTill;

    private String cardCVV;

    private BigDecimal balance;


    public Card(String cardNumber, String cardValidTill, String cardCVV) {
        this.cardNumber = cardNumber;
        this.cardValidTill = cardValidTill;
        this.cardCVV = cardCVV;
        balance = new BigDecimal("100000000");
    }

    public Card() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardValidTill() {
        return cardValidTill;
    }

    public void setCardValidTill(String cardValidTill) {
        this.cardValidTill = cardValidTill;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }



    @Override
    public String toString() {
        return String.format("Карта:\nНомер карты:%s\nМЕС/ГОД:%s\nCVV:%s\nБаланс:%s\n",
                cardNumber,
                cardValidTill,
                cardCVV,
                balance);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(cardNumber, card.cardNumber) && Objects.equals(cardValidTill, card.cardValidTill) && Objects.equals(cardCVV, card.cardCVV);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardValidTill, cardCVV);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal randomBalance) {
        this.balance = randomBalance;
    }
}

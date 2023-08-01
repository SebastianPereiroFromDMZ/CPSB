package ru.netology.netologySpringBootCourseProject.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
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
        return Objects.equals(cardNumber, card.cardNumber) &&
                Objects.equals(cardValidTill, card.cardValidTill) &&
                Objects.equals(cardCVV, card.cardCVV);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardValidTill, cardCVV);
    }
}

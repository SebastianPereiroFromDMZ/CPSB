package ru.netology.netologySpringBootCourseProject.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class CardTransfer {
    @NotNull

    private String cardFromNumber;
    @NotNull
    private String cardFromValidTill;
    @NotNull
    private String cardFromCVV;
    @NotNull
    private String cardToNumber;
    private Amount amount;


    public CardTransfer(String cardFromNumber, String cardFromValidTill, String cardFromCVV, String cardToNumber, Amount amount) {
        this.cardFromNumber = cardFromNumber;
        this.cardFromValidTill = cardFromValidTill;
        this.cardFromCVV = cardFromCVV;
        this.cardToNumber = cardToNumber;
        this.amount = amount;
    }

    public CardTransfer() {
    }
}

package ru.netology.netologySpringBootCourseProject.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.netologySpringBootCourseProject.exceptions.InvalidTransactionExceptions;
import ru.netology.netologySpringBootCourseProject.model.CardTransfer;
import ru.netology.netologySpringBootCourseProject.model.Verification;
import ru.netology.netologySpringBootCourseProject.service.TransferService;

@RestController
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public String transferMoneyCardToCard(@RequestBody @Validated CardTransfer cardTransfer) throws InvalidTransactionExceptions {
        return transferService.transferMoneyCardToCard(cardTransfer);
    }

    @PostMapping("/confirmOperation")
    public String confirmOperation(@RequestBody @Validated Verification verification) throws InvalidTransactionExceptions {
        //return transferService.confirmOperation(verification);
        return null;
    }
}

package ru.netology.netologySpringBootCourseProject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.netology.netologySpringBootCourseProject.exceptions.BadRequestExceptions;
import ru.netology.netologySpringBootCourseProject.model.CardTransfer;
import ru.netology.netologySpringBootCourseProject.model.Verification;

public interface TransferController {


    public ResponseEntity<String> transferMoneyCardToCard(@RequestBody @Validated CardTransfer cardTransfer) throws BadRequestExceptions;

    public ResponseEntity<String> confirmOperation(@RequestBody @Validated Verification verification) throws BadRequestExceptions;
}

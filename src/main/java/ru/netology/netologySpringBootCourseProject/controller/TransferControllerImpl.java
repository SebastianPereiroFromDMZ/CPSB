package ru.netology.netologySpringBootCourseProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.netologySpringBootCourseProject.exceptions.BadRequestExceptions;
import ru.netology.netologySpringBootCourseProject.model.CardTransfer;
import ru.netology.netologySpringBootCourseProject.model.Verification;
import ru.netology.netologySpringBootCourseProject.service.TransferService;

@RestController
@RequiredArgsConstructor//добавляет конструктор для final полей
public class TransferControllerImpl implements TransferController {
    private final TransferService transferService;

    @Override
    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoneyCardToCard(@RequestBody @Validated CardTransfer cardTransfer) throws BadRequestExceptions {
        return new ResponseEntity<>(transferService.transferMoneyCardToCard(cardTransfer), HttpStatus.OK);
    }

    @Override
    @PostMapping("/confirmOperation")
    public ResponseEntity<String> confirmOperation(@RequestBody @Validated Verification verification) throws BadRequestExceptions {
        return new ResponseEntity<>(transferService.confirmOperation(verification), HttpStatus.OK);
        //return null;
    }
}

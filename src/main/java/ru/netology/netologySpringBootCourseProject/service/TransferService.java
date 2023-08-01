package ru.netology.netologySpringBootCourseProject.service;

import ru.netology.netologySpringBootCourseProject.exceptions.BadRequestExceptions;
import ru.netology.netologySpringBootCourseProject.model.CardTransfer;
import ru.netology.netologySpringBootCourseProject.model.Verification;

public interface TransferService {

    public String transferMoneyCardToCard(CardTransfer cardTransfer) throws BadRequestExceptions;

    public String confirmOperation(Verification verification) throws BadRequestExceptions;
}

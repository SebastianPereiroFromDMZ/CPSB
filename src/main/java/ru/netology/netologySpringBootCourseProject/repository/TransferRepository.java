package ru.netology.netologySpringBootCourseProject.repository;

import ru.netology.netologySpringBootCourseProject.model.Card;
import ru.netology.netologySpringBootCourseProject.model.Operation;

import java.util.Map;

public interface TransferRepository {

    public Map<String, Card> getMapStorage();

    public Card getCard(String key);

    public void putCard(String cardNumber, Card card);

    public Operation getOperationFromId(String id);

    public void putOperationFromId(String id, Operation operation);

    public Map<String, Operation> getHistoryOfOperations();

    public void deleteOperationFromId(String id);

}

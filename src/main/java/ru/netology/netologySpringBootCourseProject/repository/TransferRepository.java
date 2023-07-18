package ru.netology.netologySpringBootCourseProject.repository;

import org.springframework.stereotype.Repository;
import ru.netology.netologySpringBootCourseProject.model.Card;
import ru.netology.netologySpringBootCourseProject.model.CardTransfer;
import ru.netology.netologySpringBootCourseProject.model.Operation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TransferRepository {
    private final Map<String, Card> mapStorage;
    private final Map<String, Operation> historyOfOperations;

    public TransferRepository() {
        mapStorage = new HashMap<>();
        historyOfOperations = new HashMap<>();
    }

    public Map<String, Card> getMapStorage() {
        return mapStorage;
    }

    public Card getCard(String key){
        return mapStorage.get(key);
    }

    public void putCard(String cardNumber, Card card) {
        mapStorage.put(cardNumber, card);
    }

    public Operation getOperationFromId(String id) {
        return historyOfOperations.get(id);
    }

    public void putOperationFromId(String id, Operation operation) {
        historyOfOperations.put(id, operation);
    }

    public Map<String, Operation> getHistoryOfOperations(){
        return historyOfOperations;
    }
    public void deleteOperationFromId(String id) {
        historyOfOperations.remove(id);
    }
}

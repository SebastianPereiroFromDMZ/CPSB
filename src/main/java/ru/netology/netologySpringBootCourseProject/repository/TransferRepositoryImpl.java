package ru.netology.netologySpringBootCourseProject.repository;

import org.springframework.stereotype.Repository;
import ru.netology.netologySpringBootCourseProject.model.Card;
import ru.netology.netologySpringBootCourseProject.model.Operation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransferRepositoryImpl implements TransferRepository {
    private final Map<String, Card> mapStorage;
    private final Map<String, Operation> historyOfOperations;

    public TransferRepositoryImpl() {
        mapStorage = new ConcurrentHashMap<>();
        historyOfOperations = new ConcurrentHashMap<>();
    }

    @Override
    public Map<String, Card> getMapStorage() {
        return mapStorage;
    }
    @Override
    public Card getCard(String key) {
        return mapStorage.get(key);
    }
    @Override
    public void putCard(String cardNumber, Card card) {
        mapStorage.put(cardNumber, card);
    }
    @Override
    public Operation getOperationFromId(String id) {
        return historyOfOperations.get(id);
    }
    @Override
    public void putOperationFromId(String id, Operation operation) {
        historyOfOperations.put(id, operation);
    }
    @Override
    public Map<String, Operation> getHistoryOfOperations() {
        return historyOfOperations;
    }
    @Override
    public void deleteOperationFromId(String id) {
        historyOfOperations.remove(id);
    }
}

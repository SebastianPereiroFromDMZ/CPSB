package ru.netology.netologySpringBootCourseProject.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.netologySpringBootCourseProject.model.Card;
import ru.netology.netologySpringBootCourseProject.model.Operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class TransferRepositoryImplTest {

    private TransferRepository repository;
    private final String ID = "45584885558767";

    @BeforeEach
    void setUp() {
        repository = new TransferRepositoryImpl();
    }


    @Test
    void getMapStorage_isEmpty() {
        boolean expected = true;

        boolean actual = repository.getMapStorage().isEmpty();

        assertEquals(expected, actual);
    }

    @Test
    void getMapStorage_isNotEmpty() {
        final Card card = mock(Card.class);
        repository.getMapStorage().put(ID, card);
        boolean expected = false;

        boolean actual = repository.getMapStorage().isEmpty();

        assertEquals(expected, actual);
    }

    @Test
    void getCard_shouldFindCard_whenExists() { //(название метода) 1: название тестируемого метода. 2: что он должен делать(искать карту). 3: когда (она есть)
        final Card expected = mock(Card.class);
        repository.getMapStorage().put(ID, expected);

        Card actual = repository.getCard(ID);

        assertEquals(expected, actual);
    }

    @Test
    void putCard_intoRepository() {
        final Card expected = mock(Card.class);
        repository.putCard(ID, expected);

        Card actual = repository.getMapStorage().get(ID);

        assertEquals(expected, actual);
    }

    @Test
    void getOperationFromId_shouldFindOperation_whenExists() {
        final Operation expected = mock(Operation.class);
        repository.putOperationFromId(ID, expected);

        Operation actual = repository.getOperationFromId(ID);

        assertEquals(expected, actual);
    }

    @Test
    void putOperationFromId_putToRepository() {
        final Operation expected = mock(Operation.class);
        repository.putOperationFromId(ID, expected);

        Operation actual = repository.getHistoryOfOperations().get(ID);

        assertEquals(expected, actual);
    }

    @Test
    void getHistoryOfOperations_isEmpty() {
        boolean expected = true;

        boolean actual = repository.getHistoryOfOperations().isEmpty();

        assertEquals(expected, actual);
    }

    @Test
    void getHistoryOfOperations_isNotEmpty() {
        final Operation operation = mock(Operation.class);
        repository.putOperationFromId(ID, operation);
        boolean expected = false;

        boolean actual = repository.getHistoryOfOperations().isEmpty();

        assertEquals(expected, actual);
    }


    @Test
    void deleteOperationFromId_deleteOperation() {
        final Operation operation = mock(Operation.class);
        repository.putOperationFromId(ID, operation);
        boolean expected = true;

        repository.deleteOperationFromId(ID);
        boolean actual = repository.getHistoryOfOperations().isEmpty();

        assertEquals(expected, actual);
    }
}
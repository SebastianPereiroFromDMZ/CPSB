package ru.netology.netologySpringBootCourseProject.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ru.netology.netologySpringBootCourseProject.exceptions.BadRequestExceptions;
import ru.netology.netologySpringBootCourseProject.model.CardTransfer;
import ru.netology.netologySpringBootCourseProject.model.Verification;
import ru.netology.netologySpringBootCourseProject.service.TransferService;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;

//@WebMvcTest(TransferControllerImpl.class)из JUnit 5
//@RunWith(SpringRunner.class) из JUnit 4
@ExtendWith(MockitoExtension.class)// позволяет создавать фейковые объекты
class TransferControllerImplTest {

    @Mock
    private TransferService service;

    @InjectMocks
    TransferControllerImpl controller;


    @Test
    void transfer_testInvalidTransactionExceptions() throws Exception {
        final CardTransfer cardTransfer = mock(CardTransfer.class);
        Mockito.when(service.transferMoneyCardToCard(cardTransfer)).thenThrow(new BadRequestExceptions("Error"));

        assertThrows(BadRequestExceptions.class, () -> {
            controller.transferMoneyCardToCard(cardTransfer);
        });
    }

    @Test
    void transfer_successfulOperation() throws Exception {
        final CardTransfer cardTransfer = mock(CardTransfer.class);
        Mockito.when(service.transferMoneyCardToCard(cardTransfer)).thenReturn("Success");
        ResponseEntity<String> expected = new ResponseEntity<>("Success" ,HttpStatus.OK);

        ResponseEntity<String> actual = controller.transferMoneyCardToCard(cardTransfer);

        assertEquals(expected, actual);
    }


    @Test
    void confirmOperation_testInvalidTransactionExceptions() throws BadRequestExceptions {
        final Verification verification = mock(Verification.class);
        Mockito.when(service.confirmOperation(verification)).thenThrow(new BadRequestExceptions("Error"));

        assertThrows(BadRequestExceptions.class, () -> {
            controller.confirmOperation(verification);
        });
    }

    @Test
    void confirmOperation_successfulOperation() throws BadRequestExceptions {
        final Verification verification = mock(Verification.class);
        Mockito.when(service.confirmOperation(verification)).thenReturn("Success");
        ResponseEntity<String> expected = new ResponseEntity<>("Success" ,HttpStatus.OK);

        ResponseEntity<String> actual = controller.confirmOperation(verification);

        assertEquals(expected, actual);
    }
}
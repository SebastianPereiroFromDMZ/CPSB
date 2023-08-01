package ru.netology.netologySpringBootCourseProject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.netologySpringBootCourseProject.exceptions.BadRequestExceptions;
import ru.netology.netologySpringBootCourseProject.model.Amount;
import ru.netology.netologySpringBootCourseProject.model.Card;
import ru.netology.netologySpringBootCourseProject.model.CardTransfer;
import ru.netology.netologySpringBootCourseProject.model.Verification;
import ru.netology.netologySpringBootCourseProject.repository.TransferRepository;
import ru.netology.netologySpringBootCourseProject.repository.TransferRepositoryImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
class TransferServiceImplTest {
    private final String ID = "455844588558478";

    //@Mock
    private TransferRepository repository;

    // @InjectMocks
    private TransferServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = new TransferRepositoryImpl();
        service = new TransferServiceImpl(repository);
    }


    @Test
    void transferMoneyCardToCard_throwAnException_whenTheSameCard() {
        CardTransfer cardTransfer = createBrokenCardTransferObject();
        assertThrows(BadRequestExceptions.class, () -> {
            service.transferMoneyCardToCard(cardTransfer);
        });
    }

    @Test
    void transferMoneyCardToCard_successfulOperation() throws BadRequestExceptions {
        CardTransfer cardTransfer = createCardTransferObject();

        String actual = service.transferMoneyCardToCard(cardTransfer);

        String expected = "The transfer of funds has been made. \n" +
                "Transfer amount: " + cardTransfer.getAmount().getValue() + "\n" +
                "Commission: " + cardTransfer.getAmount().getValue().divide(new BigDecimal(100)) + "\n" +
                "Card balance: " + repository.getCard(ID).getBalance();

        assertEquals(expected, actual);
    }

    @Test
    void transferMoneyCardToCard_brokenOperation_lowBalance() {
        CardTransfer cardTransfer = createCardTransferObjectForLowBalanceTest();

        assertThrows(BadRequestExceptions.class, () -> {
            service.transferMoneyCardToCard(cardTransfer);
        });
    }

    @Test
    void confirmOperation_emptyHistoryOperation() {
        Verification verification = createVerification();

        assertThrows(BadRequestExceptions.class, () -> {
            service.confirmOperation(verification);
        });
    }

    @Test
    void confirmOperation_wrongId() throws BadRequestExceptions {
        CardTransfer cardTransfer = createCardTransferObject();
        service.transferMoneyCardToCard(cardTransfer);
        Verification verification = createVerificationWithWrongId();

        assertThrows(BadRequestExceptions.class, () -> {
            service.confirmOperation(verification);
        });
    }

    @Test
    void confirmOperation_wrongSecretCode() throws BadRequestExceptions {
        CardTransfer cardTransfer = createCardTransferObject();
        service.transferMoneyCardToCard(cardTransfer);
        Verification verification = createVerificationWithWrongSecretCode();

        assertThrows(BadRequestExceptions.class, () -> {
            service.confirmOperation(verification);
        });
    }

    @Test
    void confirmOperation_successfulConfirmOperation() throws BadRequestExceptions {
        CardTransfer cardTransfer = createCardTransferObject();
        service.transferMoneyCardToCard(cardTransfer);
        Verification verification = createVerification();

        String actual = service.confirmOperation(verification);

        String expected = "Repeat operation №" + verification.getOperationId() + " successful\n" +
                "Transfer amount: " + repository.getOperationFromId(verification.getOperationId()).getAmount().getValue() + "\n" +
                "Commission: " + repository.getOperationFromId(verification.getOperationId()).getCommission().getValue() + "\n" +
                "Card balance: " + repository.getOperationFromId(verification.getOperationId()).getBalance();

        assertEquals(expected, actual);
    }

    @Test
    void confirmOperation_brokenConfirmOperation_lowBalance() throws BadRequestExceptions {
        CardTransfer cardTransfer = createCardTransferObject();
        service.transferMoneyCardToCard(cardTransfer);
        Verification verification = createVerification();

        assertThrows(BadRequestExceptions.class, () -> {
            repository.getCard(cardTransfer.getCardFromNumber()).setBalance(new BigDecimal(1));
            service.confirmOperation(verification);
        });
    }

    private CardTransfer createBrokenCardTransferObject() {
        return new CardTransfer("455844588558478",
                "08/23",
                "351",
                "455844588558478",
                new Amount(new BigDecimal("5000000"), "RUR"));
    }

    private CardTransfer createCardTransferObject() {
        return new CardTransfer("455844588558478",
                "08/23",
                "351",
                "455844588558479",
                new Amount(new BigDecimal("50000"), "RUR"));
    }

    private CardTransfer createCardTransferObjectForLowBalanceTest() {
        return new CardTransfer("455844588558478",
                "08/23",
                "351",
                "455844588558479",
                new Amount(new BigDecimal("500000000000000"), "RUR"));
    }

    private Verification createVerification() {
        return new Verification("0000", "1");
    }

    private Verification createVerificationWithWrongId() {
        return new Verification("0000", "45");
    }

    private Verification createVerificationWithWrongSecretCode() {
        return new Verification("1234", "1");
    }

}
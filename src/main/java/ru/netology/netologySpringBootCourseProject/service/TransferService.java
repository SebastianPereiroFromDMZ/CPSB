package ru.netology.netologySpringBootCourseProject.service;

import org.springframework.stereotype.Service;
import ru.netology.netologySpringBootCourseProject.exceptions.InvalidTransactionExceptions;
import ru.netology.netologySpringBootCourseProject.log.LogBuilder;
import ru.netology.netologySpringBootCourseProject.log.TransferLog;
import ru.netology.netologySpringBootCourseProject.model.*;
import ru.netology.netologySpringBootCourseProject.repository.TransferRepository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    static final int COMMISSION = 100;
    private final TransferLog transferLog;
    private final AtomicInteger num = new AtomicInteger(0);

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
        this.transferLog = TransferLog.getInstance();
    }

    public String transferMoneyCardToCard(CardTransfer cardTransfer) throws InvalidTransactionExceptions {
        //Проверки на корректность передачи данных карт:

        //1. Проверка на разные номера карт:
        if (cardTransfer.getCardFromNumber().equals(cardTransfer.getCardToNumber())) {
            throw new InvalidTransactionExceptions("The card for transfer and receipt is the same!\n" +
                    "Check the input data again");
        }
        //2. Проверка на правильный номер карты, откуда происходит перевод:

        //3. Проверка на правильный номер карты, куда происходит перевод:

        //Если карты нет в репозитории, ложим ее туда:
        if (!transferRepository.getMapStorage().containsKey(cardTransfer.getCardFromNumber())) {
            transferRepository.putCard(cardTransfer.getCardFromNumber(),
                    new Card(cardTransfer.getCardFromNumber(),
                            cardTransfer.getCardFromValidTill(),
                            cardTransfer.getCardFromCVV()
                    ));
        }

        //Достаем карту из репозитория для удобства:
        Card cardFrom = transferRepository.getCard(cardTransfer.getCardFromNumber());

        //Номер карты куда происходит перевод:
        String cardToNumber = cardTransfer.getCardToNumber();

        //Дынные перевода (сумма, валюта)
        Amount amount = new Amount(cardTransfer.getAmount().getValue(),
                cardTransfer.getAmount().getCurrency());

        //Получаем баланс, откуда происходит перевод:
        BigDecimal balanceFrom = cardFrom.getBalance();

        //Получаем коммисию за перевод:
        Amount commission = new Amount(amount.getValue().divide(BigDecimal.valueOf(COMMISSION)), amount.getCurrency());

        //Получаем итоговую сумму перевод + коммисия
        BigDecimal sumResult = amount.getValue().add(commission.getValue());

        Amount sum = new Amount(sumResult, amount.getCurrency());

        //Создаем фабрику лога
        LogBuilder logBuilder = new LogBuilder()
                .setCardNumberFrom(cardFrom.getCardNumber())
                .setCardNumberTo(cardToNumber)
                .setAmount(amount)
                .setCommission(commission);


        //Проверяем бланс карты:
        //1. Если баланс карты больше суммы перевода:
        if (balanceFrom.compareTo(sumResult) >= 0) {
            BigDecimal balanceAfterOperation = new BigDecimal(String.valueOf(cardFrom.getBalance().subtract(sumResult)));
            logBuilder.setBalance(balanceAfterOperation);
            logBuilder.setSum(sum);
            transferRepository.getCard(cardTransfer.getCardFromNumber()).setBalance(balanceAfterOperation);
            logBuilder.setResult("The operation was successful.");

            //логируем операцию (выводим в консоль + записываем в файл)
            transferLog.log(logBuilder);

            //записыем в репозиторий новую операцию в отдельную мапу для возмлжности ее повторения
            String operationId = String.valueOf(num.incrementAndGet());
            transferRepository.putOperationFromId(operationId, new Operation(logBuilder));
        } else {
            //2. Если средств недостаточно:
            logBuilder.setBalance(cardFrom.getBalance());
            logBuilder.setSum(sum);
            logBuilder.setResult("The operation is impossible. Low balance.\n" +
                    "Amount requested for transfer: " + sum.getValue() + " " +
                    sum.getCurrency() + ".\n" +
                    "Funds available: " + cardFrom.getBalance() + " " + sum.getCurrency());
            transferLog.log(logBuilder);
            throw new InvalidTransactionExceptions(logBuilder.getResult());
        }
        return "The transfer of funds has been made. \n" +
                "Transfer amount: " + amount.getValue() + "\n" +
                "Commission: " + commission.getValue() + "\n" +
                "Card balance: " + balanceFrom;
    }


    public String confirmOperation(Verification verification) throws InvalidTransactionExceptions {

        //достаем мапу из репозитория для удобства
        Map<String, Operation> historyOfOperations = transferRepository.getHistoryOfOperations();

        //проверка на наличие операций в истрии операций
        if (historyOfOperations.isEmpty()) {
            throw new InvalidTransactionExceptions("The history of operations is empty");
        }

        //проверка на содержание операции с запрошенным id
        if (!historyOfOperations.containsKey(verification.getOperationId())) {
            throw new InvalidTransactionExceptions("There is no operation with such a number");
        }
        //Если операция по id имеется, достаем ее
        Operation operation = transferRepository.getOperationFromId(verification.getOperationId());

        //достаем карта над которой проводилась эта операция
        Card cardFrom = transferRepository.getCard(operation.getCardFromNumber());

        //проверяем корректность секретного кода
        if (verification.getCode().equals(operation.getSecretCode())) {

            //достаем баланс карты
            BigDecimal balanceFrom = cardFrom.getBalance();

            //достаем сумму операции выполненной по предоставленному id
            BigDecimal sumResult = operation.getSum().getValue();

            //передаем часть данных в фабрику лога
            LogBuilder logBuilder = new LogBuilder()
                    .setOperationId(verification.getOperationId())
                    .setCardNumberFrom(operation.getCardFromNumber())
                    .setCardNumberTo(operation.getCardToNumber())
                    .setAmount(operation.getAmount())
                    .setCommission(operation.getCommission());

            //если баланс на карте превышает сумму повторяющейся операции на более чем 0
            if (balanceFrom.compareTo(sumResult) >= 0) {

                //получаем баланс после операции
                BigDecimal balanceAfterOperation = new BigDecimal(String.valueOf(cardFrom.getBalance().subtract(sumResult)));
                //передаем в фабрику баланс после
                logBuilder.setBalance(balanceAfterOperation);
                //передаем в фабрику сумму операции (сумму перевода + коммисия)
                logBuilder.setSum(operation.getSum());
                //обновляем баланс на карте
                transferRepository.getCard(operation.getCardFromNumber()).setBalance(balanceAfterOperation);
                //в фабрику результат операции
                logBuilder.setResult("The operation was successful.");
                //логируем операцию (выводим в консоль + записываем в файл)
                transferLog.log(logBuilder);
                //возвращаем пользователю
                return "Repeat operation №" + verification.getOperationId() + " successful\n" +
                        "Transfer amount: " + operation.getAmount().getValue() + "\n" +
                        "Commission: " + operation.getCommission().getValue() + "\n" +
                        "Card balance: " + balanceFrom;

                //если баланса на карте не достаточно
            } else {
                logBuilder.setBalance(cardFrom.getBalance());
                logBuilder.setSum(operation.getSum());
                logBuilder.setResult("The operation is impossible. Low balance.\n" +
                        "Amount requested for transfer: " + operation.getSum().getValue() + " " +
                        operation.getSum().getCurrency() + ".\n" +
                        "Funds available: " + cardFrom.getBalance() + " " + operation.getSum().getCurrency());
                transferLog.log(logBuilder);
                throw new InvalidTransactionExceptions(logBuilder.getResult());
            }
            //если секретный код не совпадает
        } else {
            throw new InvalidTransactionExceptions("No such operation");
        }

    }
}

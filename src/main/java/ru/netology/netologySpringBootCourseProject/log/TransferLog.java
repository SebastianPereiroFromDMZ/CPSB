package ru.netology.netologySpringBootCourseProject.log;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransferLog {
    protected AtomicInteger num = new AtomicInteger(0);
    private final ConcurrentHashMap<String, Integer> cardTransactions = new ConcurrentHashMap<>();
    private static volatile TransferLog INSTANCE = null;
    private final Logger logger = Logger.getLogger(TransferLog.class.getName());


    private TransferLog() {
    }

    public static TransferLog getInstance() {
        if (INSTANCE == null) {
            synchronized (TransferLog.class) {
                if (INSTANCE == null)
                    INSTANCE = new TransferLog();
            }
        }
        return INSTANCE;
    }

    public void log(LogBuilder logBuilder) {

        String operationId = String.valueOf(num.incrementAndGet());

        cardTransactions.put(logBuilder.getCardNumberFrom(), cardTransactions.getOrDefault(logBuilder.getCardNumberFrom(), 0) + 1);

        logger.log(Level.INFO, "---------------------");
        logger.log(Level.INFO, "Операция в системе: " + operationId);
        logger.log(Level.INFO, "Операция по карте: " + cardTransactions.get(logBuilder.getCardNumberFrom()));
        logger.log(Level.INFO, "Номер карты списания: " + logBuilder.getCardNumberFrom());
        logger.log(Level.INFO, "Номер карты зачисления: " + logBuilder.getCardNumberTo());
        logger.log(Level.INFO, "Сумма списания: " + logBuilder.getAmount().getValue() + logBuilder.getAmount().getCurrency());
        logger.log(Level.INFO, "Комиссия за перевод: " + logBuilder.getCommission().getValue() + logBuilder.getCommission().getCurrency());
        logger.log(Level.INFO, "Общая сумма списания: " + logBuilder.getSum().getValue() + logBuilder.getSum().getCurrency());
        logger.log(Level.INFO, "Остаток по карте: " + logBuilder.getBalance());
        logger.log(Level.INFO, "Результат операции: " + logBuilder.getResult());
    }
}

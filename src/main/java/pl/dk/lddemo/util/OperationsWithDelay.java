package pl.dk.lddemo.util;

import java.util.concurrent.Callable;

public class OperationsWithDelay {

    private static final long SHORT_DELAY_MILLIS = 5;

    private static final long LONG_DELAY_MILLIS = 10000;

    public static <T> T quickOperation(Callable<T> callable) {
        return operationWithDelay(callable, SHORT_DELAY_MILLIS);
    }

    public static <T> T longOperation(Callable<T> callable) {
        return operationWithDelay(callable, LONG_DELAY_MILLIS);
    }

    private static <T> T operationWithDelay(Callable<T> callable, long delay) {
        try {
            Thread.sleep(delay);
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package pl.dk.lddemo;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static pl.dk.lddemo.util.OperationsWithDelay.longOperation;
import static pl.dk.lddemo.util.OperationsWithDelay.quickOperation;

@Component
class VisitsCollector {

    private AtomicBoolean reportInCreation = new AtomicBoolean(false);

    private AtomicInteger visits = new AtomicInteger(0);

    Integer addVisit() {
        waitIfReportInCreation();
        return quickOperation(() -> visits.getAndIncrement());
    }

    private void waitIfReportInCreation() {
        try {
            while (reportInCreation.get()) {
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    Integer createReport() {
        reportInCreation.getAndSet(true);
        Integer nrOfVisits = longOperation(() -> visits.getAndSet(0));
        reportInCreation.getAndSet(false);
        return nrOfVisits;
    }
}

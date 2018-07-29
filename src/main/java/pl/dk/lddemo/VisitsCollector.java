package pl.dk.lddemo;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import static pl.dk.lddemo.util.OperationsWithDelay.longOperation;
import static pl.dk.lddemo.util.OperationsWithDelay.quickOperation;

@Component
class VisitsCollector {

    private boolean reportInCreation = false;

    private AtomicInteger visits = new AtomicInteger(0);

    Integer addVisit() {
        waitIfReportInCreation();
        return quickOperation(() -> visits.getAndIncrement());
    }

    private void waitIfReportInCreation() {
        try {
            while (reportInCreation) {
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    Integer createReport() {
        reportInCreation = true;
        Integer nrOfVisits = longOperation(() -> visits.getAndSet(0));
        reportInCreation = false;
        return nrOfVisits;
    }
}

package pl.dk.lddemo;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import static pl.dk.lddemo.util.OperationsWithDelay.longOperation;
import static pl.dk.lddemo.util.OperationsWithDelay.quickOperation;

@Component
class VisitsCollector {

    private ReentrantLock reportInCreationLock = new ReentrantLock();

    private AtomicInteger visits = new AtomicInteger(0);

    Integer addVisit() {
        waitIfReportInCreation();
        return quickOperation(() -> visits.getAndIncrement());
    }

    private void waitIfReportInCreation() {
        try {
            while (reportInCreationLock.isLocked()) {
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    Integer createReport() {
        reportInCreationLock.lock();
        Integer nrOfVisits = longOperation(() -> visits.getAndSet(0));
        reportInCreationLock.unlock();
        return nrOfVisits;
    }
}

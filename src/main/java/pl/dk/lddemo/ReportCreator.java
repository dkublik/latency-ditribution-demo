package pl.dk.lddemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class ReportCreator {

    private Logger logger = LoggerFactory.getLogger(ReportCreator.class);

    private final VisitsCollector visitsCollector;

    ReportCreator(VisitsCollector visitsCollector) {
        this.visitsCollector = visitsCollector;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void report() {
        Integer numberOfVisits = visitsCollector.createReport();
        logger.info("visits report -> number of visits: {}", numberOfVisits);
    }
}

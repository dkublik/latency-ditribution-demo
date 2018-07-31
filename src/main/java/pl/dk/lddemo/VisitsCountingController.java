package pl.dk.lddemo;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/count-visit")
class VisitsCountingController {

    private final VisitsCollector visitsCollector;
    private final Timer visitsTimer;

    VisitsCountingController(VisitsCollector visitsCollector) {
        this.visitsCollector = visitsCollector;
        visitsTimer = Metrics.timer("pl.dk.visits");
    }

    @GetMapping
    void countVisit() {
        visitsTimer.record((Runnable) () -> visitsCollector.addVisit());
    }
}

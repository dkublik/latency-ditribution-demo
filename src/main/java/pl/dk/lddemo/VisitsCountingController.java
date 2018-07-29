package pl.dk.lddemo;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/count-visit")
class VisitsCountingController {

    private final VisitsCollector visitsCollector;

    VisitsCountingController(VisitsCollector visitsCollector) {
        this.visitsCollector = visitsCollector;
    }

    @GetMapping
    @Timed(value = "pl.dk.visit")
    void countVisit() {
        visitsCollector.addVisit();
    }
}

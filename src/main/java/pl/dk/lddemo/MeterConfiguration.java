package pl.dk.lddemo;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MeterConfiguration {

    @Bean
    MeterFilter onlyMyCustomMetrics() {
        return MeterFilter.deny(id -> id.getType() != Meter.Type.TIMER || !id.getName().startsWith("pl.dk"));
    }
}

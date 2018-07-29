package pl.dk.lddemo

import org.HdrHistogram.Histogram
import spock.lang.Specification

class DistributionToStatisticsSimulationSpec extends Specification {

    def "should result in mean higher than p99"() {
        given:
            Histogram histogram = new Histogram(2)

        when:
            for (int i = 0; i < 99; i++) {
                histogram.recordValue(1)
            }
            histogram.recordValue(100)

        then:
            long p75 = histogram.getValueAtPercentile(75)
            long p95 = histogram.getValueAtPercentile(95)
            long p99 = histogram.getValueAtPercentile(99)
            double mean = histogram.getMean()
            long max = histogram.getMaxValue()
            println("p75: $p75")
            println("p95: $p95")
            println("p99: $p99")
            println("mean: $mean")
            println("max: $max")
            mean > p99
    }
}

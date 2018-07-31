Optimistic Locking Tests
===============

## Requirements

* Java 8
* Graphite run on http://localhost:80 (configurable by properties)
* Graphana (optional to visualize metrics)

#### start Graphite
```
docker run -d --name graphite --restart=always -p 80:80 -p 2003-2004:2003-2004 -p 2023-2024:2023-2024 -p 8125:8125/udp -p 8126:8126 graphiteapp/graphite-statsd
```
#### start Grafana
```
docker run -d --name grafana -p 3000:3000 grafana/grafana
```

## To run
* run pl.dk.lddemo.LDDemoApp from ide or by
```
./gradlew bootRun
```

## To load test
```
wrk -t 100 -c 100 --latency -d 50m http://localhost:8080/count-visit
```

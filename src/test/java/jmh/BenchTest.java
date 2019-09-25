package jmh;

import org.openjdk.jmh.annotations.*;

public class BenchTest {
    @Benchmark
    @Warmup(iterations = 3)
    @Measurement(iterations = 5)
    @BenchmarkMode(Mode.Throughput)
    public void init() {
        // Do nothing
    }
}
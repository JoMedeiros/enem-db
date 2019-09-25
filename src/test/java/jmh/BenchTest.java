package jmh;

import org.openjdk.jmh.annotations.*;

public class BenchTest {
    @Benchmark
    @Warmup(iterations = 1)
    @Measurement(iterations = 3)
    @BenchmarkMode(Mode.Throughput)
    public void init() {
        // Do nothing
    }
}
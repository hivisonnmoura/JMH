package com.study.java.microbench;

import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@State(Scope.Benchmark)
public class BenchmarkTest {

    public List<ObjectTest> objectTestList;

    public void populateList() {
        objectTestList = Stream.generate(ObjectTest::new).limit(1000000).collect(Collectors.toList());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void objectsNonNull() {
        populateList();
        objectTestList.forEach(Objects::nonNull);
        objectTestList.clear();

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void objectsNonNull2() {
        populateList();
        objectTestList.forEach(objectTest -> {
            boolean a = Objects.nonNull(objectTest);
        });
        objectTestList.clear();

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void oldNotNull() {
        populateList();
        objectTestList.forEach(objectTest -> {
           boolean a = objectTest != null;
        });
        objectTestList.clear();

    }
}

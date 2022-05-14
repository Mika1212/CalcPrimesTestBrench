package org.example;

import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class Eratosthenes {

    //Сложность O(N*log(log(N)))
    //Память O(N)
    // N - общее количество чисел в заданном диапазоне,
    @Benchmark
    static public int calcPrimesNumberOfEratosthenes() {
        int limit = 100;
        int limit1 = limit + 1;
        int answers = 0;
        boolean[] massive = new boolean[limit1];
        Arrays.fill(massive, true);
        massive[0] = false;

        for (int i = 2; Math.pow(i, 2.0) < limit1; i++) {
            if (massive[i]) {
                for (int j = (int) Math.pow(i, 2.0); j < limit1; j += i) {
                    massive[j] = false;
                }
            }
        }

        for (boolean b : massive) {
            if (b) answers++;
        }

        return answers;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Eratosthenes.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}

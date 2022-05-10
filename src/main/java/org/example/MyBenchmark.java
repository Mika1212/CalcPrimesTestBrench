/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyBenchmark {

    //Сложность O(N)
    //Память O(N + M)
    // N - общее количество чисел в заданном диапазоне,
    // M - количество простых чисел в заданном диапазоне.
    static public int calcPrimesNumberOfEuler(int limit) {
        if (limit < 1) return 0;
        int limit1 = limit + 1;
        List<Integer> pr = new ArrayList<>();
        int[] lp = new int[limit1];

        for (int i = 2; i < limit1; i++) {
            if (lp[i] == 0) {
                lp[i] = i;
                pr.add(i);
            }
            for (Integer p : pr) {
                if (p <= lp[i] && p * i <= limit) {
                    lp[p * i] = p;
                } else {
                    break;
                }
            }
        }
        pr.add(1);
        return pr.size();
    }

    //Сложность O(N*log(log(N)))
    //Память O(N)
    static public int calcPrimesNumberOfEratosthenesMassive(int limit) {
        int limit1 = limit + 1;
        if (limit1 <= 1) return 0;
        int answers = 0;
        boolean[] massive = new boolean[limit1];
        Arrays.fill(massive, true);
        massive[0] = false;
        massive[1] = false;

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

    //Сложность O(N*log(log(N)))
    //Память O(N)
    static public int calcPrimesNumberOfEratosthenes(int limit) {
        int limit1 = limit + 1;
        if (limit1 <= 1) return 0;
        int answers = 0;
        List<Boolean> list = new ArrayList<>();
        list.add(false);
        for (int i = 0; i < limit; i++) {
            list.add(true);
        }

        for (int i = 2; Math.pow(i, 2.0) < limit1; i++) {
            if (list.get(i)) {
                for (int j = (int) Math.pow(i, 2.0); j < limit1; j += i) {
                    list.set(j, false);
                }
            }
        }

        for (boolean value: list) {
            if (value) answers++;
        }

        return answers;
    }

    @Benchmark @Fork(1) @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testMethod() {
        int n = 100000000;

        //int euler = calcPrimesNumberOfEuler(n);
        //int eratosthenes = calcPrimesNumberOfEratosthenes(n);
        int eratosthenes2 = calcPrimesNumberOfEratosthenesMassive(n);

        //int anotherEuler = anotherCalcPrimesNumberOfEuler(n);
        //int anotherEratosthenes = anotherCalcPrimesNumberOfEratosthenes(n);
    }

    //Сложность O(N*log(log(N)))
    //Память O(N)
    static public int anotherCalcPrimesNumberOfEratosthenes(int limit) {
        int limit1 = limit + 1;
        if (limit1 <= 1) return 0;
        int answers = 0;
        LinkedList<Boolean> list = new LinkedList<>();
        list.add(false);
        for (int i = 0; i < limit; i++) {
            list.add(true);
        }

        for (int i = 2; Math.pow(i, 2.0) < limit1; i++) {
            if (list.get(i)) {
                for (int j = (int) Math.pow(i, 2.0); j < limit1; j += i) {
                    list.set(j, false);
                }
            }
        }

        for (boolean value: list) {
            if (value) answers++;
        }

        return answers;
    }

    //Сложность O(N)
    //Память O(N + M)
    static public int anotherCalcPrimesNumberOfEuler(int limit) {
        if (limit < 1) return 0;
        int limit1 = limit + 1;
        LinkedList<Integer> pr = new LinkedList<>();
        int[] lp = new int[limit1];

        for (int i = 2; i < limit1; i++) {
            if (lp[i] == 0) {
                lp[i] = i;
                pr.add(i);
            }
            for (Integer p : pr) {
                if (p <= lp[i] && p * i <= limit) {
                    lp[p * i] = p;
                } else {
                    break;
                }
            }
        }
        pr.add(1);
        //System.out.println("limit = " + limit + " pr = " + pr);
        return pr.size();
    }

}

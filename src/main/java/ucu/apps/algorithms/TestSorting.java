package ucu.apps.algorithms;

import ucu.apps.algorithms.Sorting.*;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Locale;

public class TestSorting {

    private static final String FILE_PATH = "students.csv";
    private static final int[] SIZES = { 100, 1000, 10000, 100000 };
    private static final long DURATION_NS = 10000000000L;

    private static final SortingStrategy[] SORTERS = {
            new DefaultSorting(),
            new MergeSort()
    };

    private static final String[] SORTER_NAMES = {
            "DefaultSorting",
            "MergeSorting"
    };

    public static void main(String[] args) throws Exception {
        try (PrintWriter out = new PrintWriter(new FileWriter("sorting_results.csv"))) {
            out.println("sorter,size,operations,operations_per_second,mem_bytes");

            for (int size : SIZES) {
                for (int i = 0; i < SORTERS.length; i++) {
                    SortingStrategy sorter = SORTERS[i];
                    String sorterName = SORTER_NAMES[i];

                    System.out.printf("Running %s on size %d%n", sorterName, size);

                    DataBase db = new DataBase(FILE_PATH, size);
                    db.studentSorter = sorter;

                    long operations = 0;
                    long endTime = System.nanoTime() + DURATION_NS;
                    long beforeMem = usedMemory();

                    while (System.nanoTime() < endTime) {
                        db.sortStudents();
                        operations++;
                    }

                    long afterMem = usedMemory();
                    long mem = Math.max(beforeMem, afterMem);
                    double operationsPerSecond = operations / 10.0;

                    System.out.printf("-> %s (size=%d): %,d operations, %.1f operations per second, memory %,d%n",
                            sorterName, size, operations, operationsPerSecond, mem);

                    out.printf(Locale.ROOT, "%s,%d,%d,%.2f,%d%n",
                            sorterName, size, operations, operationsPerSecond, mem);

                    db = null;
                    System.gc();
                    Thread.sleep(500);
                }
            }
        }

        System.out.println("Sorting benchmark finished -> sorting_results.csv");
    }

    private static long usedMemory() {
        Runtime rt = Runtime.getRuntime();
        return rt.totalMemory() - rt.freeMemory();
    }
}
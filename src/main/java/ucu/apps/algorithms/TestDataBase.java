package ucu.apps.algorithms;

import ucu.apps.algorithms.EmailMap.*;
import ucu.apps.algorithms.GroupMap.*;
import ucu.apps.algorithms.TopStudents.*;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Random;

import lombok.Getter;

public class TestDataBase {

    private static final String FILE_PATH = "students.csv";
    private static final int[] SIZES = { 100, 1000, 10000, 100000 };
    private static final long DURATION = 10000000000L;
    private static final Random RANDOM = new Random();

    private static final int A = 50;
    private static final int B = 10;
    private static final int C = 5;

    private static final Variant[] VARIANTS = {
            new Variant("Variant 1", 1),
            new Variant("Variant 2", 2),
            new Variant("Variant 3", 3)
    };

    @Getter
    private static class Variant {
        String name;
        TopStudentsStrategy topStudentsStrategy;
        EmailMapStrategy emailMapStrategy;
        GroupMapStrategy groupMapStrategy;

        Variant(String name, int variant) {
            switch (variant) {
                case 1 -> {
                    this.topStudentsStrategy = new TopStudents1();
                    this.emailMapStrategy = new EmailMap1();
                    this.groupMapStrategy = new GroupMap1();
                }
                case 2 -> {
                    this.topStudentsStrategy = new TopStudents2();
                    this.emailMapStrategy = new EmailMap2();
                    this.groupMapStrategy = new GroupMap2();
                }
                case 3 -> {
                    this.topStudentsStrategy = new TopStudents3();
                    this.emailMapStrategy = new EmailMap3();
                    this.groupMapStrategy = new GroupMap3();
                }
            }
            this.name = name;
        }
    }

    public static void main(String[] args) throws Exception {
        try (PrintWriter out = new PrintWriter(new FileWriter("benchmark_results.csv"))) {
            out.println("variant,size,operations,operations_per_second,mem_bytes");

            for (int size : SIZES) {
                for (Variant variant : VARIANTS) {
                    System.out.printf("Running %s (size=%d)%n", variant.name, size);

                    DataBase db = new DataBase(FILE_PATH, size);
                    changeDB(db, variant);

                    long operations = 0;
                    long endTime = System.nanoTime() + DURATION;
                    long beforeMem = usedMemory();

                    while (System.nanoTime() < endTime) {
                        switch (pickNextOperation()) {
                            case 1 -> db.getTopStudents();
                            case 2 -> {
                                if (!db.students.isEmpty()) {
                                    Student s = db.students.get(RANDOM.nextInt(db.students.size()));
                                    db.updateRating(s.getEmail(), RANDOM.nextDouble() * 100);
                                }
                            }
                            case 3 -> db.getBestGroup();
                        }
                        operations++;
                    }

                    long afterMem = usedMemory();
                    long mem = Math.max(beforeMem, afterMem);
                    double operationsPerSecond = operations / 10.0;

                    System.out.printf("-> %s: %,d operations, %.1f operations per second, memory %,d%n",
                            variant.name, operations, operationsPerSecond, mem);

                    out.printf(Locale.ROOT, "%s,%d,%d,%.2f,%d%n",
                            variant.name, size, operations, operationsPerSecond, mem);

                    db = null;
                    System.gc();
                    Thread.sleep(500);
                }
            }
        }

        System.out.println("Benchmark finished -> benchmark_results.csv");
    }

    private static int pickNextOperation() {
        int r = RANDOM.nextInt(A + B + C);

        if (r < A)
            return 1;
        else if (r > C)
            return 3;
        else
            return 2;
    }

    private static void changeDB(DataBase db, Variant v) {
        db.changeTopStudentsStrategy(v.topStudentsStrategy);
        db.changeEmailMapStrategy(v.emailMapStrategy);
        db.changeGroupMapStrategy(v.groupMapStrategy);
    }

    private static long usedMemory() {
        Runtime rt = Runtime.getRuntime();
        return rt.totalMemory() - rt.freeMemory();
    }
}
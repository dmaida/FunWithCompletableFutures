/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.time.Duration;

@Command(name = "App", mixinStandardHelpOptions = true, version = "App 1.0",
        description = "A simple application that runs CompletableFutures")
public class App implements Runnable {

    @Option(names = {"-p", "--parallelism"}, description = "ForkJoinPool parallelism", required = false)
    private String parallelism;

    @Option(names = {"-t", "--threads"}, description = "Number of CompletableFutures to create", required = true)
    private int threads;

    @Option(names = {"-i", "--io"}, description = "Run IO Bound Experiment", required = false)
    private boolean isIOBoundEnabled = false;

    @Option(names = {"-c", "--cpu"}, description = "Run CPU Bound Experiment", required = false)
    private boolean isCPUBoundEnabled = false;

    private static void cpuBoundExperiment(int threads)  {
        List<CompletableFuture<Void>> completableFutures = IntStream.range(0, threads).mapToObj(i -> CPUBoundWorkload.start()).collect(Collectors.toList());
        completableFutures.forEach(cf -> cf.join());
    }

    private static void ioBoundExperiment(int threads)  {
        List<CompletableFuture<Void>> completableFutures = IntStream.range(0, threads).mapToObj(i -> IOBoundWorkload.start()).collect(Collectors.toList());
        completableFutures.forEach(cf -> cf.join());
    }

    @Override
    public void run() {
        System.out.println("========== Fun with CompletableFutures ==========");

        System.out.println("Inputs:");
        System.out.println("  --parallelism: " + parallelism);
        System.out.println("  --threads: " + threads);

        if (parallelism != null) {
            System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", parallelism);
        }

        System.out.println("System Config:");
        System.out.println("  CPU Core: " + Runtime.getRuntime().availableProcessors());
        System.out.println("  CommonPool Parallelism: " + ForkJoinPool.commonPool().getParallelism());

        long start = System.nanoTime();

        if (isCPUBoundEnabled) {
            System.out.println("\n=== Running CPU Experiment ===");
            cpuBoundExperiment(threads);
        }
        if (isIOBoundEnabled) {
            System.out.println("\n=== Running IO Experiment ===");
            ioBoundExperiment(threads);
        }

        System.out.println("Completed in " + Duration.ofNanos(System.nanoTime()  - start).toSeconds() + " seconds");
    }

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
}

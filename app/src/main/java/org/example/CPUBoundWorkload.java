package org.example;

import java.util.concurrent.CompletableFuture;

public class CPUBoundWorkload {

    private static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static CompletableFuture<Void> start () {
        return CompletableFuture.runAsync(() -> {
            System.out.println("Current thread: " + Thread.currentThread().getName());
            fibonacci(45);
        });
    }
}

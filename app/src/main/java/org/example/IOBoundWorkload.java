package org.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class IOBoundWorkload {
    public static CompletableFuture<Void> start() {
        return CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Current thread: " + Thread.currentThread().getName());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Void> start(Executor executor) {
        return CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, executor);
    }
}

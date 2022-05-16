package ua.knu.diningphilosophers;

import java.util.concurrent.Semaphore;

class LockProvider {

    private static final Semaphore SEMAPHORE = new Semaphore(1);

    static void acquire() {
        try {
            SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void release() {
        SEMAPHORE.release();
    }

}

package ua.knu.diningphilosophers;

class Philosopher extends Thread {

    private final int identity;
    private boolean stopRequested;
    Chopstick left;
    Chopstick right;

    Philosopher(int identity, Chopstick left, Chopstick right) {
        this.identity = identity;
        this.left = left;
        this.right = right;
    }

    public void stopRequested() {
        stopRequested = true;
        System.out.println("Philosopher " + identity + " is going to leave the room");
    }

    public void run() {
        try {
            while (!stopRequested) {
                think();
                hungry();
                withRetry(() -> performWithinLock(this::takeForks));
                eat();
                putForks();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + identity + " is thinking");
        sleep((int) (1000 * Math.random()));
    }

    private void hungry() {
        System.out.println("Philosopher " + identity + " is hungry");
    }

    private void withRetry(Runnable runnable) {
        boolean tookChopsticks = false;
        while (!tookChopsticks) {
            try {
                runnable.run();
                tookChopsticks = true;
            } catch (ChopsticksAreTakenException ignored) {
            }
        }
    }

    private void performWithinLock(Runnable runnable) {
        LockProvider.acquire();
        runnable.run();
        LockProvider.release();
    }

    private void takeForks() {
        try {
            if (!right.isTaken() && !left.isTaken()) {
                right.take();
                System.out.println("Philosopher " + identity + " took right fork");

                sleep(500);

                left.take();
                System.out.println("Philosopher " + identity + " took left fork");
            } else {
                throw new ChopsticksAreTakenException();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + identity + " is eating");
        sleep((int) (1000 * Math.random()));
    }

    private void putForks() {
        right.put();
        left.put();
    }

}
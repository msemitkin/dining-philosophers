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

    public void run() {
        try {
            while (!stopRequested) {
                System.out.println("Philosopher " + identity + " is thinking");
                sleep(500 * (int) (100 * Math.random()));

                System.out.println("Philosopher " + identity + " is hungry");
                performWithinLock(() -> {
                    try {
                        right.get();
                        System.out.println("Philosopher " + identity + " took right fork");

                        sleep(500);

                        left.get();
                        System.out.println("Philosopher " + identity + " took left fork");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                System.out.println("Philosopher " + identity + " is eating");
                sleep((int) (500 * Math.random()));
                right.put();
                left.put();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void performWithinLock(Runnable runnable) {
        LockProvider.acquire();
        runnable.run();
        LockProvider.release();
    }


    public void stopRequested() {
        stopRequested = true;
        System.out.println("Philosopher " + identity + " is going to leave the room");
    }
}
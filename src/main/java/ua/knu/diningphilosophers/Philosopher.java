package ua.knu.diningphilosophers;

class Philosopher extends Thread {

    int identity;
    boolean stopRequested = false;
    Chopstick left;
    Chopstick right;

    Philosopher(int identity, Chopstick left, Chopstick right) {
        this.identity = identity;
        this.left = left;
        this.right = right;
    }

    public void run() {
        while (!stopRequested) {
            try {
                System.out.println("Philosopher " + identity + " is thinking");
                wait();
                sleep(500 * (int) (100 * Math.random()));

                System.out.println("Philosopher " + identity + " is hungry");

                right.get();
                System.out.println("Philosopher " + identity + " took right fork");

                sleep(500);

                left.get();
                System.out.println("Philosopher " + identity + " took left fork and started eating");
                sleep((int) (500 * Math.random()));

                right.put();
                left.put();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void stopRequested() {
        stopRequested = true;
    }
}
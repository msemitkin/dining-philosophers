package ua.knu.diningphilosophers;

class Chopstick {

    boolean taken = false;
    int identity;

    Chopstick(int identity) {
        this.identity = identity;
    }

    synchronized void put() {
        taken = false;
        System.out.println("Fork " + identity + " is put back on the table");
        notify();
    }

    synchronized void get() throws InterruptedException {
        while (taken) { // if the fork is already taken by another philosopher, we need to wait until it will be put again
            wait();
        }
        taken = true;
        System.out.println("Fork " + identity + " is taken");
    }
}
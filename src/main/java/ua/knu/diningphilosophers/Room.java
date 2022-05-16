package ua.knu.diningphilosophers;

import java.util.Scanner;

class Room {
    private Philosopher[] philosophers;
    private Chopstick[] chopsticks;

    Room() {
        initChopsticks();
        initPhilosophers();
    }

    void doActions() {
        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }

        new Scanner(System.in).next("exit");

        for (Philosopher philosopher : philosophers) {
            philosopher.stopRequested();
        }
    }

    private void initChopsticks() {
        chopsticks = new Chopstick[5];
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new Chopstick(i);
        }
    }

    private void initPhilosophers() {
        philosophers = new Philosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % 5]);
        }
    }

}

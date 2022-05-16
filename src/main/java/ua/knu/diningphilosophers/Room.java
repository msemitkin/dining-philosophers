package ua.knu.diningphilosophers;

public class Room {
    private Philosopher[] philosophers;
    private Chopstick[] chopsticks;

    public Room() {
        initChopsticks();
        initPhilosophers();
    }

    void doActions() {
        while (true) {
            for (Philosopher philosopher : philosophers) {
                philosopher.start();
            }
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

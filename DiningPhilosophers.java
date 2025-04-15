import java.util.concurrent.*;

public class DiningPhilosophers {

    static class Fork {
        private final Semaphore semaphore = new Semaphore(1);
        public void pickUp() throws InterruptedException { semaphore.acquire(); }
        public void putDown() { semaphore.release(); }
    }

    static class Philosopher extends Thread {
        private final int id;
        private final Fork leftFork, rightFork;

        public Philosopher(int id, Fork leftFork, Fork rightFork) {
            this.id = id;
            this.leftFork = leftFork;
            this.rightFork = rightFork;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    think();
                    leftFork.pickUp();
                    rightFork.pickUp();
                    eat();
                    leftFork.putDown();
                    rightFork.putDown();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void think() {
            System.out.println("Philosopher " + id + " is thinking.");
            try { Thread.sleep((int) (Math.random() * 1000)); } catch (InterruptedException e) {}
        }

        private void eat() {
            System.out.println("Philosopher " + id + " is eating.");
            try { Thread.sleep((int) (Math.random() * 1000)); } catch (InterruptedException e) {}
        }
    }

    public static void main(String[] args) {
        final int NUM_PHILOSOPHERS = 5;
        Fork[] forks = new Fork[NUM_PHILOSOPHERS];

        // Initialize forks and philosophers
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) forks[i] = new Fork();
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            Fork left = forks[i];
            Fork right = forks[(i + 1) % NUM_PHILOSOPHERS];
            new Philosopher(i + 1, left, right).start();
        }
    }
}

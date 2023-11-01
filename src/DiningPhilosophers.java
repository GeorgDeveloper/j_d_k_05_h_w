import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    public static void main(String[] args) {
        Semaphore[] forks = new Semaphore[5];
        Philosopher[] philosophers = new Philosopher[5];

        for (int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1); // Инициализация вилок
        }

        for (int i = 0; i < 5; i++) {
            int leftForkIndex = i;
            int rightForkIndex = (i + 1) % 5;
            philosophers[i] = new Philosopher(i, forks[leftForkIndex], forks[rightForkIndex]);
            philosophers[i].start();
        }
    }
}
import java.util.concurrent.Semaphore;

class Philosopher extends Thread {
    private int id;
    private Semaphore leftFork;
    private Semaphore rightFork;

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void eat() throws InterruptedException {
        System.out.println("Философ " + id + " начал есть.");
        Thread.sleep(500); // Время обеда
        System.out.println("Философ " + id + " закончил есть.");
    }

    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " начал размышлять.");
        Thread.sleep(500); // Время размышлений
        System.out.println("Философ " + id + " закончил размышлять.");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) { // Философ должен пообедать три раза
            try {
                leftFork.acquire(); // Философ берет левую вилку
                rightFork.acquire(); // Философ берет правую вилку

                eat(); // Философ ест

                rightFork.release(); // Философ освобождает правую вилку
                leftFork.release(); // Философ освобождает левую вилку

                think(); // Философ размышляет
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class StopwatchTest {
    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();

        while (true) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            double seconds = elapsedTime / 1000.0;

            System.out.println("Time: " + seconds);

            Thread.sleep(1000);
        }
    }
}

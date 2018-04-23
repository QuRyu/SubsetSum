/**
 * Author: Qingbo Liu
 * the main entry to the project
 */

public class Driver {
    private static long sequentialRuntime = 0;

    private static int[] testSet;
    private static int sum = 8399;


    public static void main(String[] args) {
        populateList();
    }

    private static void populateList() {
        testSet = new int[1000];
        for (int i = 0; i < 1000; i++)
            testSet[i] = i;
    }

    private static void test(String version, SubsetSum ss) {
        boolean result;

        // warm up
        result = ss.isSumPresent(sum, testSet);
        result = ss.isSumPresent(sum, testSet);


        Timer.start();
        result = ss.isSumPresent(sum, testSet);
        Timer.stop();

        System.out.println("---------" + version + "----------");

        System.out.println("The desired sum is " + sum);

        System.out.println("Time: " + Timer.getRuntime() + "ms");

        if (sequentialRuntime == 0)
            sequentialRuntime = Timer.getRuntime();
        else
            System.out.printf("Speed-up: %.2f\n",
                    sequentialRuntime/1.0/Timer.getRuntime());
        System.out.println();
    }

}

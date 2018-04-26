/**
 * Author: Qingbo Liu
 * the main entry to the project
 */

public class SpeedDriver {
    private static long sequentialRuntime = 0;

    private static long[] testSet1;
    private static long[] testSet2;
    private static long sum1 = 8399;
    private static long sum2 = 415104;


    public static void main(String[] args) {
        populateList();

        test("Exhaustive Search", new SubsetSumExhaustive());
        test("Dynamic Search", new SubsetSumDynamic());
        test("Random version", new RandomAlgo());
        test("Greedy Search", new Greedy());
        test("Hill Climbling", new HillClimbing());
        test("Annealing", new Annealing());
    }

    private static void populateList() {
        testSet1 = new long[20];
        for (int i = 0; i < testSet1.length; i++)
            testSet1[i] = i;

        testSet2 = new long[20];
        for (int i = 0; i < testSet2.length; i++)
            testSet2[i] = i * 3;
    }

    private static void test(String version, SubsetSum ss) {
         long result;

        // warm up
        result = ss.subSubSum(sum1, testSet1, testSet1.length);
        result = ss.subSubSum(sum2, testSet2, testSet2.length);


        Timer.start();
        result = ss.subSubSum(sum1, testSet1, testSet1.length);
        result = ss.subSubSum(sum2, testSet2, testSet2.length);
        Timer.stop();

        System.out.println("---------" + version + "----------");

        System.out.println("Time: " + Timer.getRuntime() + "ms");

        if (sequentialRuntime == 0)
            sequentialRuntime = Timer.getRuntime();
        else
            System.out.printf("Speed-up: %.2f\n",
                    sequentialRuntime/1.0/Timer.getRuntime());
        System.out.println();
    }

}

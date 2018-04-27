import java.util.ArrayList;
import java.util.List;

/**
 * Author: Qingbo Liu
 * the main entry to the project
 */

public class SpeedDriver {
    private static long sequentialRuntime = 0;

    private static List<long[]> testSet;
    private static long[] sums;

    private static long[] testSet1;
    private static long[] testSet2;
    private static long[] testSet3;
    private static long[] testSet4;
    private static long sum1 = 8399;
    private static long sum2 = 415104;
    private static long sum3 = 23134;
    private static long sum4 = 4123;

    private static long[][][] speed;
    private static int runtime = 0;
    private static int speedup = 1;

    public static void main(String[] args) {
        populateList();

        test("Exhaustive Search", new SubsetSumExhaustive(), 0);
        test("Dynamic Search", new SubsetSumDynamic(), 1);
        test("Random version", new RandomAlgo(), 2);
        test("Greedy Search", new Greedy(), 3);
        test("Hill Climbling", new HillClimbing(), 4);
        test("Annealing", new Annealing(), 5);

        System.out.println("Running time table of each algorithm for different subsets: unit, µs");
        printSpeedTable(runtime);
        System.out.println();
        System.out.println();
        System.out.println("Speed up of each algorithm with respect to exhaustive search");
        printSpeedTable(speedup);
    }

    private static void populateList() {
        testSet1 = new long[5];
        for (int i = 0; i < testSet1.length; i++)
            testSet1[i] = i;

        testSet2 = new long[10];
        for (int i = 0; i < testSet2.length; i++)
            testSet2[i] = i * 3;

        testSet3 = new long[15];
        for (int i = 0; i < testSet2.length; i++)
            testSet2[i] = i * 5;

        testSet4 = new long[20];
        for (int i = 0; i < testSet2.length; i++)
            testSet2[i] = i * 8;

        testSet = new ArrayList<long[]>();
        testSet.add(testSet1);
        testSet.add(testSet2);
        testSet.add(testSet3);
        testSet.add(testSet4);

        sums = new long[4];
        sums[0] = sum1;
        sums[1] = sum2;
        sums[2] = sum3;
        sums[3] = sum4;

        speed = new long[6][4][2];
    }

    private static void test(String version, SubsetSum ss, int n) {
         long result;


        for (int i = 0; i < 4; i++) {
            //warm up
            result = ss.subSubSum(sums[i], testSet.get(i), testSet.get(i).length);
            result = ss.subSubSum(sums[i], testSet.get(i), testSet.get(i).length);

            Timer.start();
            result = ss.subSubSum(sums[i], testSet.get(i), testSet.get(i).length);
            Timer.stop();

            speed[n][i][runtime] = Timer.getRuntime();
            if (n == 0)
                speed[n][i][speedup] = 1;
            else
                speed[n][i][speedup] = (long)(speed[0][i][runtime]/1.0/Timer.getRuntime());
        }
    }

    private static void printSpeedTable(int n) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(speed[i][j][n] + "  ");
            }
            System.out.println();
        }

    }
}

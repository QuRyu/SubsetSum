import java.lang.Math;

public class AccuracyDriver {

    private static long[] testSet1;
    private static long sum1 = (long)(25*Math.pow(10,12));
    private static long base_residue;


    public static void main(String[] args) {
        populateList();
        SubsetSum exha = new SubsetSumExhaustive();
        long base_residue = exha.subSubSum(sum1, testSet1, testSet1.length);

        test("Random version", new RandomAlgo());
        test("Greedy Search", new Greedy());
        test("Hill Climbling", new HillClimbing());
        test("Annealing", new Annealing());
    }

    private static void populateList() {
        testSet1 = new long[100];
        long leftLimit = 1;
        long rightLimit = (long) Math.pow(10,12);
        for (int i = 0; i < testSet1.length; i++){
          testSet1[i] = (long)(leftLimit + Math.random()*(rightLimit-leftLimit));
        }

    }

    private static void test(String version, SubsetSum ss) {
        long result;

        result = ss.subSubSum(sum1, testSet1, testSet1.length);

        System.out.println("---------" + version + "----------");

        System.out.println("Accuracy: %.2f" + base_residue/result + "%");

        System.out.println();
    }

}

public class SubsetSumDynamic implements SubsetSum {

    public boolean isSumPresent(int k, int[] set) {
        // TODO: 4/22/18 add guard against cases when set is empty or k is 0
        boolean[][] table = new boolean[set.length+1][k+1];

        if ((set.length == 0 && k != 0)
                || k == 0)
            return true;

        for (int i = 0; i < k; i++)
            table[0][i] = set[0] == (i+1);


        for (int i = 1; i < set.length; i++) {
            for (int j = 0; j < k; j++) {
                table[i][j] = table[i-1][j] || (set[i] == (j+1));

                if (j >= set[i])
                    table[i][j] = table[i][j] || table[i-1][j-set[i]];
            }
        }

        return table[set.length-1][k-1];
    }

    public static void main(String[] args) {
        int set[] = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        int n = set.length;
        SubsetSumDynamic algo = new SubsetSumDynamic();

        if (algo.isSumPresent(sum, set))
            System.out.println("Found a subset with given sum");
        else
            System.out.println("the algorithm is not correct");
    }

}

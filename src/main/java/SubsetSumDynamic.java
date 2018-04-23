public class SubsetSumDynamic implements SubsetSum {

    public boolean isSumPresent(int k, int[] set) {
        boolean[][] table = new boolean[set.length+1][k+1];

        for (int i = 0; i < k; i++)
            table[0][i] = set[0] == (i+1);


        for (int i = 1; i < set.length; i++) {
            for (int j = 0; j < k; j++) {
                table[i][j] = table[i-1][j] || (set[i] == (j+1));

                if (j+1 >= set[i])
                    table[i][j] = table[i][j] || table[i-1][(j+1)-set[i]];
            }
        }

        return table[set.length-1][k-1];
    }

    public static void main(String[] args) {
        int set[] = {3, 34, 4, 12, 5, 2};
        int sum = 54;
        int n = set.length;
        SubsetSumDynamic algo = new SubsetSumDynamic();

        if (algo.isSumPresent(sum, set))
            System.out.println("Found a subset with given sum");
        else
            System.out.println("the algorithm is not correct");
    }


}

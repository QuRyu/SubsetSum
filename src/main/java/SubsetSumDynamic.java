public class SubsetSumDynamic implements SubsetSum {

    public boolean isSumPresent(int k, int[] set) {
        boolean[][] table = new boolean[set.length+1][k+1];

        for (int i = 1; i <= k; i++)
            table[1][i] = set[0] == i;


        for (int i = 2; i <= set.length; i++) {
            for (int j = 1; j <= k; j++) {
                table[i][j] = table[i-1][j] || (set[i-1] == j);

                if (j >= set[i-1])
                    table[i][j] = table[i][j] || table[i-1][j-set[i-1]];
            }
        }

        for (int i = 1; i <= set.length; i++) {
            for (int j = 1; j <= k; j++)
                System.out.print(table[i][j] + " ");
            System.out.println();
        }

        return table[set.length][k];
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

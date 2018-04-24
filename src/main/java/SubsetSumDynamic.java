public class SubsetSumDynamic implements SubsetSum {

    public boolean isSumPresent(int k, int[] set) {
        boolean[][] table = new boolean[2][k];
        int curRow = 0;

        if ((set.length == 0 && k == 0)
                || k == 0)
            return true;
        else if (set.length == 0) // guaranteed that k != 0
            return false;

        for (int i = 0; i < k; i++)
            table[0][i] = set[0] == (i+1);

        for (int i = 1; i < set.length; i++) {
            int preRow = curRow;
            curRow = rotateRow(curRow);

            for (int j = 0; j < k; j++) {
                table[curRow][j] = table[preRow][j] || (set[i] == (j+1));

                if (j >= set[i])
                    table[curRow][j] = table[curRow][j] || table[preRow][j-set[i]];
            }
        }

        return table[curRow][k-1];
    }

    public int rotateRow(int curRow) {
        return (curRow+1) % 2;
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

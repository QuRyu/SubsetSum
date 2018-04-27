// Author: Qingbo Liu
// Implementation of the dynamic programming version of the subset problem;
// optimized by using a 2-dimension array of length 2*k: the observation behind
// this optimization is that the recurrence relation only uses the information from
// current and previous row. All other rows do not need to be stored

// The interface of dynamic implementation is that when the sum is present, it would
// return 0; if not, -1.
public class SubsetSumDynamic implements SubsetSum {

    // the public entry
    public long subSubSum(long k, long[] set,  int iteration) {
        if (isSumPresent((int)k, set))
            return 0;
        else
            return -1;
    }

    // the method to solve the subset sum problem
    private boolean isSumPresent(int k, long[] set) {
        boolean[][] table = new boolean[2][(int)k];
        int curRow = 0; // points to the current row the method is manipulating over

        // precondition check
        if (k == 0)  // desired sum of 0 is always trivially satisfied by an empty set
            return true;
        else if (set.length == 0) // guaranteed that k != 0 and therefore the answer must be false
            return false;

        // initialize the first row
        for (int i = 0; i < k; i++)
            table[0][i] = set[0] == (i+1);


        for (int i = 1; i < set.length; i++) {
            int preRow = curRow; // the index of the previous row from which the method extracts information to build the current row
            curRow = rotateRow(curRow);

            for (int j = 0; j < k; j++) {
                table[curRow][j] = table[preRow][j] || (set[i] == (j+1));

                if (j >= set[i]) // a check to prevent ArrayOutOfBounds exception
                    table[curRow][j] = table[curRow][j] || table[preRow][(int)(j-set[i])];
            }
        }

        return table[curRow][k-1];
    }

    // change the index of row to one whose information can be discarded now
    public int rotateRow(int curRow) {
        return (curRow+1) % 2;
    }

    public static void main(String[] args) {
        int set[] = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        int n = set.length;
        SubsetSumDynamic algo = new SubsetSumDynamic();

       // if (algo.isSumPresent(sum, set))
       //     System.out.println("Found a subset with given sum");
       // else
       //     System.out.println("the algorithm is not correct");
    }

}

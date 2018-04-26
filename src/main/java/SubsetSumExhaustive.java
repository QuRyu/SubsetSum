import java.util.ArrayList;
import java.util.List;

/**
 * Author: Qingbo Liu
 *
 * The implementation of exhaustive search of subset sum problem.
 * it works by generating all possible subsets of a given set and checks if any of them
 * added together would give the value.
 *
 * One optimization to reduce the number of subsets generated is based on the observation
 * that only those each subsets whose sum is equal to or smaller than the desired sum should be kept.
 * Therefore elements and subsets whose sums are greater than desired sum k should be discarded.
 */

public class SubsetSumExhaustive implements SubsetSum {
    public boolean isSumPresent(int k, int[] set) {
        List<List<Integer>> subsets = new ArrayList<List<Integer>>();
        isSumPresentHelper(subsets, set, set.length, k);

        for (List<Integer> subset :
                subsets) {
            if (sumList(subset) == k)
                return true;
        }
        return false;
    }

    private void isSumPresentHelper(List<List<Integer>> subsets, int[] set, int n, int k) {
        if (n == 0) {
            subsets.add(new ArrayList<Integer>());
        } else {
            isSumPresentHelper(subsets, set, n-1, k);

            if (set[n-1] > k)
                return;

            List<Integer> result;
            int len = subsets.size();
            for (int i = 0; i < len; i++) {
                result = new ArrayList<Integer>(subsets.get(i));
                result.add(set[n-1]);
                if (sumList(result) <= k)
                    subsets.add(result);
            }
        }
    }

    private int sumList(List<Integer> list) {
        int sum = 0;
        for (Integer i :
                list) {
            sum += i;
        }
        return sum;
    }

}

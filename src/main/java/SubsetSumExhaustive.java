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
 *
 * The interface of the exhaustive implementation works in a way that if the sum is present, the
 * algorithm returns 0; if not, -1.
 */

public class SubsetSumExhaustive implements SubsetSum {

    public long subSubSum(long k, long[] set, int iteration) {
        List<List<Long>> subsets = new ArrayList<List<Long>>();
        isSumPresentHelper(subsets, set, set.length, k);

        for (List<Long> subset :
                subsets) {
            if (sumList(subset) == k)
                return 0;
        }
        return -1;
    }

    private void isSumPresentHelper(List<List<Long>> subsets, long[] set, int n, long k) {
        if (n == 0) {
            subsets.add(new ArrayList<Long>());
        } else {
            isSumPresentHelper(subsets, set, n-1, k);

            if (set[n-1] > k)
                return;

            ArrayList<Long> result;
            int len = subsets.size();
            for (int i = 0; i < len; i++) {
                result = new ArrayList<Long>(subsets.get(i));
                result.add(set[n-1]);
                if (sumList(result) <= k) {
                    result.trimToSize();
                    subsets.add(result);
                }
            }
        }
    }

    private long sumList(List<Long> list) {
        long sum = 0;
        for (Long i :
                list) {
            sum += i;
        }
        return sum;
    }

}

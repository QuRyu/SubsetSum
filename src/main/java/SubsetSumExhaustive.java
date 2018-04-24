import java.util.ArrayList;
import java.util.List;

/**
 * Author: Qingbo Liu
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

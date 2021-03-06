/**
 * Author: Qingbo Liu
 *
 * It tests the implementation by generating all subsets of a specific set and sees if the
 * implementation will yield true for all values.
 */

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SubsetSumTest {

    private int[] testSet1 = {3, 34, 4, 12, 5, 2};
    private List<List<Integer>> testSetSubSets1;
    private List<SubsetSum> finders;

    @Before
    public void initialize() {
        finders = new ArrayList<SubsetSum>();
        finders.add(new SubsetSumDynamic());
        finders.add(new SubsetSumExhaustive());

        testSetSubSets1 = new ArrayList<List<Integer>>();
        generateSubsets(testSetSubSets1, testSet1, testSet1.length);
    }


    // test if the helper method is working correctly
    @Test
    public void generateSubsetsTest() {
        assertEquals(64, testSetSubSets1.size());
    }

    // main entry for testing the implementation
    @Test
    public void isSumPresentest() {
        for (SubsetSum sum :
                finders) {
            // test all combinations of a given set
            for (List<Integer> list:
                    testSetSubSets1){
                assertEquals("expecting " + sumList(list) + ", with list " + list.toString(),
                        0, sum.subSubSum(sumList(list), toIntArr(list), 1));
            }

            // test empty set
            //assertFalse(sum.isSumPresent(1, new int[0]));
            //assertTrue(sum.isSumPresent(0, new int[0]));

        }

    }


    private void generateSubsets(List<List<Integer>> subsets, int[] set, int n) {
        if (n == 0) {
            subsets.add(new ArrayList<Integer>());
        } else {
            generateSubsets(subsets, set, n-1);
            List<Integer> result;
            int len = subsets.size();
            for (int i = 0; i < len; i++) {
                result = new ArrayList<Integer>(subsets.get(i));
                result.add(set[n-1]);
                subsets.add(result);
            }

        }
    }

    private int sumList(List<Integer> list) {
        int sum = 0;
        for (Integer aList : list) {
            sum += aList;
        }
        return sum;
    }

    private long[] toIntArr(List<Integer> list) {
        long[] arr = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}

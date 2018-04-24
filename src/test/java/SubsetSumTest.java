/**
 * Author: Qingbo Liu
 */

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SubsetSumTest {

    private int[] testSet1 = {3, 34, 4, 12, 5, 2};
    private List<List<Integer>> testSetSubSets1;
    private List<SubsetSum> finders;

    @Before
    public void initialize() {
        finders = new ArrayList<SubsetSum>();
        finders.add(new SubsetSumDynamic());

        testSetSubSets1 = new ArrayList<List<Integer>>();
        generateSubsets(testSetSubSets1, testSet1, testSet1.length);
    }


    @Test
    public void generateSubsetsTest() {
        assertEquals(64, testSetSubSets1.size());
    }

    @Test
    public void isSumPresentest() {
        for (SubsetSum sum :
                finders) {
            for (List<Integer> list:
                    testSetSubSets1){
                assertTrue("expecting " + sumList(list) + ", with list " + list.toString(),
                        sum.isSumPresent(sumList(list), toIntArr(list)));
            }

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
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum;
    }

    private int[] toIntArr(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}

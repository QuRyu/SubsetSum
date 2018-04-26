// CS375 Project 4
// HillClimbing Algorithm
// This algorithm repeatedly chooses a random subset of S and checks its residue.
// It keeps track of the smallest residue it found as it is doing so.
import java.util.Random;
import java.io.*;
import java.util.*;

public class HillClimbing implements SubsetSum {

    public long subSubSum(long k, long[] set, long iteration) {
      long min_residue = Long.MAX_VALUE;
      long[] sublist;

      long[] clone;
      clone = new long[set.length];
      long[] current;
      current = new long[set.length];


      // create a random sublist
      sublist = new long[set.length];
      for ( int j = 0; j < set.length; j++ ){
          Random rand = new Random();
          if ( rand.nextInt(10) < 5 ){
              sublist[j] = set[j];
          }
      }

      // calculate the sum of current sublist
      long sum_old = 0;
      for (int i = 0; i < sublist.length; i++){
          sum_old = sum_old + sublist[i];
      }

      // calculate the residue of current sublist
      long residue_old;
      residue_old = Math.abs(k-sum_old);

      current = sublist;
      min_residue = residue_old;

      for (int x = 0; x < iteration; x++) {
          // generate the neighbor
          //clone = current;
          clone = current.clone();
          int i, j;
          Random rand = new Random();
          i = rand.nextInt(set.length);
          j = rand.nextInt(set.length);
          while (i == j) {
              i = rand.nextInt(set.length);
              j = rand.nextInt(set.length);
          }

          // randomly generate a neighbor of the current subset
          // check if the i and j index of the input list is in the current_subset
          if (set[i] == clone[i]) {
              clone[i] = 0;     // remove the element from the current list
          } else {
              clone[i] = set[i];
          }
          if (set[j] == clone[j]) {
              clone[j] = 0;     // remove the element from the current list
          } else {
              clone[j] = set[j];
          }

          // calculate the sum of neighbor sublist
          long sum_neig = 0;
          for (int l = 0; l < clone.length; l++) {
              sum_neig = sum_neig + clone[l];
          }

          // calculate the residue of current sublist
          long residue_neig;
          residue_neig = Math.abs(k - sum_neig);
         // System.out.println("Residue neig " + residue_neig);
         // System.out.println("Min Residue " + min_residue);
          // compare the current residue with the min_residue
          if (residue_neig < min_residue) {
              min_residue = residue_neig;
              current = clone.clone();
          } else {
          }
         // System.out.println("Current :" + Arrays.toString(current));
      }

      return min_residue;
    }

  public static void main(String[] args){
    long[] list;
    list = new long[]{1,3,4,2,6,9,10,24,11,23,25,28,29,30,34,99};
    long target = 100;
    int num = 10;
    SubsetSum test = new HillClimbing();

    System.out.println("Input list: " + Arrays.toString(list));
    System.out.println("Target Sum: " + target);
    System.out.println("Minimum Residue: " + test.subSubSum(target, list, num));
  }

}

// CS375 Project 4
// HillClimbing Algorithm
// This algorithm repeatedly chooses a random subset of S and checks its residue.
// It keeps track of the smallest residue it found as it is doing so.
import java.util.Random;
import java.lang.*;
import java.util.Arrays;

public class HillClimbing{
  private long min_residue;

  public HillClimbing(long[] list, long target, int num){
    min_residue = Long.MAX_VALUE;
    long[] sublist;

    long[] clone;
    clone = new long[list.length];
    long[] current;
    current = new long[list.length];


    // create a random sublist
    sublist = new long[list.length];
    for ( int j = 0; j < list.length; j++ ){
      Random rand = new Random();
      if ( rand.nextInt(10) < 5 ){
        sublist[j] = list[j];
      }
    }

    // calculate the sum of current sublist
    long sum_old = 0;
    for (int k = 0; k < sublist.length; k++){
      sum_old = sum_old + sublist[k];
    }

    // calculate the residue of current sublist
    long residue_old;
    residue_old = Math.abs(target-sum_old);

    current = sublist;
    min_residue = residue_old;

    for (int x = 0; x < num; x++){
      // generate the neighbor
      //clone = current;
      clone = current.clone();
      int i, j;
      Random rand = new Random();
      i = rand.nextInt(list.length);
      j = rand.nextInt(list.length);
      while ( i == j ){
        i = rand.nextInt(list.length);
        j = rand.nextInt(list.length);
      }

      // randomly generate a neighbor of the current subset
      // check if the i and j index of the input list is in the current_subset
      if ( list[i]==clone[i] ){
        clone[i] = 0;     // remove the element from the current list
      }
      else{
        clone[i] = list[i];
      }
      if ( list[j]==clone[j] ){
        clone[j] = 0;     // remove the element from the current list
      }
      else{
        clone[j] = list[j];
      }

      // calculate the sum of neighbor sublist
      long sum_neig = 0;
      for (int k = 0; k < clone.length; k++){
        sum_neig = sum_neig + clone[k];
      }

      // calculate the residue of current sublist
      long residue_neig;
      residue_neig = Math.abs(target-sum_neig);
      System.out.println("Residue neig " + residue_neig);
      System.out.println("Min Residue " + min_residue);
      // compare the current residue with the min_residue
      if (residue_neig < min_residue){
        min_residue = residue_neig;
        current = clone.clone();
      }
      else{
      }
      System.out.println("Current :" + Arrays.toString(current));
    }
  }

  public long getResidue(){
    return min_residue;
  }

  public static void main(String[] args){
    long[] list;
    list = new long[]{1,3,4,2,6,9,10,24,11,23,25,28,29,30,34,99};
    long target = 100;
    int num = 10;
    HillClimbing test = new HillClimbing(list, target, num);

    System.out.println("Input list: " + Arrays.toString(list));
    System.out.println("Target Sum: " + target);
    System.out.println("Minimum Residue: " + test.getResidue());
  }

}

// CS375 Project 4
// Random Algorithm
// This algorithm repeatedly chooses a random subset of S and checks its residue.
// It keeps track of the smallest residue it found as it is doing so.
import java.util.Random;
import java.lang.*;
import java.util.Arrays;

public class RandomAlgo{
  private long min_residue;
  private long[] bestlst;

  public RandomAlgo(long[] list, long target, int num){
    min_residue = Long.MAX_VALUE;
    bestlst = new long[list.length];
    // loop num times
    for (int i = 0; i < num; i++){
      long[] sublist;


      // create a random sublist
      sublist = new long[list.length];
      for ( int j = 0; j < list.length; j++ ){
        Random rand = new Random();
        if ( rand.nextInt(10) < 5 ){
          sublist[j] = list[j];
        }
      }

      // calculate the sum of current sublist
      long sum = 0;
      for (int k = 0; k < sublist.length; k++){
        sum = sum + sublist[k];
      }

      // calculate the residue
      long residue;
      residue = Math.abs(target-sum);

      // compare the current residue with the min_residue
      if (residue < min_residue){
        min_residue = residue;
        bestlst = sublist;
      }
    }
    System.out.println("Best list:" + Arrays.toString(bestlst));
  }

  public long getResidue(){
    return min_residue;
  }

  public static void main(String[] args){
    long[] list;
    list = new long[]{1,3,4,2,6,9,10,24,11,23,25,28,29,30,34,99};
    long target = 100;
    int num = 100;
    RandomAlgo test = new RandomAlgo(list, target, num);

    System.out.println("Input list: " + Arrays.toString(list));
    System.out.println("Target Sum: " + target);
    System.out.println("Minimum Residue: " + test.getResidue());
  }

}

//Implement the Simulated Annealing Algorithm
//Zilin Chen
//4/24/2018
import java.lang.Math;


public class Annealing implements SubsetSum{

	//implement interface
	public long subSubSum(long k, long[] arr, int iteration){
		// System.out.println("This is the Simulated Annealing Algorithm: \n");
		long[] subset = generateRandomSubset(arr);
		long smallest_r = getSmallestResidue(arr, subset, iteration);
		return smallest_r;
	}

	// public static long Annealing(long[] arr, int iteration){
	// 	long[] subset = generateRandomSubset(arr);
	// 	long smallest_r = getSmallestResidue(arr, subset, iteration);
	// 	return smallest_r;
	// }

	//generate list of 100 random numbers from 1 to 10^12
	private long[] generateRandomList(int num){
		long[] list = new long[num];
		long leftLimit = 1;
		long rightLimit = (long) Math.pow(10,12);
		// System.out.print(leftLimit+(rightLimit-leftLimit)*Math.random());
		for (int i=0; i<num ; i++) {
			list[i] = (long)(leftLimit + Math.random()*(rightLimit-leftLimit));
			// System.out.print(list[i]+"\n");
		}
		return list;
	}

	//print out the list
	private void printList(long[] list){
		for (int i=0; i<list.length ;i++ ) {
			System.out.print(i + ": " + list[i] + "\n");
		}
		System.out.println();
	}

	
	//Choose a random subset of S as the current subset.
	private long[] generateRandomSubset(long[] originalList){
		double probability = 0.5;
		long[] subset = new long[originalList.length];
		//every element has 50% change of being added to the sublist
		for (int i=0;i<originalList.length ;i++ ) {
			if (Math.random() >= probability) {
				subset[i] = originalList[i];
			}
		}
		return subset;
	}

	//generate neighbor set
	private long[] generateRandomNeighbor(long[] originalList, long[] subset_s){
		//let subset_t be a copy of subset_s
		long[] subset_t = new long[subset_s.length];
		for (int i=0; i<subset_s.length; i++) {
			subset_t[i] = subset_s[i];
		}

		//generate two random indices
		int index_i = 0;
		int index_j = 0;

		while (index_i == 0 || index_j == 0 || index_i == index_j){
			index_i = 1 + (int)(Math.random()*(subset_s.length-1));
			index_j = 1 + (int)(Math.random()*(subset_s.length-1));
		}

		// System.out.print(index_i +","+ index_j+"\n");
		// System.out.print(subset_s[index_i] + "," + subset_s[index_j] + "\n");

		//if xi is in s, remove it from t. Otherwise, add xi to t.
		if (subset_s[index_i] != 0) {
			subset_t[index_i] = 0;
		} else { subset_t[index_i] = originalList[index_i]; }

		//if xj is in s, then with probability 0.5, remove it from t. 
		//If xj is not in s, then with probability 0.5, add xj to t.
		if (subset_s[index_j] != 0) {
			if (Math.random() >= 0.5) {
				subset_t[index_j] = 0;
			} else {
				if (Math.random() >= 0.5){
					subset_t[index_j] = originalList[index_j];
				}
			}
		}

		return subset_t;
	}

	//get residue for each neighbor
	private long getResidue(long[] list){
		long sum = 0;
		long target = (long)(25*Math.pow(10,12));
		for (int i=0; i<list.length; i++) {
			sum += list[i];
		}
		long residue = Math.abs(target - sum);
		return residue;
	}

	
	//get the smallest residue
	private long getSmallestResidue(long[] originalList, long[] sublist, int iteration){
		long current_r = 0;
		long neighbor_r = 0;
		long smallest_r = (long)Math.pow(10,15); //give it a big number
		long[] current = sublist;

		for (int i=0; i<iteration; i++) {
			//save the smallest residue of all current subsets
			if (getResidue(current) < smallest_r) {
				smallest_r = getResidue(current);
			}
			long[] neighbor = generateRandomNeighbor(originalList, current);

			//If the neighbor has smaller residue, then make it the current subset.
			if (getResidue(neighbor) < getResidue(current)) {
				current = neighbor;
			} else {
				//If the neighbor has larger or equal residue, then with probability e-T, make it the current subset.
				long exponentT = (getResidue(neighbor) - getResidue(current)) / (long)(Math.pow(10,11) * Math.pow(0.8,i/300));
				long probability = (long)Math.exp(-exponentT);
				//System.out.print("prob:" + probability);
				if (probability >= 0.05) {
					current = neighbor;
				}
			}
		}

		return smallest_r;
	}




	public static void main(String args[]){
		Annealing annealing = new Annealing();
		long[] arr = annealing.generateRandomList(100);
		long k_value = (long)(25*Math.pow(10,12));
		long residue = annealing.subSubSum(k_value, arr, 100);
		System.out.print("Residue: " + residue + "\n");
 //        System.out.println("Random List:");
 //        annealing.printList(arr);

 //        //test random subset
 //        System.out.println("\nRandom Subset:");
 //        long[] sub = annealing.generateRandomSubset(arr);
 //        annealing.printList(sub);

 //        long[] neighbor = annealing.generateRandomNeighbor(arr, sub);
 //        annealing.printList(neighbor);

 //        System.out.println("\nTest residue:");
 //        long residue = annealing.getResidue(sub);
 //        System.out.print(residue);

 //        System.out.println("\nTest smallest residue:");
 //        long s_residue = annealing.getSmallestResidue(arr, sub, 100);
 //        System.out.print("\nresult:" + s_residue + "\n");

 //        long residue2 = Annealing(arr, 100);
 //        System.out.print("\nresult2:" + residue2);
	}




















}

//Implement the Greedy Algorithm
//Zilin Chen
//4/23/2018


import java.lang.Math;

public class Greedy {

	//constructor
	public Greedy() {
		System.out.println("This is the Greedy Algorithm: \n");
	}

	//takes a random long array
	public static long Greedy(long[] randomArr){
		long[] subset = new long[randomArr.length];
		long[] sortedArr = sort(randomArr, 0, randomArr.length-1);
		long residue = findResidue(sortedArr);
		return residue;
	}

	//generate list of 100 random numbers from 1 to 10^12
	public static long[] generateRandomList(int num){
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
	public static void printList(long[] list){
		for (int i=0; i<list.length ;i++ ) {
			System.out.print(i + ": " + list[i] + "\n");
		}
		System.out.println();
	}

	//merge sort for long list from largest to smallest
	public static void merge(long arr[], int l, int m, int r){
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
        /* Create temp arrays */
        long L[] = new long [n1];
        long R[] = new long [n2];
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];
 
        /* Merge the temp arrays */
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2){
            if (L[i] >= R[j]){
                arr[k] = L[i];
                i++;
            }else{
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        /* Copy remaining elements of L[] if any */
        while (i < n1){
            arr[k] = L[i];
            i++;
            k++;
        }
        /* Copy remaining elements of R[] if any */
        while (j < n2){
            arr[k] = R[j];
            j++;
            k++;
        }
    }
 
   //main sort function
    public static long[] sort(long[] arr, int l, int r){
        if (l < r) {
            // Find the middle point
            int m = (l+r)/2;
            // Sort first and second halves
            sort(arr, l, m);
            sort(arr , m+1, r);
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
        return arr;
    }

    public static long findSum(long[] arr){
    	long sum = 0;
    	for (int i=0; i<arr.length; i++ ) {
    		sum += arr[i];
    	}
    	return sum;
    }
 	
	//find residue
    public static long findResidue(long[] arr){
    	long target = (long)(25*Math.pow(10,12));
    	long[] subset = new long[arr.length];
    	//repeatedly adding elements to the subset
    	for (int i=0; i<arr.length ; i++) {
    		//If the integer will not give the subset too large a sum, add it to the subset.
    		long sum = findSum(subset) + arr[i];
    		if (sum <= target) {
    			subset[i] = arr[i];
    		}
    	}

    	//System.out.println("\nSublist List:");
    	//printList(subset);

    	long residue = target - findSum(subset);
    	return residue;
    }



    //test function
	public static void main(String args[]){
        
        System.out.print(Math.random());
        System.out.println();
        
        //test random list
        Greedy greedy = new Greedy();
        //step one: generate random list
        // long[] list = greedy.generateRandomList(100);
        //greedy.printList(list);

        //test merge sort
        long[] arr = greedy.generateRandomList(100);
        System.out.println("Random List:");
        greedy.printList(arr);
        long[] sortedArr = greedy.sort(arr,0,arr.length-1);
        System.out.println("Sorted List:");
        greedy.printList(sortedArr);

        //test find sum
        System.out.println("Sum of Sorted List:");
        System.out.print(greedy.findSum(sortedArr)+"\n");

        //test finding subset
        long residue = greedy.findResidue(sortedArr);
        System.out.println("Residue:");
        System.out.print(residue + "\n");

        //test
        long residue2 = Greedy(arr);
        System.out.println("Residue2:");
        System.out.print(residue2 + "\n");
     
    }






}
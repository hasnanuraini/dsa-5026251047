
package lw04.unguided;

import java.util.*;

public class Main {

    
    static void bubbleSort(int[] parcels, int startPass, int endPass) {
        int n = parcels.length;

        for (int i = startPass; i < endPass; i++) {
            for (int j = 0; j < n - 1 - i; j++) {

                if (parcels[j] < parcels[j + 1]) {
                    int temp = parcels[j];
                    parcels[j] = parcels[j + 1];
                    parcels[j + 1] = temp;
                }
            }
        }
    }

    
    static int[] binarySearch(int[] parcels, int target) {
        int low = 0;
        int high = parcels.length - 1;
        int steps = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            steps++;

            if (parcels[mid] == target) {
                return new int[]{mid, steps};
            } else if (parcels[mid] < target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return new int[]{-1, steps};
    }

    
    static void displayArray(int[] parcels) {
        for (int i = 0; i < parcels.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }

            System.out.print(parcels[i]);
        }

        System.out.println();
    }

    
    static void processFile(String fileName) {

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream(fileName)
        );

        int n = scanner.nextInt();

        int[] parcels = new int[n];

        for (int i = 0; i < n; i++) {
            parcels[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();

        int[] targets = new int[m];

        for (int i = 0; i < m; i++) {
            targets[i] = scanner.nextInt();
        }

        scanner.close();

        
        bubbleSort(parcels, 0, 2);

        
        int[] afterTwoPasses = parcels.clone();

        
        bubbleSort(parcels, 2, n - 1);

        
        System.out.println("==== After 2 Passes ====");
        displayArray(afterTwoPasses);

        System.out.println();


        System.out.println("==== Fully Sorted ====");
        displayArray(parcels);

        System.out.println();


        System.out.println("==== Search Results ====");

        for (int i = 0; i < m; i++) {

            int[] result = binarySearch(parcels, targets[i]);

            int index = result[0];
            int steps = result[1];

            if (index != -1) {
                System.out.println(
                    targets[i] + ": FOUND at index " + index
                    + " (steps: " + steps + ")"
                );
            } else {
                System.out.println(
                    targets[i] + ": NOT FOUND"
                    + " (steps: " + steps + ")"
                );
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("========== EXAMPLE 1 ==========");
        processFile("parcels1.txt");

        System.out.println();

        System.out.println("========== EXAMPLE 2 ==========");
        processFile("parcels2.txt");
    }
}

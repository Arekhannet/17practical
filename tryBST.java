// tryBST.java
// CSC 211 Practical 17 — Binary Search Tree
// Consulted Claude (Anthropic) for guidance

import java.text.*;

public class tryBST {

    static final int REPETITIONS = 30;

    // Build a perfect BST by inserting middle element first
    // then recursively doing the same for each half
    static void populateTree(BST tree, int low, int high) {
        if (low > high) return;
        int mid = (low + high) / 2;
        tree.insert(mid);
        populateTree(tree, low,     mid - 1);
        populateTree(tree, mid + 1, high);
    }

    // Delete all even-numbered nodes from the tree
     static void removeEvens(BST tree, tNode node) {
        if (node == null) return;
        // visit left and right before deleting
        // (save children first in case node gets deleted)
        tNode left  = node.left;
        tNode right = node.right;
        removeEvens(tree, left);
        removeEvens(tree, right);
        if (node.key % 2 == 0)
            tree.delete(node.key);
    }

 
    // Small test with n=3 (keys 1..7)
   
    static void smallTest() {
        System.out.println("=== Small Test n=3 (keys 1..7) ===");
        BST t = new BST();
        populateTree(t, 1, 7);
        System.out.println("Is BST: "   + t.isBST());
        System.out.println("Size:   "   + t.size());
        System.out.println("Removing even nodes...");
        removeEvens(t, t.root);
        System.out.println("Is BST after removal: " + t.isBST());
        System.out.println("Size after removal:   " + t.size());
        System.out.println();
    }

    // -------------------------------------------------------
    // Print results table
    // -------------------------------------------------------
    static void printTable(int n, int keys,
                           double popAvg, double popStd,
                           double remAvg, double remStd) {
        DecimalFormat twoD = new DecimalFormat("0.00");
        System.out.println("\n________________________________________________");
        System.out.println("Binary Search Tree Timing Results");
        System.out.println("________________________________________________");
        System.out.printf("%-30s %-10s %-15s %-15s%n",
                "Method", "keys n", "Avg time (ms)", "Std Deviation");
        System.out.println("________________________________________________");
        System.out.printf("%-30s %-10d %-15s %-15s%n",
                "Populate tree", keys,
                twoD.format(popAvg), twoD.format(popStd));
        System.out.printf("%-30s %-10d %-15s %-15s%n",
                "Remove evens from tree", keys,
                twoD.format(remAvg), twoD.format(remStd));
        System.out.println("________________________________________________");
        System.out.println("Repetitions = " + REPETITIONS);
    }

     // Main
    public static void main(String[] args) {

        // Step 1 — small test first
        smallTest();

        // Step 2 — run with n=20, keys 1..2^n - 1
        int n    = 20;
        int keys = (1 << n) - 1; // 2^n - 1
        System.out.printf("Running with n=%d, keys=1..%,d%n%n", n, keys);

        // --- Time populating the tree ---
        double popTime = 0, popTime2 = 0;
        for (int rep = 0; rep < REPETITIONS; rep++) {
            BST tree = new BST();
            long start  = System.currentTimeMillis();
            populateTree(tree, 1, keys);
            long finish = System.currentTimeMillis();
            double time = (double)(finish - start);
            popTime  += time;
            popTime2 += (time * time);
        }
        double popAvg = popTime  / REPETITIONS;
        double popStd = Math.sqrt(popTime2 - REPETITIONS * popAvg * popAvg)
                        / (REPETITIONS - 1);

        System.out.println("Populate tree:");
        System.out.printf("  Average time = %.2fms  +/- %.2fms%n", popAvg, popStd);

        // --- Time removing evens ---
        double remTime = 0, remTime2 = 0;
        for (int rep = 0; rep < REPETITIONS; rep++) {
            // rebuild tree fresh each time
            BST tree = new BST();
            populateTree(tree, 1, keys);
            long start  = System.currentTimeMillis();
            removeEvens(tree, tree.root);
            long finish = System.currentTimeMillis();
            double time = (double)(finish - start);
            remTime  += time;
            remTime2 += (time * time);
        }
        double remAvg = remTime  / REPETITIONS;
        double remStd = Math.sqrt(remTime2 - REPETITIONS * remAvg * remAvg)
                        / (REPETITIONS - 1);

        System.out.println("Remove evens:");
        System.out.printf("  Average time = %.2fms  +/- %.2fms%n", remAvg, remStd);

        // --- Print table ---
        printTable(n, keys, popAvg, popStd, remAvg, remStd);
    }
}

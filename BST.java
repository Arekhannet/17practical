// BST.java
// Binary Search Tree class
// Consulted Claude (Anthropic) for guidance

public class BST {

    tNode root;

    // Empty tree constructor
    public BST() {
        root = null;
    }

    // Insert a key into the BST
    public void insert(int key) {
        }
        tNode curr   = root;
        tNode parent = null;
        while (curr != null) {
        }
        newNode.parent = parent;
        if (key < parent.key)
            parent.left  = newNode;
        else
            parent.right = newNode;
    }

    // Search for a key, return node or null
    public tNode search(int key) {
    }

    // Delete a key from the BST
    public void delete(int key) 
      
    }

    private void deleteNode(tNode node) {
    }

    // Replace node u with node v in the tree
    private void replaceNode(tNode u, tNode v) {
    }

    // Find minimum node in subtree rooted at node
    public tNode minNode(tNode node) {
    }

    // Check if tree satisfies BST properties
    public boolean isBST() {
        return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBSTHelper(tNode node, int min, int max) {
    }

    // Count nodes in tree
    public int size() {
    }

    private int sizeHelper(tNode node) {
    }
}

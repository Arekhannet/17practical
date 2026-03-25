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
        tNode newNode = new tNode(key);
        if (root == null) {
            root = newNode;
            return;
        }
        tNode curr   = root;
        tNode parent = null;
        while (curr != null) {
            parent = curr;
            if (key < curr.key)
                curr = curr.left;
            else if (key > curr.key)
                curr = curr.right;
            else
                return; // already in tree
        }
        newNode.parent = parent;
        if (key < parent.key)
            parent.left  = newNode;
        else
            parent.right = newNode;
    }

    // Search for a key, return node or null
    public tNode search(int key) {
        tNode curr = root;
        while (curr != null) {
            if      (key < curr.key) curr = curr.left;
            else if (key > curr.key) curr = curr.right;
            else                     return curr;
        }
        return null;
    }

    // Delete a key from the BST
    public void delete(int key) {
        tNode node = search(key);
        if (node == null) return;
        deleteNode(node);
    }

    private void deleteNode(tNode node) {
        // Case 1: no children
        if (node.left == null && node.right == null) {
            replaceNode(node, null);
        }
        // Case 2: one child
        else if (node.left == null) {
            replaceNode(node, node.right);
        }
        else if (node.right == null) {
            replaceNode(node, node.left);
        }
        // Case 3: two children — replace with inorder successor
        else {
            tNode successor = minNode(node.right);
            node.key        = successor.key;
            deleteNode(successor);
        }
    }

    // Replace node u with node v in the tree
    private void replaceNode(tNode u, tNode v) {
        if (u.parent == null)
            root = v;
        else if (u == u.parent.left)
            u.parent.left  = v;
        else
            u.parent.right = v;
        if (v != null)
            v.parent = u.parent;
    }

    // Find minimum node in subtree rooted at node
    public tNode minNode(tNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    // Check if tree satisfies BST properties
    public boolean isBST() {
        return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBSTHelper(tNode node, int min, int max) {
        if (node == null) return true;
        if (node.key <= min || node.key >= max) return false;
        return isBSTHelper(node.left,  min,      node.key) &&
               isBSTHelper(node.right, node.key, max);
    }

    // Count nodes in tree
    public int size() {
        return sizeHelper(root);
    }

    private int sizeHelper(tNode node) {
        if (node == null) return 0;
        return 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }
}

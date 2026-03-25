// tNode.java
// Node class for Binary Search Tree
// Consulted Claude (Anthropic) for guidance

public class tNode {
    int key;
    tNode left, right, parent;

    public tNode(int key) {
        this.key    = key;
        this.left   = null;
        this.right  = null;
        this.parent = null;
    }
}

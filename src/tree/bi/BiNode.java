package tree.bi;

/**
 * 二叉树
 */
public class BiNode {
    int value;
    BiNode left;
    BiNode right;

    public BiNode(int value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
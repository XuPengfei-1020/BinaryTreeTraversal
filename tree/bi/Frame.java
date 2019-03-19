package tree.bi;

/**
 * 模拟栈帧
 */
public class Frame {
    Frame(BiNode node, int index) {
        this.node = node;
        this.index = index;
    }

    /**
     * 当前栈中的 node
     */
    BiNode node;

    /**
     * 当前栈执行到的步骤编号。
     */
    int index = 0;
}
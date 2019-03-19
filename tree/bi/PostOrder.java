package tree.bi;

import java.util.Stack;

/**
 * 后续遍历二叉树。
 *
 * @author flying
 */
public class PostOrder {
    /**
     * 需要被遍历的 tree
     */
    private BiNode node;

    /**
     * Constructor
     */
    public PostOrder(BiNode node) {
        this.node = node;
    }

    /**
     * 递归方式
     */
    public void traversal() {
        traversal0(this.node);
    }

    private void traversal0(BiNode node) {
        if (node != null) {
            traversal0(node.left);
            traversal0(node.right);
            System.out.print(node + ", ");
        }
    }

    /**
     * 非递归，双栈法，将后序遍历转换为左右颠倒的先序遍历（父 -> 右 -> 左），然后将转换后的 result 倒序输出。
     */
    public void useDBStack() {
        BiNode cnode = this.node;

        if (cnode == null) {
            return;
        }

        Stack<BiNode> stack = new Stack<>();
        stack.push(cnode);
        // 结果栈，放到此栈中的数据是 ”父 -> 右 -> 左“ 的顺序
        Stack<BiNode> result = new Stack<>();

        while (!stack.isEmpty()) {
            cnode = stack.pop();
            result.push(cnode);

            if (cnode.left != null) {
                stack.push(cnode.left);
            }

            if (cnode.right != null) {
                stack.push(cnode.right);
            }
        }

        while (!result.empty()) {
            System.out.print(result.pop() + ", ");
        }
    }

    /**
     * 非递归， 记录最后一个 node。
     * 输出节点的步骤：
     * 1. 如果刚刚输出过它的右节点，则将自己输出。
     * 2. 如果刚刚输出过它的左节点，则将自己的右节点输出。
     * 3. 如果既没有输出过它的左节点，也没有输出过它的右节点，那么尝试输出这个节点的左节点。
     * 4. 将每次输出的节点，记左 lastNode，以便于上边几个步骤的比较。
     */
    public void useRememberLastNode() {
        BiNode cnode = this.node;

        if (cnode == null) {
            return;
        }

        Stack<BiNode> stack = new Stack<>();
        stack.push(cnode);
        BiNode lastNode = null;

        while (!stack.isEmpty()) {
            cnode = stack.peek();

            if (lastNode == null || lastNode != cnode.right) {
                if (lastNode != cnode.left && cnode.left != null) {
                    stack.push(cnode.left);
                    continue;
                }

                if (cnode.right != null) {
                    stack.push(cnode.right);
                    continue;
                }
            }

            System.out.print((lastNode = stack.pop()) + ", ");
        }
    }

    /**
     * frame， 模拟栈帧的方法。
     * 1. 将递归方法中分为几个隔离的步骤
     * 2. 使用 frame 来记住这些步骤，以此模拟递归中的调用过程。
     *
     * 将递归中的栈帧模拟下来，将递归方法中的处理步骤分为几步，然后用 frame 中的 index 来指向应该执行的步骤，将 frame 放到 stack 中。
     * 原理与递归调用的出入栈相同。此方法可用于任何递归调用的方法。
     */
    public void useFrame() {
        BiNode cnode = this.node;

        if (cnode == null) {
            return;
        }

        Stack<Frame> stack = new Stack<>();
        stack.push(new Frame(cnode, 0));

        while (!stack.isEmpty()) {
            Frame frame = stack.peek();
            frame.index ++;

            if (frame.node == null || frame.index == 4) {
                stack.pop();
                continue;
            }

            if (frame.index == 1) {
                stack.push(new Frame(frame.node.left, 0));
                continue;
            }

            if (frame.index == 2) {
                stack.push(new Frame(frame.node.right, 0));
                continue;
            }

            if (frame.index == 3) {
                System.out.print(frame.node + ", ");
            }
        }
    }
}
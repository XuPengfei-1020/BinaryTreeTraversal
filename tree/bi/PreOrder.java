package tree.bi;

import java.util.Stack;

/**
 * 先序遍历二叉树。
 *
 * @author flying
 */
public class PreOrder {
    /**
     * 需要被遍历的 tree
     */
    private BiNode node;

    /**
     * Constructor
     */
    public PreOrder(BiNode node) {
        this.node = node;
    }

    /**
     * 递归遍历
     */
    public void traversal() {
        traversal0(this.node);
    }

    private void traversal0(BiNode node) {
        if (node != null) {
            System.out.print(node + ", ");
            traversal0(node.left);
            traversal0(node.right);
        }
    }

    /**
     * 二叉树，先序遍历，非递归。
     * 对于任何节点，使用一下步骤
     * 1. 从栈中取出节点作为当前节点，输出当前节点
     * 2. 如果当前节点的 right 节点不为空，则将 right 子节点入栈。
     * 3. 如果当前节点的 left 节点不为空，则将 left 节点入栈。
     * 4. 重复 1,2,3 步骤，直到栈为空。
     */
    public void useStack() {
        BiNode cnode = this.node;

        if (cnode == null) {
            return;
        }

        Stack<BiNode> stack = new Stack<>();
        stack.push(cnode);

        while (!stack.empty()) {
            cnode = stack.pop();
            System.out.print(cnode + ", ");

            if (cnode.right != null) {
                stack.push(cnode.right);
            }

            if (cnode.left != null) {
                stack.push(cnode.left);
            }
        }
    }

    /**
     * frame， 模拟栈帧的方法。
     *
     * 1. 将递归方法中分为几个隔离的步骤
     * 2. 使用 frame 来记住这些步骤，以此模拟递归中的调用过程。
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

        while (!stack.empty()) {
            Frame frame = stack.peek();
            frame.index ++;

            if (frame.node == null || frame.index == 4) {
                stack.pop();
                continue;
            }

            if (frame.index == 1) {
                System.out.print(frame.node + ", ");
                continue;
            }

            if (frame.index == 2) {
                stack.push(new Frame(frame.node.left, 0));
                continue;
            }

            if (frame.index == 3) {
                stack.push(new Frame(frame.node.right, 0));
                continue;
            }
        }
    }
}
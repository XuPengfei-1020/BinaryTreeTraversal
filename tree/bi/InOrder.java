package tree.bi;

import java.util.Stack;

/**
 * 中序遍历二叉树
 */
public class InOrder {
    /**
     * 需要被遍历的 tree
     */
    private BiNode node;

    /**
     * Constructor
     */
    public InOrder(BiNode node) {
        this.node = node;
    }

    /**
     * 二叉树，中序遍历，递归
     */
    public void traversal() {
        traversal0(this.node);
    }

    public void traversal0(BiNode node) {
        if (node != null) {
            traversal0(node.left);
            System.out.print(node + ", ");
            traversal0(node.right);
        }
    }

    /**
     * 二叉树，中序遍历，非递归。
     * 1. 从栈中取出一个节点，如果刚刚没有尝试遍历右节点失败，则访问其左节点，结束。
     * 2. 第一步不满足的情况下，输出当前节点
     * 3. 尝试遍历当前节点的右节点，如果遍历失败，则将 left 置为 false。
     * 4. 重复上述步骤，直到 stask 为空
     *
     * 实现这个步骤的关键在于，访问完毕一个 node 的左子节点后，不要再继续访问这个 node 的左子节点了。如果做到呢？通过下面这个结论：
     * 如果尝试访问某一个节点的右节点，并且失败了，那么就意味这一个左子树访问完毕了。
     * 那么下次一定会访问这个左子树的父节点，这个时候就不要访问这个父节点的左子树了（避免陷入死循环）
     * 这个方法不好理解，其实是和 {@link PostOrder#useRememberLastNode()} 这个方法中的使用 lastNode 记住上一次访问节点的思想是类似的。
     * 只不过这里的 lastNode 很有可能不是这次节点的直接左子节点或者父子节点。
     */
    public void useStack() {
        BiNode cnode = this.node;

        if (cnode == null) {
            return;
        }

        Stack<BiNode> stack = new Stack<>();
        stack.push(cnode);
        // 是否应该继续遍历右子节点
        boolean left = true;

        while (!stack.isEmpty()) {
            cnode = stack.pop();

            if (left && cnode.left != null) {
                stack.push(cnode);
                stack.push(cnode.left);
                continue;
            }

            System.out.print(cnode + ", ");

            if (left = cnode.right != null) {
                stack.push(cnode.right);
            }
        }
    }

    /**
     * 模拟栈帧的方法。
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
                System.out.print(frame.node + ", ");
                continue;
            }

            if (frame.index == 3) {
                stack.push(new Frame(frame.node.right, 0));
                continue;
            }
        }
    }
}

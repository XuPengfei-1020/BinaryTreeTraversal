package tree.bi;

import java.util.Stack;

/**
 * 递归，非递归方式生成二叉树。
 */
public class BiTreeGenerator {
    /**
     * 一下两个变量是生成的树的节点值 范围。
     */
    private int value;
    private int maxValue;

    public BiTreeGenerator(int value, int maxValue) {
        if (value > maxValue)
            throw new RuntimeException("value:" + value + ", maxValue" + maxValue);

        this.value = value;
        this.maxValue = maxValue;
    }

    /**
     * 生成适合先序遍历的 tree，递归方式。
     * 按照先序遍历，输出为从 {@link #value} 到 {@link #maxValue}
     */
    public BiNode preOrder() {
        return this.preOrder0(this.value, this.maxValue);
    }

    /**
     * 递归
     */
    private BiNode preOrder0(int value, int maxValue) {
        if (value > maxValue) {
            return null;
        }

        BiNode node = new BiNode(value);
        int leftMaxValue = ((maxValue - value) / 2) + value;
        node.left = preOrder0(value + 1, leftMaxValue);
        node.right = preOrder0(leftMaxValue + 1, maxValue);
        return node;
    }

    /**
     * 生成适合先序遍历的 tree，非递归。
     */
    public BiNode preOrderNoRecursion() {
        int value = this.value;
        int maxValue = this.maxValue;

        BiNode root = new BiNode(value);
        Stack<BiNode> stack = new Stack<>();
        stack.push(root);
        Stack<Integer> maxValueStack = new Stack<>();
        maxValueStack.push(maxValue);

        while (!stack.isEmpty()) {
            BiNode node = stack.pop();
            value = node.value;
            maxValue = maxValueStack.pop();

            int leftMaxValue = ((maxValue - value) / 2) + value;

            if (value < leftMaxValue) {
                node.left = new BiNode(value + 1);
                stack.push(node.left);
                maxValueStack.push(leftMaxValue);
            }

            if (leftMaxValue < maxValue) {
                node.right = new BiNode(leftMaxValue + 1);
                stack.push(node.right);
                maxValueStack.push(maxValue);
            }
        }

        return root;
    }

    /**
     * 生成适合中序遍历的 tree，递归方式。
     * 按照中序遍历，输出为从 {@link #value} 到 {@link #maxValue}
     */
    public BiNode inOrder() {
        return inOrder0(this.value, this.maxValue);
    }

    /**
     * 递归方法
     */
    private BiNode inOrder0(int value, int maxValue) {
        if (value > maxValue) {
            return null;
        }

        int middleValue = (maxValue - value) / 2 + value;
        BiNode node = new BiNode(middleValue);
        node.left = inOrder0(value, middleValue - 1);
        node.right = inOrder0(middleValue + 1, maxValue);
        return node;
    }

    /**
     * 生成适合中序遍历的 tree，非递归。
     * 按照中序遍历，输出为从 {@link #value} 到 {@link #maxValue}
     */
    public BiNode inOrderNoRecursion() {
        int value = this.value;
        int maxValue = this.maxValue;

        int middleValue = (maxValue - value) / 2 + value;
        BiNode root = new BiNode(middleValue);
        Stack<BiNode> nodes = new Stack<>();
        nodes.push(root);
        Stack<Integer> maxValues = new Stack<>();
        maxValues.push(maxValue);
        Stack<Integer> values = new Stack<>();
        values.push(value);

        while (!nodes.isEmpty()) {
            BiNode node = nodes.pop();
            middleValue = node.value;
            maxValue = maxValues.pop();
            value = values.pop();

            if (value < middleValue) {
                node.left = new BiNode((middleValue - value) / 2 + value);
                nodes.push(node.left);
                maxValues.push(middleValue - 1);
                values.push(value);
            }

            if (middleValue < maxValue) {
                node.right = new BiNode((maxValue + 1 - middleValue) / 2 + middleValue);
                nodes.push(node.right);
                maxValues.push(maxValue);
                values.push(middleValue + 1);
            }
        }

        return root;
    }

    /**
     * 生成适合后续遍历的 tree, 递归。
     * 按照后序遍历，输出为从 {@link #value} 到 {@link #maxValue}
     */
    public BiNode postOrder() {
        return postOrder0(this.value, this.maxValue);
    }

    /**
     * 递归方法
     */
    private BiNode postOrder0(int value, int maxValue) {
        if (value > maxValue) {
            return null;
        }

        BiNode node = new BiNode(maxValue);

        if (value != maxValue) {
            maxValue = maxValue - 1;
            int middleValue = (maxValue - value) / 2 + value;
            node.left = postOrder0(value, middleValue);
            node.right = postOrder0(middleValue + 1, maxValue);
        }

        return node;
    }

    /**
     * 生成适合后续遍历的 tree, 非递归。
     * 按照后序遍历，输出为从 {@link #value} 到 {@link #maxValue}
     *
     */
    public BiNode postOrderNoRecursion() {
        int value = this.value;
        int maxValue = this.maxValue;

        if (value > maxValue) {
            return null;
        }

        BiNode node = new BiNode(maxValue);
        Stack<BiNode> nodes = new Stack<>();
        nodes.push(node);
        Stack<Integer> values = new Stack<>();
        values.push(value);

        while (!nodes.isEmpty()) {
            BiNode cnode = nodes.pop();
            maxValue = cnode.value - 1;
            value = values.pop();
            int middle = (maxValue - value) / 2 + value;

            // left from middle to value
            if (value < middle) {
                cnode.left = new BiNode(middle++);
                nodes.push(cnode.left);
                values.push(value);
            }

            // right from middle + 1 to maxValue
            if (middle <= maxValue) {
                cnode.right = new BiNode(maxValue);
                nodes.push(cnode.right);
                values.push(middle);
            }
        }

        return node;
    }
}
package tree.bi;

import java.util.Stack;

/**
 * 递归，非递归方式生成二叉树。
 */
public class BiTreeGenerator {
    /**
     * 生成适合先序遍历的 tree，递归方式。
     */
    public static BiNode preOrder(int value, int maxValue) {
        if (value > maxValue) {
            return null;
        }

        BiNode node = new BiNode(value);
        int leftMaxValue = ((maxValue - value) / 2) + value;
        node.left = preOrder(value + 1, leftMaxValue);
        node.right = preOrder(leftMaxValue + 1, maxValue);
        return node;
    }

    /**
     * 生成适合先序遍历的 tree，非递归。
     */
    public static BiNode preOrderNoRecursion(int value, int maxValue) {
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
     */
    public static BiNode inOrder(int value, int maxValue) {
        if (value > maxValue) {
            return null;
        }

        int middleValue = (maxValue - value) / 2 + value;
        BiNode node = new BiNode(middleValue);
        node.left = inOrder(value, middleValue - 1);
        node.right = inOrder(middleValue + 1, maxValue);
        return node;
    }

    /**
     * 生成适合中序遍历的 tree，非递归。
     */
    public static BiNode inOrderNoRecursion(int value, int maxValue) {
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
     */
    public static BiNode postOrder(int value, int maxValue) {
        if (value > maxValue) {
            return null;
        }

        BiNode node = new BiNode(maxValue);

        if (value != maxValue) {
            maxValue = maxValue - 1;
            int middleValue = (maxValue - value) / 2 + value;
            node.left = postOrder(value, middleValue);
            node.right = postOrder(middleValue + 1, maxValue);
        }

        return node;
    }

    /**
     * 生成适合后续遍历的 tree, 非递归。
     */
    public static BiNode postOrderNoRecursion(int value, int maxValue) {
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
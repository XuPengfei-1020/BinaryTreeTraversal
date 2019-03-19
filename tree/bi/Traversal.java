package tree.bi;

/**
 * 遍历算法集合
 */
public class Traversal {
    public static void main(String[] args) {
        // 生成从 0 到 33 的二叉树。
        int value = 0;
        int maxValue = 33;

        // 先跟遍历 递归生成 + 递归/非递归遍历
        PreOrder preOrder = new PreOrder(BiTreeGenerator.preOrder(value, maxValue));
        preOrder.traversal();
        System.out.println();
        preOrder.useStack();
        System.out.println();
        preOrder.useFrame();
        System.out.println();

        // 先跟遍历 非递归生成 + 递归/非递归遍历
        preOrder = new PreOrder(BiTreeGenerator.preOrderNoRecursion(value, maxValue));
        preOrder.traversal();
        System.out.println();
        preOrder.useStack();
        System.out.println();
        preOrder.useFrame();
        System.out.println();

        // 中序遍历 递归生成 + 递归/非递归遍历
        InOrder inOrder = new InOrder(BiTreeGenerator.inOrder(value, maxValue));
        inOrder.traversal();
        System.out.println();
        inOrder.useStack();
        System.out.println();
        inOrder.useFrame();
        System.out.println();

        // 中序遍历 非递归生成 + 递归/非递归遍历
        inOrder = new InOrder(BiTreeGenerator.inOrderNoRecursion(value, maxValue));
        inOrder.traversal();
        System.out.println();
        inOrder.useStack();
        System.out.println();
        inOrder.useFrame();
        System.out.println();

        // 后序遍历 递归生成 + 递归/非递归遍历
        PostOrder postOrder = new PostOrder(BiTreeGenerator.postOrder(value, maxValue));
        postOrder.traversal();
        System.out.println();
        postOrder.useDBStack();
        System.out.println();
        postOrder.useRememberLastNode();
        System.out.println();
        postOrder.useFrame();
        System.out.println();

        // 后序遍历 递归生成 + 递归/非递归遍历
        postOrder = new PostOrder(BiTreeGenerator.postOrderNoRecursion(value, maxValue));
        postOrder.traversal();
        System.out.println();
        postOrder.useDBStack();
        System.out.println();
        postOrder.useRememberLastNode();
        System.out.println();
        postOrder.useFrame();
        System.out.println();
    }
}
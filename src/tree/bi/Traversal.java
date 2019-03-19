package tree.bi;

/**
 * 遍历算法集合
 */
public class Traversal {
    public static void main(String[] args) {
        // 生成从 0 到 33 的二叉树。
        BiTreeGenerator generator = new BiTreeGenerator(0, 33);

        // 先跟遍历 递归生成 + 递归/非递归遍历
        PreOrder preOrder = new PreOrder(generator.preOrder());
        preOrder.traversal();
        System.out.println();
        preOrder.useStack();
        System.out.println();
        preOrder.useFrame();
        System.out.println();

        // 先跟遍历 非递归生成 + 递归/非递归遍历
        preOrder = new PreOrder(generator.preOrderNoRecursion());
        preOrder.traversal();
        System.out.println();
        preOrder.useStack();
        System.out.println();
        preOrder.useFrame();
        System.out.println();

        // 中序遍历 递归生成 + 递归/非递归遍历
        InOrder inOrder = new InOrder(generator.inOrder());
        inOrder.traversal();
        System.out.println();
        inOrder.useStack();
        System.out.println();
        inOrder.useFrame();
        System.out.println();

        // 中序遍历 非递归生成 + 递归/非递归遍历
        inOrder = new InOrder(generator.inOrderNoRecursion());
        inOrder.traversal();
        System.out.println();
        inOrder.useStack();
        System.out.println();
        inOrder.useFrame();
        System.out.println();

        // 后序遍历 递归生成 + 递归/非递归遍历
        PostOrder postOrder = new PostOrder(generator.postOrder());
        postOrder.traversal();
        System.out.println();
        postOrder.useDBStack();
        System.out.println();
        postOrder.useRememberLastNode();
        System.out.println();
        postOrder.useFrame();
        System.out.println();

        // 后序遍历 递归生成 + 递归/非递归遍历
        postOrder = new PostOrder(generator.postOrderNoRecursion());
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
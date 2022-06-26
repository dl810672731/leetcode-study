import java.util.*;

public class BinaryTree {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        pre(root, result);
        return result;
    }

    private void pre(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        pre(root.left, result);
        pre(root.right, result);
    }

    public boolean isSymmetric(TreeNode root) {
        return compare(root.left, root.right);
    }

    private boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return compare(left.left, right.right) && compare(left.right, right.left);
    }

    public int maxDepth(TreeNode root) {
        return getMaxDepth(root);
    }

    public int getMaxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getMaxDepth(root.left);
        int rightDepth = getMaxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public int minDepth(TreeNode root) {
        return getMixDepth(root);
    }

    public int getMixDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return getMixDepth(root.right) + 1;
        } else if (root.right == null) {
            return getMixDepth(root.left) + 1;
        } else {
            return Math.min(getMixDepth(root.left), getMixDepth(root.right)) + 1;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return depth(root) != -1;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        int leftDepth = depth(root.left);
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = depth(root.right);
        if (rightDepth == -1) {
            return -1;
        }
        // 左右子树高度差 > 1,返回-1表示不是二叉平衡树
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        } else {
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            // 该层节点数量 size
            int size = que.size();
            // 保存一层的数据
            List<Integer> levelTemp = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode node = que.poll();
                if (node != null) {
                    levelTemp.add(node.val);
                    if (node.left != null) {
                        que.offer(node.left);
                    }
                    if (node.right != null) {
                        que.offer(node.right);
                    }
                }
            }
            result.add(levelTemp);
        }
        return result;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        convert(root, "", result);
        return result;
    }

    public void convert(TreeNode root, String path, List<String> result) {
        if (root == null) {
            return;
        }
        StringBuilder p = new StringBuilder();
        p.append(path);
        p.append(root.val);
        if (root.left == null && root.right == null) {
            p.append("->");
            result.add(p.toString());
            return;
        }
        convert(root.left, p.toString(), result);
        convert(root.right, p.toString(), result);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return cut(root, targetSum);
    }

    public boolean cut(TreeNode root, int count) {
        if (root == null) {
            return false;
        }
        count = count - root.val;
        if (0 == count && root.left == null && root.right == null) {
            return true;
        }
        return cut(root.left, count) || cut(root.right, count);
    }


    int[] inorder;
    int[] postorder;
    int index;
    HashMap<Integer, Integer> inOrderIndex = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        for (int i = 0; i < inorder.length; i++) {
            inOrderIndex.put(inorder[i], i);
        }
        index = postorder.length - 1; // 如果是前序和中序的话，index=inorder.length -1
        return buildTreeHelper(0, postorder.length - 1);
    }

    private TreeNode buildTreeHelper(int left, int right) {     // left, right：在 inorder 中的 index
        if (left > right) {
            return null;
        }
        int rootVal = postorder[index--];
        TreeNode root = new TreeNode(rootVal);
        int i = inOrderIndex.get(rootVal);
        // 因为采用了 postIndex 每次减一的方法，所以必须先递归生成右子树再递归生成做子树！！！
        root.right = buildTreeHelper(i + 1, right);
        root.left = buildTreeHelper(left, i - 1);
        return root;
    }

    private Map<Integer, Integer> indexMap;
    int[] preorder;

    public TreeNode buildTree(int preLeft, int preRight, int inLeft, int inorder_right) {
        if (preLeft > preRight) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int rootVal = preorder[preLeft];
        // 先把根节点建立出来
        TreeNode root = new TreeNode(rootVal);

        // 对于任意一颗树而言，前序遍历的形式总是
        // [ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
        // [preLeft  [preLeft+1,x], [x+1,preRight] ]

        // 即根节点总是前序遍历中的第一个节点。而中序遍历的形式总是
        // [ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
        // [ [inLeft,pIndex-1], pIndex, [pIndex+1,inRight] ]
        // 左子树的节点数目是相同的
        // x - (preLeft+1) = pIndex-1-inLeft; 得出 x= pIndex-1-inLeft+ (preLeft+1) = pIndex-inLeft+preLeft
        // 在中序遍历中定位根节点
        int pIndex = indexMap.get(rootVal);
        int x = pIndex - inLeft + preLeft;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = buildTree(preLeft + 1, x, inLeft, pIndex - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = buildTree(x + 1, preRight, pIndex + 1, inorder_right);
        return root;
    }

    public TreeNode myBuildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        this.preorder = preorder;
        this.inorder = inorder;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTree(0, n - 1, 0, n - 1);
    }
}

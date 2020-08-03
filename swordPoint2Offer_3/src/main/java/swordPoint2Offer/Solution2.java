package swordPoint2Offer;

import java.util.*;

/**
 * @Author 李振华
 * @Date 2020/8/2 16:05
 */
public class Solution2 {

    public static ArrayList<Integer> maxWindow1(int[] arr, int size){

        ArrayList<Integer> result = new ArrayList<>();
        if (arr.length<=size){
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < arr.length; i++) {
                max = Math.max(max, arr[i]);
            }
            result.add(max);
            return result;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < arr.length; i++) {
            maxHeap.add(arr[i]);
            if (maxHeap.size()>=size){
                result.add(maxHeap.peek());
                maxHeap.remove(arr[i-size+1]);
            }
            return result;

        }
        return result;
    }




    public static ArrayList<Integer> maxWindow2(int[] arr, int size){

        ArrayList<Integer> resultData = new ArrayList<>();
        if (arr == null || arr.length < size || size <= 0) return resultData;
        LinkedList<Integer> index = new LinkedList<>();


        for (int i = 0; i < arr.length; i++) {

            if (!index.isEmpty()&&arr[i]>=arr[index.peekLast()]){
                index.pollLast();
            }
            if (!index.isEmpty()&&i-index.pollFirst()>=size){
                index.pollFirst();
            }
            index.add(i);
            if (i>=size){
                resultData.add(index.peekFirst());
            }

        }
        return resultData;
    }


     static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }


    static TreeNode result = null ;
    static Integer count = 0;

    static TreeNode KthNode(TreeNode pRoot, int k){

        if(k<=0||pRoot==null){
            return null;
        }
        midTraverse(pRoot,k);
        return result;

    }
    public static void midTraverse(TreeNode root ,int k){
        if(root.left!=null&&count<k){
            midTraverse(root.left,k);
        }
        if(++count==k){
            result = root;
            return;
        }
        if (count < k && root.right != null) {
            midTraverse(root.right, k);
        }
    }

    public static void midTraverse2(TreeNode root ,int k){
        if(root!=null){
            midTraverse(root.left,k);
            count++;
            if(count == k){
                result = root;
                return;
            }
            midTraverse(root.left,k);
        }

    }




    public static void main(String[] args) {
        //{8,6,10,5,7,9,11},3
        TreeNode treeNode8 = new TreeNode(1);
        TreeNode treeNode6 = new TreeNode(1);
//        TreeNode treeNode10 = new TreeNode(10);
//        TreeNode treeNode5 = new TreeNode(5);
//        TreeNode treeNode7= new TreeNode(7);
//        TreeNode treeNode9 = new TreeNode(9);
//        TreeNode treeNode11 = new TreeNode(11);
        treeNode8.left = treeNode6;
//        treeNode8.right = treeNode10;
//        treeNode6.left = treeNode5;
//        treeNode6.right = treeNode7;
//        treeNode10.left = treeNode9;
//        treeNode10.right = treeNode11;
//        TreeNode treeNode = KthNode(treeNode8,3);

//        System.out.println(isValidBST(treeNode8));
        Deque<TreeNode> queue = new ArrayDeque<>();
    }




    double last = -Double.MAX_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (isValidBST(root.left)) {
            if (last < root.val) {
                last = root.val;
                return isValidBST(root.right);
            }
        }
        return false;
    }









    String Serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root != null)
            queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node != null){
                queue.offer(node.left);
                queue.offer(node.right);
                sb.append(node.val + ",");
            }else{
                sb.append("#" + ",");
            }
        }
        if(sb.length() != 0)
            sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    TreeNode Deserialize(String str) {
        TreeNode head = null;
        if(str == null || str.length() == 0)
            return head;
        String[] nodes = str.split(",");
        TreeNode[] treeNodes = new TreeNode[nodes.length];
        for(int i=0; i<nodes.length; i++){
            if(!nodes[i].equals("#"))
                treeNodes[i] = new TreeNode(Integer.valueOf(nodes[i]));
        }
        for(int i=0, j=1; j<treeNodes.length; i++){
            if(treeNodes[i] != null){
                treeNodes[i].left = treeNodes[j++];
                treeNodes[i].right = treeNodes[j++];
            }
        }
        return treeNodes[0];
    }



















    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            //统计这一行有多少个节点
            int count = queue.size();
            //遍历这一行的所有节点
            for (int i = 0; i < count; i++) {
                //poll移除队列头部元素（队列在头部移除，尾部添加）
                TreeNode node = queue.poll();
                //判断是从左往右打印还是从右往左打印。
                if (leftToRight) {
                    level.add(node.val);
                } else {
                    level.add(0, node.val);
                }
                //左右子节点如果不为空会被加入到队列中
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            res.add(level);
            leftToRight = !leftToRight;
        }
        return res;
    }



    }



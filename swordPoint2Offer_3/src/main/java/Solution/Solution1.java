package Solution;

import java.sql.SQLOutput;
import java.util.*;
import java.lang.*;
public class Solution1 {

     static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }


//
//
//
//    static String Serialize(TreeNode root) {
//        StringBuilder result = new StringBuilder();
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while(!queue.isEmpty()){
//            TreeNode currNode = queue.pop();
//            if(currNode!=null){
//                result.append(String.valueOf(currNode.val));
//                result.append(",");
//                queue.addLast(currNode.left);
//                queue.addLast(currNode.right);
//            }
//            else{
//                result.append("#");
//                result.append(",");
//            }
//
//
//        }
//        result.deleteCharAt(result.length()-1);
//        return result+"";
//    }
//    static TreeNode Deserialize(String str) {
//        String[] nodes = str.split(",");
//        TreeNode head = new TreeNode(Integer.parseInt(nodes[0]));
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        queue.add(head);
//        for(int j=1;j<nodes.length;){
//            if(!nodes[i].equals("#")){
//                TreeNode node = new TreeNode(Integer.parseInt(nodes[i]));
//                if(i==0){
//                    head = node;
//                }
//                if(!nodes[j].equals("#")){
//                    node.left = new TreeNode(Integer.parseInt(nodes[j++]));
//                }else{
//                    j++;
//                }
//                if(!nodes[j].equals("#")){
//                    node.right = new TreeNode(Integer.parseInt(nodes[j++]));
//                }else{
//                    j++;
//                }
//            }
//
//        }
//
//        return head;
//    }

//    String Serialize(TreeNode root) {
//        if(root == null)
//            return "";
//        StringBuilder sb = new StringBuilder();
//        Serialize2(root, sb);
//        return sb.toString();
//    }
//
//    void Serialize2(TreeNode root, StringBuilder sb) {
//        if(root == null) {
//            sb.append("#,");
//            return;
//        }
//        sb.append(root.val);
//        sb.append(',');
//        Serialize2(root.left, sb);
//        Serialize2(root.right, sb);
//    }
//
//    int index = -1;
//
//    TreeNode Deserialize(String str) {
//        if(str.length() == 0)
//            return null;
//        String[] strs = str.split(",");
//        return Deserialize2(strs);
//    }
//
//    TreeNode Deserialize2(String[] strs) {
//        index++;
//        if(!strs[index].equals("#")) {
//            TreeNode root = new TreeNode(0);
//            root.val = Integer.parseInt(strs[index]);
//            root.left = Deserialize2(strs);
//            root.right = Deserialize2(strs);
//            return root;
//        }
//        return null;
//    }
//
//
//
//
//
//    String Serialize(TreeNode root) {
//        if(root==null){
//            return "#,";
//        }
//        StringBuilder sb = new StringBuilder();
//        doSerialize(root,sb);
//        return sb+"";
//
//
//    }
//    public static void doSerialize(TreeNode root,StringBuilder sb){
//        if(root==null){
//            sb.append("#");
//            sb.append(",");
//            return ;
//        }
//        sb.append(root.val);
//        sb.append(",");
//        doSerialize(root.left,sb);
//        doSerialize(root.right,sb);
//
//    }
//
//
//
//    private int index = 0;
//    TreeNode Deserialize(String str) {
//        if(str==null||str.equals("#,")){
//            return null;
//        }
//
//        String[] strArr = str.split(",");
//        TreeNode root = new TreeNode(0);
//        doDeserialize(strArr,root.left);
//        return root;
//
//    }
//
//
//    private void doDeserialize(String[] str,TreeNode root){
//
//        if(!str[index].equals("#")){
//            root.val = Integer.parseInt(str[index]);
//            index++;
//            doDeserialize(str,root.left);
//            doDeserialize(str,root.right);
//        }else{
//            index++;
//            return ;
//        }
//
//
//
//    }



    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {

        LinkedList<TreeNode> queue = new LinkedList();
        queue.offer(pRoot);
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(pRoot==null){
            return result;
        }
        Boolean flag = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> temList = new ArrayList();
            for(int i=0;i<size;i++){
                TreeNode currNode = null;
                currNode = queue.pollFirst();
                if(flag){
                    temList.add(currNode.val);
                }else{
                    temList.add(0,currNode.val);
                }
                if(currNode.left!=null){
                    queue.offer(currNode.left);
                }
                if(currNode.right!=null){
                    queue.offer(currNode.right);
                }
            }
            result.add(temList);
            flag = !flag;
        }
        return result;


    }





    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(9);
        TreeNode node8 = new TreeNode(11);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        String string = "8,6,5,11,#,#,#,5,#,#,10,7,#,#,9,#,#,";
        Solution1 solution1 = new Solution1();
        ArrayList<ArrayList<Integer> > result = solution1.Print(node1);
        System.out.println(result);

    }











}

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import sun.applet.AppletClassLoader;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.*;

/**
 * @Author 李振华
 * @Date 2020/7/5 10:47
 */
public class Test1 {

//    static class ListNode {
//        int val;
//        ListNode next = null;
//
//        ListNode(int val) {
//            this.val = val;
//        }
//    }
//    public ListNode deleteDuplication(ListNode pHead)
//    {
//        ListNode result;
//        ListNode temp=pHead;
//        ListNode index=new ListNode(1);
//        index.next=pHead;
//        result=index;
//        while(temp!=null){
//            if(temp.next!=null&&temp.next.val==temp.val){
//                while(temp.next!=null&&temp.next.val==temp.val){
//                    temp=temp.next;
//                }
//                temp=temp.next;
//                index.next=temp;
//            }
//            else{
//                index=index.next;
//                temp=temp.next;
//            }
//        }
//        return result.next;
//    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if(pHead==null){
            return null;
        }
        ListNode fast = pHead;
        ListNode slow = pHead;
        ListNode tem = null;
        if((tem = isCircle(pHead))!=null){
            int i = countCircle(tem,tem);

            do{
                fast = fast.next.next;
                slow = slow.next;
            }while(!fast.equals(slow));
            return fast;
        }else{
            return null;
        }



    }

    public ListNode isCircle(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        do{
            slow = slow.next;
            if (slow!=null){
                fast = fast.next.next;
            }
        }while(fast!=null||!fast.equals(slow));
        return fast ;
    }

    public Integer countCircle(ListNode fast,ListNode slow){
        Integer count = 0;
        do{
            fast = fast.next.next;
            slow = slow.next;
            count++;
        }while(!fast.equals(slow));
        return count-1;
    }
//        public static void main(String[] args) {
            //1,2,3,3,4,4,5
//            ListNode node1 = new ListNode(1);
//            ListNode node2 = new ListNode(2);
//            ListNode node3 = new ListNode(3);
//            ListNode node4 = new ListNode(3);
//            ListNode node5 = new ListNode(4);
//            ListNode node6 = new ListNode(4);
//            ListNode node7 = new ListNode(5);
//            node1.next = node2;
//            node2.next = node3;
//            node3.next = node4;
//            node4.next = node5;
//            node5.next = node6;
//            node6.next = node7;
 //           Test1 test1 = new Test1();
            //ListNode r = test1.deleteDuplication(node1);
//            while (r!=null){
//                System.out.println(r.value);
//                r = r.next;
//            }
//            test1.EntryNodeOfLoop(node1);
//
//            String s = "abcab";
//            int x = s.indexOf("a");
//            int y = s.lastIndexOf("a");
//            System.out.println(x==y);
//     }
//
//
//
//    public int[] multiply(int[] A) {
//        if (A == null || A.length == 0) {
//            return new int[1];
//        }
//        int[] B = new int[A.length];
//        int tmp1 = 1;
//        int[] tmp2 = new int[A.length];
//        tmp2[A.length - 1] = 1;
//        for (int i = 1; i < A.length; i++) {
//            tmp2[A.length - i - 1] = tmp2[A.length - i] * A[A.length - i];
//        }
//        for (int i = 0; i < A.length; i++) {
//            if(i!=0){
//                tmp1 *=A[i-1];
//            }
//            B[i] = tmp1 * tmp2[i];
//        }
//        return B;
//    }



//    public static boolean duplicate(int numbers[], int length, int[] duplication) {
//        int temp;
//        if(length<=1)
//            return false;
//        for(int i=0;i<length;i++) {
//            while(numbers[i]!=i) {
//                if(numbers[numbers[i]]!=numbers[i]) {
//                    temp=numbers[numbers[i]];
//                    numbers[numbers[i]]=numbers[i];
//                    numbers[i]=temp;
//                }
//                else {
//                    duplication[0]=numbers[i];
//                    return true;
//                }
//            }
//        }
//        return false;
//    }


//    public static void main(String[] args) {
//
////        int[] num = new int[]{2,1,4,4,3,0};
////        int[] d = new int[1];
////
////        duplicate(num,6,d);
//    }

    public int Add(int num1,int num2) {
        int num3;
        int num4;
        do{
            num3=num1^num2;
            num4=(num1&num2)<<1;
            num1=num3;
            num2=num4;
        }while(num4!=0);
        return (num1|num2);
    }

    public int Sum_Solution(int n) {
        int sum = n;
        boolean ans = (n>0)&&((sum+=Sum_Solution(n-1))>0);
        return sum;
    }



    class ListNode1 {
        int val;
        ListNode1 next = null;
        ListNode1(int val) {
            this.val = val;
        }
    }

    public int LastRemaining_Solution(int n, int m) {

        if (n <= 0 || m <= 0) {
            return -1;
        }

        ListNode1 head = new ListNode1(0);
        ListNode1 node = head;
        for (int i = 1; i < n; i++) {
            node.next = new ListNode1(i);
            node = node.next;
        }
        node.next = head;

        int k = 0;
        while (node.next != node) {
            if (++k == m) {
                node.next = node.next.next;
                k = 0;
            } else {
                node = node.next;
            }
        }

        return node.val;
    }






    public boolean isContinuous(int [] numbers) {
        if(numbers == null)
            return false;
        Arrays.sort(numbers);
        int zero = 0, i = 0;
        for(; i < numbers.length && numbers[i] == 0; i++){
            zero ++;  //统计0的个数
        }
        for(; i < numbers.length - 1 && zero >= 0; i++){
            //有对子，则返回false
            if(numbers[i] == numbers[i+1])
            {
                return false;
            }
            //可以继续匹配
            if(numbers[i] + 1 + zero >= numbers[i+1]){
                zero -= numbers[i+1] - numbers[i] - 1;
            }
            else {
                return false;
            }
        }
            return true;

    }




    public String LeftRotateString(String str,int n) {
        if(str.length() == 0){
            return str;
        }
        StringBuffer buffer = new StringBuffer(str);
        StringBuffer buffer1 = new StringBuffer(str);
        StringBuffer buffer2 = new StringBuffer();
        buffer.delete(0,n);
        buffer1.delete(n,str.length());
        buffer2.append(buffer.toString()).append(buffer1.toString());
        return buffer2.toString();



    }














    public static ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> returnData = new ArrayList();
        int left = 0;
        int right = array.length-1;
        if(right==0){
            return returnData;
        }
        while(left<right){

            if((array[left]+array[right])>sum){
                right--;
                continue;
            }
            if((array[left]+array[right])<sum){
                left++;
                continue;
            }else{
                returnData.add(array[left]);
                returnData.add(array[right]);
                break;
            }
        }
        return returnData;

    }

//    public static void main(String[] args) {
//        int[] data = new int[]{1,2,4,7,11,16};
//        int sum = 10;
//        FindNumbersWithSum(data,sum);
//
//
//
//
//
//    }






    public boolean Find(int [][] array,int target) {
        boolean found = false;
        int lie = array[0].length;
        int hang = array.length;
        int column = lie -1;
        int row =0;
        while(row<hang &&column>=0){
            int value = array[row][column];
            if(target>value){
                row++;
            }else if(value>target){
                column--;
            }else{
                found = true;
                break;
            }

        }
        return found;
    }





    public String replaceSpace(StringBuffer str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ' '){
                count++;
            }
        }
        int oldLength = str.length();
        str.setLength(str.length()+2*count);
        int end = str.length()-1;
        for (int i = oldLength-1; i >=0 ; i--) {
            if(str.charAt(i)==' '){
                str.setCharAt(end--,'0');
                str.setCharAt(end--,'2');
                str.setCharAt(end--,'%');
            }else{
                str.setCharAt(end--,str.charAt(i));
            }
        }

        return str.toString();

    }









    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList();
        LinkedList<Integer> stack = new LinkedList();
        while(listNode!=null){
            stack.offer(listNode.val);
            listNode = listNode.next;
        }
        while(!stack.isEmpty()){
            result.add(stack.pollLast());
        }
        return result;
    }



  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

//    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
//
//        int leftPre = 0;
//        int rightPre = pre.length-1;
//        int leftIn = 0;
//        int rightIn = pre.length-1;
//        return doreConstructBinaryTree(pre,leftPre,rightPre,in,leftIn,rightIn);
//
//
//    }
//
//    public TreeNode doreConstructBinaryTree(int[] pre,int leftPre,int rightPre,int[] in,int leftIn,int rightIn ){
//        TreeNode result = new TreeNode(pre[leftIn]);
//        int flag = 0;
//        if (rightIn==leftIn){
//            return result;
//        }else{
//            for ( flag = leftIn; flag < rightIn; flag++) {
//                if(in[flag]==pre[leftIn]){
//                    break;
//                }
//            }
//            result.left = doreConstructBinaryTree(pre,leftPre+1,leftPre+(flag-leftIn),in,leftIn,flag-1);
//            result.right = doreConstructBinaryTree(pre,leftPre+(flag-leftIn)+1,rightPre,in,flag+1,rightIn);
//        }
//        return result;
//    }



//    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
//        TreeNode root=reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
//        return root;
//    }
//    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
//    private TreeNode reConstructBinaryTree(int [] pre,int startPre,int endPre,int [] in,int startIn,int endIn) {
//
//        if(startPre>endPre||startIn>endIn)
//            return null;
//        TreeNode root=new TreeNode(pre[startPre]);
//
//        for(int i=startIn;i<endIn;i++)
//            if(in[i]==pre[startPre]){
//                root.left=reConstructBinaryTree(pre,startPre+1,startPre+i-startIn,in,startIn,i-1);
//                root.right=reConstructBinaryTree(pre,i-startIn+startPre+1,endPre,in,i+1,endIn);
//            }
//
//        return root;
//    }
//
//
//

//
//
//    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
//
//        int leftPre = 0;
//        int rightPre = pre.length-1;
//        int leftIn = 0;
//        int rightIn = in.length-1;
//        return doreConstructBinaryTree(pre,leftPre,rightPre,in,leftIn,rightIn);
//
//
//    }
//
//    public TreeNode doreConstructBinaryTree(int[] pre,int leftPre,int rightPre,int[] in,int leftIn,int rightIn ){
//        if(leftPre>rightPre||leftIn>rightIn)
//            return null;
//        TreeNode result = new TreeNode(pre[leftIn]);
//        int flag = 0;
//
//        for ( flag = leftIn; flag <= rightIn; flag++) {
//            if(in[flag]==pre[leftIn]){
//                result.left = doreConstructBinaryTree(pre,leftPre+1,leftPre+(flag-leftIn),in,leftIn,flag-1);
//                result.right = doreConstructBinaryTree(pre,leftPre+(flag-leftIn)+1,rightPre,in,flag+1,rightIn);
//            }
//        }
//        return result;
//    }
//



    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode root=reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }
    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    private TreeNode reConstructBinaryTree(int [] pre,int startPre,int endPre,int [] in,int startIn,int endIn) {

        if(startPre>endPre||startIn>endIn)
            return null;
        TreeNode root=new TreeNode(pre[startPre]);

        for(int i=startIn;i<=endIn;i++) {
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
            }
        }
        return root;
    }












    public int minNumberInRotateArray(int [] array) {
        if(array == null || array.length == 0) return 0;
        int start = 0;
        int end = array.length - 1;
        int mid = (start + end) / 2;
        if(array[end] == array[start] || array[mid] == array[end] || array[mid] == array[start]){
            return findMin(array);
        }
        if(array[mid] > array[start] && array[mid] < array[end]){
            return array[0];
        }
        while(true){
            mid = (start + end) / 2;
            if(array[mid] > array[start]){
                start = mid;
            }else if(array[mid] < array[end]){
                end = mid;
            }
            if((start+1) == end) break;
        }
        return array[end];
    }

    public int findMin(int[] array){
        int result = Integer.MAX_VALUE;
        for(int i = 0; i <= array.length - 1; i++){
            result = Math.min(result, array[i]);
        }
        return result;
    }







    public static int NumberOf1(int n) {
        int count = 0;
        int flag = 1;
        for(int i=0;i<32;i++){
            if((n&flag)!=0){
                count++;
            }
            flag=flag<<1;
        }
        return count;
    }


    public static void main(String[] args) {
        NumberOf1(10);
    }

    public void reOrderArray(int [] array) {
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i]%2 == 1){
                int tem = array[i];
                int kk = i;
                while (k<kk){
                    array[kk] = array[kk-1];
                    kk--;
                }
                array[k] = tem;
            }
        }

    }




    public ListNode FindKthToTail(ListNode head,int k) {

       if (k<0||head==null){
           throw new IllegalArgumentException("非法参数异常");
       }
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i <k ; i++) {
            fast = fast.next;
            if (fast==null){
                throw new IllegalArgumentException("非法参数异常");
            }
        }
        while(fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }






/*思路：参考剑指offer
1、首先设置标志位result = false，因为一旦匹配成功result就设为true，
剩下的代码不会执行，如果匹配不成功，默认返回false
2、递归思想，如果根节点相同则递归调用DoesTree1HaveTree2（），
如果根节点不相同，则判断tree1的左子树和tree2是否相同，
再判断右子树和tree2是否相同
3、注意null的条件，HasSubTree中，如果两棵树都不为空才进行判断，
DoesTree1HasTree2中，如果Tree2为空，则说明第二棵树遍历完了，即匹配成功，
tree1为空有两种情况（1）如果tree1为空&&tree2不为空说明不匹配，
（2）如果tree1为空，tree2为空，说明匹配。

*/


    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        boolean result = false;
        if(root1 != null && root2 != null){
            if(root1.val == root2.val){
                result = DoesTree1HaveTree2(root1,root2);
            }
            if(!result){result = HasSubtree(root1.left, root2);}
            if(!result){result = HasSubtree(root1.right, root2);}
        }
        return result;
    }
    public boolean DoesTree1HaveTree2(TreeNode root1,TreeNode root2){
        if(root1 == null && root2 != null) return false;
        if(root2 == null) return true;
        if(root1.val != root2.val) return false;
        return DoesTree1HaveTree2(root1.left, root2.left) && DoesTree1HaveTree2(root1.right, root2.right);
    }













    Stack<Integer> data = new Stack();
    Stack<Integer> min = new Stack();
    public void push(int node) {
        data.push(node);
        if (!min.isEmpty()){
            if (min.peek()>node){
                min.push(node);
            }else{
                min.push(min.peek());
            }
        }else{
            min.push(node);
        }
    }

    public void pop() {
        data.pop();
        min.pop();
    }

    public int top() {
       return data.peek();
    }

    public int min() {
        return min.peek();
    }





    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        for(int i = 0; i < pushA.length; i++){
            stack.push(pushA[i]);
            while(!stack.isEmpty() && popA[idx] == stack.peek()){
                stack.pop();
                idx ++;
            }
        }
        return stack.isEmpty();
    }





    public boolean VerifySquenceOfBST(int [] sequence) {

        if (sequence.length<2){
            return true;
        }
        return doVerifySquenceOfBST(sequence,0,sequence.length-1);
    }

    public boolean doVerifySquenceOfBST(int [] sequence,int start,int end){
        if ((end-start)<=1){
            return true;
        }
        int i = start;
        int root = sequence[end];
        while (sequence[i]<root){
            i++;
        }
        int j = i;
        for (; j < end ; j++) {
            if (sequence[j]<root){
                return false;
            }
        }
        return doVerifySquenceOfBST(sequence,start,i-1)&&doVerifySquenceOfBST(sequence,i,end-1);
    }




    private ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
    private Stack<Integer> path = new Stack<Integer>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root == null){
            return paths;
        }
        path.push(root.val);
        target = target -root.val;
        if(target == 0 && root.left == null && root.right == null){
            paths.add(new ArrayList<Integer>(path));
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        path.pop();
        return paths;
    }






}
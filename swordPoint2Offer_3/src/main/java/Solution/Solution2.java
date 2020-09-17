package Solution;

import algorithm_practice.other.NthPrime;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;

import javax.print.DocFlavor;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:LZH
 * @Date:2020/8/28 9:42
 * @Description
 */
public class Solution2 extends LinkedHashMap{
//    private int MAX_CACHE_SIZE;
//    LinkedHashMap<Integer,Integer> map = null;
//    public int[] LRU (int[][] operators, int k) {
//        // write code here
//        MAX_CACHE_SIZE = k;
//        map = new LinkedHashMap((int) (k/0.75),0.75f,true);
//        ArrayList<Integer> data = new ArrayList<Integer>();
//        for (int[] entry:operators) {
//            int op = entry[0];
//            if (op==1){
//                map.put(entry[1],entry[2]);
//            }
//            if (op==2){
//                data.add(map.get(entry[1]));
//            }
//        }
//       int[] reData = new int[data.size()];
//        for (int i = 0; i < data.size(); i++) {
//            if (data.get(i)==null){
//                reData[i] = -1;
//            }else{
//                reData[i] = data.get(i);
//            }
//        }
//        return reData;
//    }
//
//    @Override
//    protected boolean removeEldestEntry(Map.Entry eldest) {
//        return map.size()>MAX_CACHE_SIZE;
//    }


//    public static void main(String[] args) {
//        int[][] ints = new int[][]{{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};
//        int[] a = LRU(ints,3);
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(a[i]);
//        }
//    }

    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public static int[] LRU(int[][] operators, int k) {
        // write code here
        ArrayList<Integer> list = new ArrayList<Integer>();
        LRUCache cache = new LRUCache(k);
        for(int[] op : operators){
            if(op[0]==1){
                cache.put(op[1],op[2]);
            }else{
                int val = cache.get(op[1]);
                list.add(val);
            }
        }
        int[] ans = new int[list.size()];
        for(int i=0;i<list.size();i++){
            ans[i] = list.get(i);
        }
        return ans;
    }
}

 class LRUCache extends LinkedHashMap<Integer,Integer>{
    int capacity;

    public LRUCache(int capacity){
        super(capacity,0.75f,true);
        this.capacity = capacity;
    }

    public int get(int key){
        return super.getOrDefault(key,-1);
    }

    public void put(int key, int val){
        super.put(key,val);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest){
        return size() > capacity;
    }










     public class ListNode {
         int val;
         ListNode next = null;

         ListNode(int val) {
             this.val = val;
         }
     }
     public ListNode ReverseList(ListNode head) {
         if (head==null){
             return head;
         }
        ListNode pre = head;
         ListNode cur = head.next;
         ListNode next = cur;
         pre.next = null;
         while (next!=null){
             next = cur.next;
             cur.next = pre;
             pre = cur;
             cur = next;
         }
         return pre;
     }






     public ListNode mergeTwoLists (ListNode l1, ListNode l2) {
         // write code here
         if(l1==null&&l2==null){
             return null;
         }
         ListNode temp=new ListNode(0);
         ListNode head=temp;
         ListNode temp1=l1;
         ListNode temp2=l2;
         while(true){
             if(temp2==null){
                 temp.next=temp1;
                 break;
             }else if (temp1==null){
                 temp.next=temp2;
                 break;
             }else if (temp1.val<=temp2.val){
                 temp.next=temp1;
                 temp1=temp1.next;
                 temp=temp.next;
             }else {
                 temp.next=temp2;
                 temp2=temp2.next;
                 temp=temp.next;
             }
         }
         return head.next;
     }









     private void swap(int[] a, int i, int j){
         int tmp = a[i];
         a[i] = a[j];
         a[j] = tmp;
     }

     private int partition(int[] a, int s, int e){
         int flag = s;
         while (s<e){
             if (a[s]<a[e]){
                 swap(a,s++,flag++);
             }else{
                 s++;
             }
         }
         swap(a,flag,e);
         return flag;
     }

     public int findKth(int[] a, int n, int K) {
         // write code here
         int k = n-K;
         int s = 0;
         int e = n-1;
         while(s<e){
             int i = partition(a, s, e);
             if(i==k){
                 break;
             }else if(i<k){
                 s = i+1;
             }else{
                 e = i-1;
             }
         }
         return a[k];
     }



     public ListNode detectCycle(ListNode head) {
         if(head == null) {
             return head;
         }
         ListNode slow = head;
         ListNode fast = head;
         ListNode meet = null;
         while(fast != null && fast.next != null) {
             slow = slow.next;
             fast = fast.next.next;
             if(fast == slow) {
                 meet = fast;
                 break;
             }
         }
         if(meet == null) {
             return null;
         }
         slow = head;
         while(slow != fast) {
             slow = slow.next;
             fast = fast.next;
         }
         return slow;
     }


     public ListNode removeNthFromEnd (ListNode head, int n) {
         ListNode dummy = new ListNode(0);
         dummy.next = head;
         ListNode first = dummy;
         ListNode second = dummy;
         // Advances first pointer so that the gap between first and second is n nodes apart
         for (int i = 1; i <= n + 1; i++) {
             first = first.next;
         }
         // Move first to the end, maintaining the gap
         while (first != null) {
             first = first.next;
             second = second.next;
         }
         second.next = second.next.next;
         return dummy.next;
     }



     public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
         int len1 = queryLength(pHead1);
         int len2 = queryLength(pHead2);
         if (len1>len2){
             int dif = len1-len2;
             while (dif>0){
                 pHead1 = pHead1.next;
                 dif--;
             }
         }
         if (len1<len2){
             int dif = len1-len2;
             while (dif>0){
                 pHead2 = pHead2.next;
                 dif--;
             }
         }
         while (pHead1!=null&&pHead2!=null){
            if (pHead1==pHead2){
                return pHead1;
            }
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
         }

        return null;

     }


    public int queryLength(ListNode pHead){
        int l = 0;
        while (pHead!=null){
            l++;
            pHead = pHead.next;
        }

        return l;

    }














     public ListNode mergeKLists(ArrayList<ListNode> lists) {
         if (lists == null || lists.size()==0)
             return null;

         PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.size(), new Comparator<ListNode>() {
             @Override
             public int compare(ListNode o1, ListNode o2) {
                 if (o1.val < o2.val)
                     return -1;
                 else if (o1.val == o2.val)
                     return 0;
                 else
                     return 1;
             }
         });

         ListNode dummy = new ListNode(0);
         ListNode tail = dummy;

         for (ListNode node: lists) {
             if (node != null) {
                 queue.add(node);
             }
         }

         while (!queue.isEmpty()) {
             tail.next = queue.poll();
             tail = tail.next;
             if (tail.next!=null) {
                 queue.add(tail.next);
             }
         }
         return dummy.next;
     }







     /**
      *
      * @param prices int整型一维数组
      * @return int整型
      */
     public int maxProfit (int[] prices) {
         if(null == prices || prices.length == 0) {
             return 0;
         }

         int profit = 0, buy = prices[0];
         for(int i = 1; i < prices.length; i++) {
             profit = Math.max(profit, prices[i] - buy);
             buy = Math.min(buy, prices[i]);
         }
         return profit;
     }
     /**
      * min edit cost
      * @param str1 string字符串 the string
      * @param str2 string字符串 the string
      * @param ic int整型 insert cost
      * @param dc int整型 delete cost
      * @param rc int整型 replace cost
      * @return int整型
      */
     public int minEditCost (String str1, String str2, int ic, int dc, int rc) {
         int m = str1.length();
         int n = str2.length();
         int[][] dp = new int[m + 1][n + 1];    // 行是str1，列是str2，题目要求把str1编辑成str2
         for (int i = 1; i <= m; i++) dp[i][0] = dp[i - 1][0] + dc;    // str1 位置 i 字符 变成 str2 空字符 —— 需要delete
         for (int j = 1; j <= n; j++) dp[0][j] = dp[0][j - 1] + ic;    // str1 空字符 变成 str2 位置 i 字符 —— 需要insert
         for (int i = 1; i <= m; i++) {
             for (int j = 1; j <= n; j++) {
                 if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                     // 字符相同，无需花费cost
                     dp[i][j] = dp[i - 1][j - 1];
                 } else {
                     // 在insert，delete 和 replace中找到 cost 最小的一个
                     dp[i][j] = Math.min(dp[i - 1][j - 1] + rc, Math.min(dp[i - 1][j] + dc, dp[i][j - 1] + ic));
                 }
             }
         }
         return dp[m][n];
     }




     /**
      *
      * @param matrix int整型二维数组 the matrix
      * @return int整型
      */
     public int minPathSum (int[][] matrix) {
         // write code here
         int m = matrix.length, n = matrix[0].length;
         if (m == 0 || n == 0) return 0;

         for (int i = 1; i < m; i++) matrix[i][0] += matrix[i-1][0];
         for (int i = 1; i < n; i++) matrix[0][i] += matrix[0][i-1];

         for (int i = 1; i < m; i++) {
             for (int j = 1; j < n; j++) {
                 matrix[i][j] += Math.min(matrix[i-1][j], matrix[i][j-1]);
             }
         }
         return matrix[m-1][n-1];
     }





     /**
      * 最大正方形
      * @param matrix char字符型二维数组
      * @return int整型
      */
     public int solve(char[][] matrix) {
         // write code here

         // write code here
         int hen = matrix.length;
         int su = matrix[0].length;
         int max = 1;
         for (int i = 1; i < hen; i++) {
             for (int j = 1; j < su; j++) {
                 if (matrix[i][j] != '0' && matrix[i - 1][j - 1] != '0' && matrix[i - 1][j] != '0' && matrix[i][j - 1] != '0') {
                     int temp = Math.min(matrix[i - 1][j - 1], Math.min(matrix[i][j - 1], matrix[i - 1][j])) - '0';
                     matrix[i][j] = (char) (temp + 1 + '0');
                 } else matrix[i][j] = matrix[i][j];
                 max = Math.max(matrix[i][j] - '0', max);
             }
         }
         return max * max;

     }






     /**
      *
      * @param s string字符串
      * @return int整型
      */
     public int longestValidParentheses (String s) {
         // write code here
         int length = s.length();
         if(length==0 || length==1) return 0;
         int[] dp = new int[length+1];
         int maxVal = 0;
         for(int i=1;i<length;i++){
             if(s.charAt(i)==')'){
                 if(s.charAt(i-1)=='('){
                     dp[i] = i >= 2 ? dp[i-2] + 2 : 2;
                 }else if(i - dp[i-1] > 0 && s.charAt(i-dp[i-1]-1)=='('){
                     dp[i] = dp[i-1] + (i-dp[i-1]>=2?dp[i-dp[i-1]-2]+2 : 2);
                 }
                 maxVal = Math.max(maxVal,dp[i]);
             }
         }
         return maxVal;
     }


     public static void main(String[] args) {
         String s = "This order was placed for QT3000! OK?";
         String p = "(\\d+)(.*)";
         Pattern pattern = Pattern.compile(p);

         Matcher m = pattern.matcher(s);

         if (m.find()){
             System.out.println("Found value: " + m.group(0) );
             System.out.println("Found value: " + m.group(1) );
             System.out.println("Found value: " + m.group(2) );
             
         }else{
             System.out.println("NO MATCH");
         }


     }

}
package code.offer.src.Chap6;

import common.util.SysOut;

import java.util.*;

/**
 * 题目1：滑动窗口的最大值。
 * 给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。
 * 例如，如果输入数组{2, 3, 4, 2, 6, 2, 5}以及滑动窗口的大小3，那么一共存在6个滑动窗口
 * 他们的最大值分别为{4,4,6,6,6,5}
 */
public class MaxInWindow {
    /**
     * 方法1：使用优先队列
     */
    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (num == null || num.length < size || size <= 0) {
            return list;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        int j = 0;
        for (int i = 0; i < num.length; i++) {
            maxHeap.offer(num[i]);
            if (maxHeap.size() >= size) {
                list.add(maxHeap.peek());
                maxHeap.remove(num[j++]);
            }
        }
        return list;
    }
    /**
     * 方法2: 使用双端队列，存放下标
     */
    public static ArrayList<Integer> maxInWindow2(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (num == null || num.length < size || size <= 0) {
            return list;
        }
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            while (!deque.isEmpty() && num[i] >= num[deque.peekLast()]) {
                deque.pollLast();
            }
            if (!deque.isEmpty() && i - deque.peekFirst() >= size) {
                deque.pollFirst();
            }
            deque.offerLast(i);
            if (i +1 >= size) {
                list.add(num[deque.peekFirst()]);
            }
        }
        return list;
    }


    public static void main(String[] args) {
        // expected {7};
        int[] data1 = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        ArrayList<Integer> res = maxInWindow2(data1, 4);
        res.forEach(e->System.out.println(e));
//        // expected {3, 3, 5, 5, 6, 7};
//        int[] data2 = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
//        SysOut.printArray(maxInWindows(data2, 3));
//        // expected {7, 9, 11, 13, 15};
//        int[] data3 = new int[]{1, 3, 5, 7, 9, 11, 13, 15};
//        SysOut.printArray(maxInWindows(data3, 4));
//        // expected  {16, 14, 12};
//        int[] data5 = new int[]{16, 14, 12, 10, 8, 6, 4};
//        SysOut.printArray(maxInWindows(data5, 5));
//        // expected  {10, 14, 12, 11};
//        int[] data6 = new int[]{10, 14, 12, 11};
//        SysOut.printArray(maxInWindows(data6, 1));
//        // expected  {14};
//        int[] data7 = new int[]{10, 14, 12, 11};
       // SysOut.printArray(maxInWindows(data7, 4));
    }
}
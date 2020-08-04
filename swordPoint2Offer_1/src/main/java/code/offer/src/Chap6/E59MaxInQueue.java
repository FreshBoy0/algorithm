package code.offer.src.Chap6;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 定义一个队列，实现max方法得到队列中的最大值。
 * 要求入列、出列以及邱最大值的方法时间复杂度都是O(1)
 */
public class E59MaxInQueue {

//队列的最大值

    /*问题一：滑动窗口的最大值*/
    public static Queue<Integer> getMaxInWindows(int[] queue, int windowsSize) {
        if (queue == null || queue.length < windowsSize || windowsSize <= 0) {
            return null;
        }
        Queue<Integer> results = new LinkedList<>();
        Deque<Integer> finder = new LinkedList<>();
        for (int i = 0; i < windowsSize; i++) {
            while (!finder.isEmpty() && queue[finder.peekLast()] <= queue[i]) {
                finder.removeLast();
            }
            while (!finder.isEmpty() && finder.peekFirst() <= i - windowsSize) {
                finder.removeFirst();
            }
            finder.addLast(i);
        }
        for (int i = windowsSize; i < queue.length; i++) {
            results.add(queue[finder.peekFirst()]);
            while (!finder.isEmpty() && queue[finder.peekLast()] <= queue[i]) {
                finder.removeLast();
            }
            while (!finder.isEmpty() && finder.peekFirst() <= i - windowsSize) {
                finder.removeFirst();
            }
            finder.addLast(i);
        }
        results.add(queue[finder.peekFirst()]);
        return results;
    }

    /*问题二：定义一个队列并且实现add、remove和max函数，时间复杂度都为O(1)*/
    public class SpecialQueue<T extends Comparable<T>> {
        private class QueueData {
            int index;
            T data;

            QueueData(int index, T data){
                this.index = index;
                this.data = data;
            }
        }

        private Deque<QueueData> finder;
        private Queue<QueueData> container;
        private int index;

        public SpecialQueue() {
            finder = new LinkedList<>();
            container = new LinkedList<>();
            index = 0;
        }

        public void add(T data){
            while(!finder.isEmpty() && finder.peekLast().data.compareTo(data) <= 0) {
                finder.removeLast();
            }
            QueueData queueData = new QueueData(index, data);
            index++;
            finder.addLast(queueData);
            container.add(queueData);
        }

        public T remove(){
            if (container.isEmpty()) {
                throw new IllegalArgumentException("Empty Queue!");
            }
            if (container.peek().index == finder.peekFirst().index) {
                finder.removeFirst();
            }
            return container.remove().data;
        }

        public T max(){
            if (container.isEmpty()) {
                throw new IllegalArgumentException("Empty Queue!");
            }
            return finder.peekFirst().data;
        }
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        /**
         * 问题一
         */
        int[] queue = {2, 3, 4, 2, 6, 2, 5, 1};
        Queue<Integer> results = E59MaxInQueue.getMaxInWindows(queue, 3);
        for (int result : results) {
            System.out.print(result + "\t");
        }
        System.out.println();
        /**
         * 问题二
         */
        SpecialQueue<Integer> specialQueue = new E59MaxInQueue().new SpecialQueue<>();
        specialQueue.add(2);
        specialQueue.add(3);
        specialQueue.add(4);
        specialQueue.add(2);
        specialQueue.add(6);
        specialQueue.add(2);
        specialQueue.add(2);
        specialQueue.add(5);
        // 5
        System.out.println(specialQueue.max());
        specialQueue.remove();
        specialQueue.remove();
        specialQueue.remove();
        specialQueue.remove();
        specialQueue.remove();
        System.out.println(specialQueue.max());
        // 3
        System.out.println(specialQueue.max());
    }

}

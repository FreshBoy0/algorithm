package code.offer.src.Chap6;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author 李振华
 * 最大值队列
 * @Date 2020/7/1 20:49
 */
/**
 * 定义一个队列，实现max方法得到队列中的最大值。
 * 要求入列、出列以及邱最大值的方法时间复杂度都是O(1)
 */
public class MaxQueue2 {

    Queue<Integer> queue;
    Deque<Integer> deque;

    public MaxQueue2(){
        queue = new LinkedList<Integer>();
        deque = new LinkedList<Integer>();
    }

    public void push_back(int e){
        queue.offer(e);

        int num = 1;                                                                   //不出的时候，也要把自身进去
        while(!deque.isEmpty() && deque.peekLast()<e){
            deque.pollLast();
            num++;                                                                      //出的时候，统计一下
        }

        while(num!=0){
            deque.offerLast(e);
            num--;
        }
    }

    public int pop_front(){
        deque.pollFirst();
        return queue.poll();
    }

    public int max(){
        return deque.peekFirst();
    }

    public static void main(String[] args) {
        MaxQueue2  maxQueue = new MaxQueue2();
        maxQueue.push_back(2);
        maxQueue.push_back(3);
        maxQueue.push_back(4);
        maxQueue.push_back(2);
        maxQueue.push_back(6);
        maxQueue.push_back(2);
        maxQueue.push_back(5);
        maxQueue.push_back(1);

        System.out.println(maxQueue.max());
        maxQueue.pop_front();
        maxQueue.pop_front();
        maxQueue.pop_front();
        maxQueue.pop_front();
        System.out.println(maxQueue.max());
    }





}

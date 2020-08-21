package BFS;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:LZH
 * @Date:2020/8/21 15:31
 * @Description
 */
public class Prime {

    // prime算法实现
    public static int prime(int[][] graph, ArrayList<Integer> points, int current) {
        String path = "";
        ArrayList<Integer> selectPoints = new ArrayList<Integer>(); // 选中的点集合
        int totalWeights = 0;  // 权重总和
        selectPoints.add(current); // 添加初始开始节点
        points.remove(current); // 从未选择的节点集合中删除被选中的节点
        path = "|" + current + "|";
        System.out.println("当前路径：" + path);
        System.out.println("当前已选中节点: " + selectPoints.toString());
        System.out.println("当前剩余节点: " + points.toString());
        System.out.println("当前总权重: " + totalWeights);
        // 循环找出最小权重的边 直至所有的点都被选中
        while(points.size() > 0) {
            // 遍历选中的点相连的边中权重最小的边记录下来
            int mincost = 0;  // 最小权重
            int mincostPoint = selectPoints.get(0); // 最小权重边对应的点
            List<Integer> linePoints = new ArrayList<Integer>();  // 记录所有与已选中点相连的点
            for(int i = 0 ; i < selectPoints.size(); i++) {
                for(int j = 0; j < points.size(); j++) {
                    int startPoint = selectPoints.get(i); // 起点
                    int endPoint = points.get(j); // 终点
                    // 两点是相连的
                    if(graph[startPoint][endPoint] != 10000 && graph[startPoint][endPoint] < 100) {
                        // 将和已选中点连通的点加入连通集合
                        linePoints.add(points.get(j));
                        if(linePoints.size() == 1) {
                            // 将第一个连通的边的权重赋值为最小权重
                            mincost = graph[startPoint][linePoints.get(0)];
                            // 最小权重相连的点
                            mincostPoint = endPoint;
                        }else {
                            // 与当前的最小权重比较
                            if(graph[startPoint][endPoint] < mincost) {
                                // 最小权重相连的点
                                mincost = graph[startPoint][endPoint];
                                mincostPoint = endPoint;
                            }
                        }
                    }
                }
            }
            if(mincost != 0) { // 证明是找到了相连的点
                selectPoints.add(mincostPoint);   // 添加点
                points = (ArrayList<Integer>) removeFormPoints(points, mincostPoint);
                // 权重增加
                totalWeights += mincost;
                path += " ---" + mincost + "--- |" + mincostPoint + "|";
                System.out.println("当前路径：" + path);
            }else {
                System.out.println("不连通");
                return 0;
            }
            // 打印当前所选中的最小权重边对应的点
            System.out.println("当前已选中节点: " + selectPoints.toString());
            System.out.println("当前剩余节点: " + points.toString());
            System.out.println("当前总权重: " + totalWeights);
        }
        System.out.println("总路径：" + path);
        // 返回总权重
        return totalWeights;
    }
    // 删除剩余节点中的相连通的最小权重的节点的方法（就是将该节点加入最小生成树中）
    public static List<Integer> removeFormPoints(ArrayList<Integer> points, int mincostPoint) {
        List<Integer> tempPoints = new ArrayList<Integer>();
        for(int i = 0; i < points.size(); i++) {
            if(points.get(i) != mincostPoint) {
                tempPoints.add(points.get(i));
            }
        }
        return tempPoints;
    }















}

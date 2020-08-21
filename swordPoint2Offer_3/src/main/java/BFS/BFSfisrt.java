package BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author:LZH
 * @Date:2020/8/21 13:55
 * @Description
 */
public class BFSfisrt {

    private Queue q;
    private Queue<Integer> visited;

    public BFSfisrt() {
        // TODO Auto-generated constructor stub
        q = new LinkedList();
        visited = new LinkedList<>();
    }

    private int getIndex(char v, char[] str) {
        for (int i = 0; i < str.length; i++) {
            if (v == str[i])
                return i;
        }
        return -1;
    }


    public void bfs(int[][] matrix, char v, char[] str) {
        int i = getIndex(v, str);
        if (i == -1) return;
        q.add(i);
        visited.add(i);
        System.out.print(str[i] + " ");
        while (!q.isEmpty()) {
            int u = (int) q.remove();

            for (int j = 0; j < str.length; j++) {
                if (matrix[u][j] == 1 && (!visited.contains(j))) {
                    q.add(j);
                    visited.add(j);
                    System.out.print(str[j] + " ");
                }
            }
        }
    }

    public static void main (String[]args){
        // TODO Auto-generated method stub
        int[][] matrix = {{0, 1, 1, 0, 0, 0, 0, 0},
                          {1, 0, 0, 1, 1, 0, 0, 0},
                          {1, 0, 0, 0, 0, 1, 1, 0},
                          {0, 1, 0, 0, 0, 0, 0, 1},
                          {0, 1, 0, 0, 0, 0, 0, 1},
                          {0, 0, 1, 0, 0, 0, 1, 0},
                          {0, 0, 1, 0, 0, 1, 0, 0},
                          {0, 0, 1, 1, 0, 0, 0, 0}};
        char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        BFSfisrt bfs = new BFSfisrt();
        bfs.bfs(matrix, 'A', str);

    }





}
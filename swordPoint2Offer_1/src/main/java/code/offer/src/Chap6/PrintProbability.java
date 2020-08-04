package code.offer.src.Chap6;

/**
 * 题目：把 n 个骰子扔在地上，所有骰子朝上一面的点数之和为 s。输入 n，打印出 s 的所有可能的值出现的概率。
 *
 * 【法1】：递归
 *  第一次：将n个骰子分成两堆，第一堆只有1个骰子，第二堆有n-1个骰子
 *      这1个骰子有6种情况，和剩下的n-1个骰子点数相加
 *  第二次：继续把剩下的n-1个骰子分成两堆，一堆1个，一堆n-2个...
 *      同样，这一个骰子还是要分成6种情况与剩下的n-2个骰子的点数相加
 *      同时，这一次还要把这颗骰子的点数与上一颗骰子的点数相加，之后再递归调用下一次分割。
 *  直到：此次递归调用还剩1颗骰子时，
 *      我们用一个probabilities[]数组保存所有点数之和的情况，出现的次数
 *      在对应的probabilities【当前点数之和sum】++;
 *
 *
 *  【法2】：上述递归调用显然有很多计算是重复的，极大的影响性能
 *  下面我们用动态规划-->二维数组求解：
 *  probabilities[2][i]：
 *      第1行代表：第一堆的这个骰子 当前累加和的情况
 *      第2行代表：另一堆的那个骰子（6种情况） 分别给上一堆的每种情况再加上（1、2、3、4、5、6）
 *
 * Created by nibnait on 2016/10/2.
 */
public class PrintProbability {
    private int sideNum = 6;
    /**
     * 递归
     */
    public void printProbability(int n) {
        if (n < 1) return;
        int maxVal = n * sideNum;
        int[] probabilities = new int[maxVal - n + 1];
        getProbabilities(n, n, 0, probabilities);
        int total = (int) Math.pow(sideNum, n);
        for (int i = n; i <= maxVal; i++) {
            System.out.println("s=" + i + ": " + probabilities[i - n] + "/" + total);
        }
    }

    private void getProbabilities(int n, int cur, int sum, int[] p) {
        if (cur == 0) p[sum - n]++;
        else for (int i = 1; i <= sideNum; i++) {
            getProbabilities(n, cur - 1, sum + i, p);
        }
    }

    /**
     * 动态规划
     */
    public void printProbabilityDP(int n) {
        if (n < 1) return;
        int maxVal = n * sideNum;
        int[][] f = new int[n + 1][maxVal + 1];
        // 初始化f(1, i) 1 <= i <= 6, 只有一个骰子，点数和为i的情况只有一种
        for (int i = 1; i <= sideNum; i++) {
            f[1][i] = 1;
        }
        // 逐个增加骰子个数
        for (int k = 2; k <= n; k++) {
            // k个骰子可能的点数和是k~6k
            for (int sum = k; sum <= k * sideNum; sum++) {
                for (int i = 1; (sum-i)>0 && i <= sideNum; i++) {
                    f[k][sum] += f[k - 1][sum - i];
                }
            }
        }

        int total = (int) Math.pow(sideNum, n);
        for (int sum = n; sum <= maxVal; sum++) {
            System.out.println("s=" + sum + ": " + f[n][sum] + "/" + total);
        }
    }

    /**
     * 更省空间的动态规划
     */
    public void printProbabilityBetterDP(int n) {
        if (n < 1) return;
        int maxVal = n * sideNum;
        int[][] f = new int[2][maxVal + 1];
        int flag = 0;
        // 初始化f(1, i) 1 <= i <= 6, 只有一个骰子，点数和为i的情况只有一种
        for (int i = 1; i <= sideNum; i++) {
            f[flag][i] = 1;
        }
        // 逐个增加骰子个数
        for (int k = 2; k <= n; k++) {
            // k个骰子可能的点数和是k~6k
            for (int sum = k; sum <= k * sideNum; sum++) {
                // 求f(k -1, s-1)~f(k-1, s-6)的情况和
                int s = 0;
                for (int i = 1; sum > i && i <= sideNum; i++) {
                    s += f[flag][sum - i];
                }
                // 重新给f(k, s)赋值
                f[1 - flag][sum] = s;
            }
            flag = 1 - flag;
        }

        int total = (int) Math.pow(sideNum, n);
        for (int sum = n; sum <= maxVal; sum++) {
            // f(k, s)也就是f[1-flag][sum], 但之后flag = 1 -flag,所以调用f[flag]才能得到f(k, s)
            System.out.println("s=" + sum + ": " + f[flag][sum] + "/" + total);
        }
    }

    public static void main(String[] args) {
        PrintProbability p = new PrintProbability();
        p.printProbability(3);
        System.out.println();
        p.printProbabilityBetterDP(3);
    }
}

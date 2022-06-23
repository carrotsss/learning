package alg.monotoneStack;

import java.util.Stack;

/**
 * 每日温度
 * 请根据每日气温列表，temperatures，计算每一天需要等几天才会有更高的温度，如果气温在这之后都不会升高，请在该位置用0代替。
 * 输入：temperatures = 【73,74,75,71,69,72,76,73]
 * 输出：【1,1,4,2,1,1，0，0】
 */
public class P1_739_DailyTemperature {
    /**
     * 单调栈，栈内顺序要么从大到小，要么从小到大，本题从小到大
     * 入栈元素要和当前栈内手元素进行比较
     * 若大于栈首则与元素下标做差
     * 若大于等于则放入
     * @param temperature
     * @return
     */
    public static int[] dailyTemperature(int[] temperature) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperature.length];
        for (int i = 0; i < temperature.length; i++) {
            /**
             * 取出下标进行元素值比较
             */
            while (!stack.isEmpty() && temperature[i] > temperature[stack.peek()]) {
                int preIndex = stack.pop();//当前栈顶元素是之前比当前温度低的第一个温度的下标，删除这个下标，并把当前温度下标放入
                res[preIndex] = i - preIndex;//i 是大于 preindex的最近的那个温度的下标，求差并放入答案数组
            }
            /**
             * 注意，放入的是元素位置
             */
            stack.push(i);//0,1,2,3,4...
        }
        return res;
    }

    public static int[] dailyTemperatureSelf(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int preIndex = stack.pop();
                res[preIndex] = i - preIndex;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        for (int num : dailyTemperatureSelf(new int[]{73, 74, 75, 71, 69, 72, 76, 73})) {
            System.out.print(num + " ");
        };
    }
}

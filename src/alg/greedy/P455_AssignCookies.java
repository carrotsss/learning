package alg.greedy;

import java.util.Arrays;

/**
 * 贪心解题步骤:
 * 1、将问题分解为若干子问题
 * 2、找出适合的贪心策略
 * 3、求解每一个子问题的最优解
 * 4、将局部最优解堆叠成全局最优解
 *
 * 分发饼干
 * 对每个孩子i,都有一个胃口值g[i],这个能让孩子们满足胃口的饼干的最小尺寸；
 * 并且每块饼干j都有一个尺寸s[j],如果s[j] > g[i],我们可以将这个饼干j分配给孩子i，这个孩子会得到满足。
 * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 * 输入：g=[1,2,3]  s=[1,2]
 * 输出：2
 */
public class P455_AssignCookies {
    /**
     * 思路一：优先考虑饼干，小饼干先喂饱小胃口
     * @param stomacs 胃口数组
     * @param cookies 饼干数组
     * @return
     */
    public static int findCountChildrenOfficial(int[] stomacs, int[] cookies) {
        Arrays.sort(stomacs);//从小到大排列
        Arrays.sort(cookies);
        int j = 0;
        int count = 0;
        //1、将问题拆分子问题，把问题拆分成每个满足小胃口stomac[j]孩子的小饼干cookies[i]作为一个子问题，
        //2、找到贪心策略并求解最优解，可以满足最小胃口stomac的小饼干cookie为一组数据，
        //3、对子问题进行堆叠得到最优解，count++
        for (int i = 0; i < cookies.length && j < stomacs.length; i++) {
            //找到第一个满足j孩子胃口的饼干
            if (cookies[i] >= stomacs[j]) {
                //满足之后看下一个孩子
                j++;
                count++;
            }
        }
        return count;
    }

    public static int findCountChildrenSelf(int[] stomacs, int[] cookies) {
        Arrays.sort(stomacs);
        Arrays.sort(cookies);
        int j = 0;
        for (int i = 0; i < cookies.length && j < stomacs.length; i++) {
            if (cookies[i] >= stomacs[j]) {
                j++;
            }
        }
        return j;
    }

    /**
     * 思路二：优先考虑胃口，大饼干先喂饱大胃口
     * @param stomacs 胃口数组
     * @param cookies 饼干数组
     * @return
     */
    public static int findCountChildrenOfficial2(int[] stomacs, int[] cookies) {
        Arrays.sort(stomacs);
        Arrays.sort(cookies);
        int start = cookies.length - 1;
        int count = 0;
        for (int i = stomacs.length - 1; i >= 0; i++) {
            if (start >= 0 && stomacs[i] <= cookies[start]) {
                start--;
                count++;
            }
        }
        return count;
    }

    public static int findCountChildrenSelf2(int[] stomacs, int[] cookies) {
        Arrays.sort(stomacs);
        Arrays.sort(cookies);
        int j = stomacs.length - 1;
        int count = 0;
        for (int i = cookies.length - 1; i >= 0 && j >= 0; i--) {
            if (cookies[i] > stomacs[j]) {
                count++;
                j--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] cookies = new int[]{1, 2, 3,2};
        int[] stomacs = new int[]{1, 2};
        System.out.println(findCountChildrenSelf2(stomacs, cookies));
    }
}

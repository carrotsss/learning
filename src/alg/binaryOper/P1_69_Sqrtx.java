package alg.binaryOper;

/**
 * 给你一个非负整数x，计算返回x的算术平方根
 * 由于返回类型是整数，结果只保留整数部分，小数部分将被舍去
 */
public class P1_69_Sqrtx {
    public static int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int l = 1, h = x;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            int sqrt = x / mid;
            if (sqrt == mid) {
                return mid;
            } else if (mid > sqrt) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return h;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(20));;
    }
}

package alg.bit;

/**
 * 汉明距离
 * 两个整数之间的汉明距离指的是两个数字对应二进制位不同的位置的数目
 * 输入：x=1，y=4
 * 输出：2
 * 说明：1（0 0 0 1)  4(0100）
 */
public class P1_461_HammingDistance {
    /**
     * 对两个数进行异或操作，位级表示不同的那一位为1，统计有多少个1即可
     * @param x
     * @param y
     * @return
     */
    public static int hamingDistaceOfficial1(int x, int y) {
        int z = x ^ y;
        int count = 0;
        while (z != 0) {
            System.out.println(Integer.toBinaryString(z));
            //0100 & 0001
            if ((z & 1) == 1) {
                count++;
            }
            z = z >> 1;
        }
        return count;
    }

    //使用z &（z -1）去除z位级最低的那一位
    public static int hamingDistanceOfficial2(int x, int y) {
        int z = x + y;
        int count = 0;
        while (z != 0) {
            System.out.println(Integer.toBinaryString(z));
            z &= (z - 1);
            count++;
        }
        return count;
    }

    public static int hamingDistanceOfficial3(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    public static void main(String[] args) {
        System.out.println(hamingDistaceOfficial1(1, 4));
        System.out.println("------------------------");
        System.out.println(hamingDistanceOfficial2(1, 4));
        System.out.println("------------------------");
        System.out.println(hamingDistanceOfficial3(1, 4));
    }
}

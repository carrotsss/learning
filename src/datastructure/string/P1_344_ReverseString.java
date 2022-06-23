package datastructure.string;

/**
 * 反转字符串
 * 编写一个方法其作用是将输入的字符串翻转过来。输入字符串以字符数组char[]形式给出
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组，使用O(1)的额外空间解决这一问题
 */
public class P1_344_ReverseString {
    public static char[] reverseString(char[] chars) {
        int l = 0;
        int r = chars.length - 1;
        while (l < r) {
            char tmp = chars[l];
            chars[l] = chars[r];
            chars[r] = tmp;
//            chars[l] ^= chars[r];//构造a^b，把值赋给chars【l】
//            chars[r] ^= chars[l];
//            chars[l] ^= chars[r];
            l++;
            r--;
        }
        return chars;
    }
}

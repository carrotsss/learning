package alg.binaryOper;

/**
 * 大于给定元素的最小元素
 * 给定一个有序的字符数组letters和一个字符target，要求找出letters中大于target的最小字符，如果找不到就返回第1个字符
 */
public class P2_744_FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int l = 0, h = n - 1;
        while (l < h) {
            int mid = l + (h - 1) / 2;
            if (letters[mid] <= target) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return l < n ? letters[l] : letters[0];
    }
}

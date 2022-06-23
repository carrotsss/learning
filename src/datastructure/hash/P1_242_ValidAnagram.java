package datastructure.hash;

/**
 * 有效的字母异位词
 * 给定两个字符串s和t，编写一个函数来判断t是否是s的字母异位词
 * 注：若s和t中每个字符出现的次数都相同，则称s和t互为字母异位词
 * 输入：s = "anagram" t = "nagram"
 * 输出：true
 */
public class P1_242_ValidAnagram {
    public static boolean isAnagramOfficial(String s, String t) {
        //hash表以字母ascll码差值作为index
        int[] record = new int[26];
        for (char c : s.toCharArray()) {
            record[c - 'a'] += 1;
        }
        for (char c : t.toCharArray()) {
            record[c - 'a'] -= 1;
        }
        for (int i : record) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "angram";
        String t = "nagram";
        if (isAnagramOfficial(s, t)) {
            System.out.println("是异位词!");;
        }
    }
}

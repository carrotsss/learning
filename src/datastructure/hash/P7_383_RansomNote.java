package datastructure.hash;

/**
 * 赎金信
 * 为了不在赎金信里暴露字迹，从杂志上搜索各个需要的字母，组成单词来表达意思
 * 给你一个赎金信字符串和一个杂志字符串，判断ransomNote能不能由magazine里面的字符组成
 * 如果可以构成返回true，否则返回false
 * 输入：ransomNote= "a"  magazin = "b"
 * 输出：false
 * ransomNote = "aa" magazine = "ab"
 * false
 * ransomNote = "aa" magazine = "aab"
 * true
 */
public class P7_383_RansomNote {

    /**
     * 暴力解法
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public static boolean canConstructViolentOfficial(String ransomNote, String magazine) {
        char[] magazineChars = magazine.toCharArray();
        char[] ransomNoteChars = ransomNote.toCharArray();
        for (int i = 0; i < magazine.length(); i++) {
            for (int j = 0; j < ransomNote.length(); j++) {
                // 在ransomNote中找到和magazine相同的字符
                if (magazineChars[i] == ransomNoteChars[j]) {
                    for (int n = j; n < ransomNoteChars.length; j++) {
                        ransomNoteChars[n] = ransomNoteChars[n + 1];
                    }
                    break;
                }
            }
        }
        if (ransomNoteChars.length == 0) {
            return true;
        }
        return false;
    }

    /**
     * hash解法
     * 利用
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public static boolean canConstructHashOfficial(String ransomNote, String magazine) {
        //记录杂志字符串出现的次数，用作hash表作用，利用26个字母做散列
        int[] arr = new int[26];
        int temp;
        for (int i = 0; i < magazine.length(); i++) {
            temp = magazine.charAt(i) - 'a';
            arr[temp]++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            temp = ransomNote.charAt(i) - 'a';
            //对于赎金信中没一个字符都在数组中查找
            //找到相应位减1，否则找不到返回false
            if (arr[temp] > 0) {
                arr[temp]--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String ransomNote = "anagram";
        String magazine = "naaramg";
        if (canConstructHashOfficial(ransomNote, magazine)) {
            System.out.println("是的");
        }else{
            System.out.println("不是的");
        }
    }
}

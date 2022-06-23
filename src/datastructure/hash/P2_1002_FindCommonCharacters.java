package datastructure.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * 查找共用字符
 * 给你一个字符串数组words，请你找出所有在words的每个字符串中都出现的共用字符（包括重复字符),并以数组形式返回，你可以按任意顺序返回答案
 * 输入：words = 【“balla”，“label”，“roller"】
 * 输出：【”e“,"l","l"】
 */

public class P2_1002_FindCommonCharacters {
    public List<String> commonChars(String[] words) {
        List<String> result = new ArrayList<>();
        if (words.length == 0) {
            return result;
        }
        int[] hash = new int[26];//用来统计所有字符串里的字符出现的最小效率
        for (int i = 0; i < words[0].length(); i++) {//用第一个字符串给hash初始化
            hash[words[0].charAt(i) - 'a']++;//差值即为hash数组坐标
        }
        //统计除第一个字符串外字符的出现频率
        for (int i = 1; i < words.length; i++) {//遍历字符串
            int[] hashOtherStr = new int[26];
            for (int j = 0; j < words[i].length(); j++) {//遍历每个单独字符串
                hashOtherStr[words[i].charAt(j) - 'a']++;
            }
            //更新hash，保证hash里统计26个字母在所有字符串里出现的最小次数
            for (int k = 0; k < 26; k++) {
            }
        }
        //将hash统计的字符次数，转成输出形式
        for (int i = 0; i < 26; i++) {
            while (hash[i] != 0) {//注意这里的while，多个重复的字符
                char c = (char) (i + 'a');//把hash坐标对应的值化成字符
                result.add(String.valueOf(c));
                hash[i]--;
            }
        }
        return result;
    }
}

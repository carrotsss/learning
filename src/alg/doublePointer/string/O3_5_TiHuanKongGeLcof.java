package alg.doublePointer.string;

/**
 * 实现一个函数，把字符串s中的每一个空格替换成"%20"
 */
public class O3_5_TiHuanKongGeLcof {
    /**
     * 暴力解法
     * @param string
     * @return
     */
    //私用一个新对象，赋值str，复制的过程对其判断，失控而
    public String replaceSpaceOfficial(String string) {
        if (string == null) {
            return string;
        }
        StringBuilder stringBuilder = new StringBuilder();
        //使用sb逐个赋值str，碰到空格则替换，否则直接复制
        for (int i = 0; i < string.length(); i++) {
            //string.charAt(i）为char类型，为了比较需要将其转为和“”相同的字符串类型
            if (" ".equals(String.valueOf(string.charAt(i)))) {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(string.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 双指针法
     * @param string
     * @return
     */
    public String replaceSpaceOfficial2(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ') {
                stringBuilder.append("  ");
            }
        }
        //若是没有空格直接返回
        if (string.length() == 0) {
            return string;
        }
        //有空格的情况，定义两个指针
        int left = string.length() - 1;//左指针指向原始字符串最后一个位置
        string += stringBuilder.toString();
        int right = string.length() - 1;//右指针指向扩展字符串最后一个位置
        char[] chars = string.toCharArray();
        while (left >= 0) {
            if (chars[left] == ' ') {
                chars[right--] = '0';
                chars[right--] = '2';
                chars[right] = '%';
            }else{
                chars[right] = chars[left];
            }
            left--;
            right--;
        }
        return new String(chars);
    }
}

package company.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by carrots on 2022/2/28.
 */
public class VlanRemove {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String vlan = scanner.nextLine();
        String unless = scanner.next();
        String[] vlans = vlan.split(",");
        List<String> list = new ArrayList<>();
        for (String string : vlans) {
            if (string.contains("-")) {
                String[] ss = string.split("-");
                Integer i = Integer.valueOf(ss[0]);
                Integer j = Integer.valueOf(ss[1]);
                for (int m = i; i <= j; i++) {
                    list.add(i.toString());
                }
            } else {
                list.add(string);
            }
        }
        try {
            list.remove(unless);
        } catch (Exception e) {

        }
        int start = 0, end = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(start) + 1 == list.get(end + 1)) {
                end++;
                continue;
            }else{
                result.append(list.get(start));
                if (end > start + 1 && end < list.size()) {
                    result.append("-");
                    result.append(list.get(end));
                }
                if (list.get(end) != list.get(list.size() -1)) {
                    result.append(",");
                }
                start++;
            }
        }
        System.out.println(result);
    }
}

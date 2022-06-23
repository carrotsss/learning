package company.cmcc.chapter3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 使用java程序实现Shell中的如下功能:
 * cat src/main/resources/log.log |grep "cmcc"|sort|uniq -c |sort -n
 *
 * 期望结果
 *    5 cmcc2
 *    6 cmcc1
 *   11 cmcc
 */
public class SearchWord {
    public void process(){
        File file = new File("src/company/cmcc/chapter3/log.log");
        // todo 补充完善方法
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<>();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                lines.add(str);
            }
            lines = lines.stream().filter(s -> s.contains("cmcc")).collect(Collectors.toList());
            lines = lines.stream().sorted().collect(Collectors.toList());
            Map<String, Long> integerMap =
                    lines.stream().sorted().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            List<Long> res = integerMap.values().stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
            res.forEach(System.out::println);
        }catch (Exception e){
            throw new RuntimeException("executed failed !!!");
        }
    }
    public static void main(String[] args) {
        new SearchWord().process();
    }
}
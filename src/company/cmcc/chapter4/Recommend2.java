package company.cmcc.chapter4;

import java.util.*;

/**
 * Created by carrots on 2022/1/26.
 */
public class Recommend2 {
    public static void main(String[] args) {
        int K = 44;
        List<Integer> inputs = Arrays.asList(19,23,41,5,40,36);
        int opt = 0; // optimal solution so far

        Set<Integer> sums = new HashSet();
        sums.add(opt);
        // loop over all input values
        for (Integer input : inputs) {
            Set newSums = new HashSet<>();
            // loop over all sums so far
            for (Integer sum : sums) {
                int newSum = sum + input;
                // ignore too big sums
                if (newSum <= K) {
                    newSums.add(newSum);
                    // update optimum
                    if (newSum > opt) {
                            opt = newSum;
                    }
                }
            }
            sums.addAll(newSums);
        }
        System.out.println(opt);
    }

}

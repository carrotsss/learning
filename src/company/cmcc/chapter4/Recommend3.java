//package com.cmcc.chapter4;
//
///**
// * Created by carrots on 2022/1/26.
// */
//public class SubList {undefined
//
//    public int size;
//
//    public List subList;
//
//    public SubList() {undefined
//
//        this(0, new ArrayList<>());
//
//    }
//
//    public SubList(int size, List subList) {undefined
//
//        this.size = size;
//
//        this.subList = subList;
//
//    }
//
//}
//
//初始化现在变为：
//
//        SubList opt = new SubList();
//
//        Set sums = new HashSet<>();
//
//        sums.add(opt);
//
//        sums需求的内部循环也需要一些小的调整：
//
//        for (Integer input : inputs) {undefined
//
//        Set newSums = new HashSet<>();
//
//// loop over all sums so far
//
//        for (SubList sum : sums) {undefined
//
//        List newSubList = new ArrayList<>(sum.subList);
//
//        newSubList.add(input);
//
//        SubList newSum = new SubList(sum.size + input, newSubList);
//
//// ignore too big sums
//
//        if (newSum.size <= K) {undefined
//
//        newSums.add(newSum);
//
//// update optimum
//
//        if (newSum.size > opt) {undefined
//
//        opt = newSum;
//
//        }
//
//        }
//
//        }
//
//        sums.addAll(newSums);
//
//        }

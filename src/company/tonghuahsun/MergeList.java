package company.tonghuahsun;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carrots on 2022/2/23.
 */
public class MergeList {
    public List merge(List list1, List list2) {
        List list3 = new ArrayList();

        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
//                if () {
//                }
            }
        }
        return null;
    }

    public static List BubbleSort(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                int a = list.get(i);
                int b = list.get(j);
                if (a <= b) {
                    list.set(i, b);
                    list.set(j, a);
                }
            }
        }
        return list;
    }

    public static int[] mergeLikeSort(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int[] res = new int[len1+len2];
        //双指针方案
        int l1 = 0; //arr1 指针
        int l2 = 0; //arr2 指针

        for(int i = 0; i < len1+len2; i++) {
            if(l1 < len1 && l2 < len2) { //两个指针都没到头
                if(arr1[l1] < arr2[l2]) {
                    res[i] = arr1[l1];
                    l1++;
                }else {
                    res[i] = arr2[l2];
                    l2++;
                }
            }else if(l1 < len1) { //若l2到头了，l1还没到头
                res[i] = arr1[l1];
                l1++;
            }else if(l2 < len2) { //若l1到头了，l2还没到头
                res[i] = arr2[l2];
                l2++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayList<Integer> lsit1 = new ArrayList<Integer>();
        lsit1.add(1);
        lsit1.add(5);
        lsit1.add(3);
        BubbleSort(lsit1);
        for (Integer integer : lsit1) {
            System.out.print(integer + " ");
        }
        List list2 = new ArrayList();
        list2.add(4);
        list2.add(3);

    }

}

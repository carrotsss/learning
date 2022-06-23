package company.tonghuahsun;
import java.util.ArrayList;
/**
 * Created by carrots on 2022/2/28.
 */

public class Largest5 {
    //方法1：全部排序
    public void quickSort(int[] A,int start,int end){
        if(end > start){
            int k = LomutoPartition(A,start,end);
            quickSort(A,start,k-1);
            quickSort(A,k+1,end);
        }
    }
    //返回数值result，满足：     左边部分< A[result] <=右边部分
    public int LomutoPartition(int[] A,int start,int end){
        if(start >= end)
            return start;
        int begin = A[start];
        int result = start;
        for(int i = start + 1;i <= end;i++){
            if(A[i] < begin){
                result++;
                swap(A,i,result);
            }
        }
        swap(A,start,result);
        return result;
    }
    //交换数组m位置和n位置上的值
    public void swap(int[] arrayA,int m,int n){
        int temp = arrayA[m];
        arrayA[m] = arrayA[n];
        arrayA[n] = temp;
    }
    //输出数组前k个元素
    public void printArrayK(int[] array,int k){
        for(int i = 0;i < k;i++){
            System.out.print(array[i]+" ");
        }
    }

    //方法2：部分排序
    public void getArrayMinK(int[] A,int k){
        if(k > A.length)
            return;
        while(true){
            int max = getMaxArrayK(A,k);  //当前数组前k个元素中的最大值
            int count = 0;
            for(int i = k;i < A.length;i++){
                if(A[max] > A[i])
                    swap(A,max,i);
                else
                    count++;
            }
            if(count == A.length-k)
                break;
        }
        System.out.println("\n"+"使用方法2进行部分排序后的结果：");
        for(int i = 0;i < A.length;i++)
            System.out.print(A[i]+" ");
        System.out.println("\n部分排序选出数组中最小的"+k+"个数：");
        for(int i = 0;i < k;i++)
            System.out.print(A[i]+" ");
    }

    //获取数组前k个元素的最大值的数组下标
    public int getMaxArrayK(int[] A,int k){
        int result = 0;
        if(k > A.length)
            return 0;
        for(int i = 0;i < k;i++){
            if(A[i] > A[result])
                result = i;
        }
        return result;
    }

    //方法3：用堆来代替数组
    /*
     * 函数功能：对数组A前k个元素进行堆排序
     */
    public void heapBottomUp(int[] A,int k){
        for(int i = (k-1)/2;i >= 0;i--){
            int temp = i;
            int tempV = A[temp];
            boolean heap = false;
            while(!heap && 2*temp < k-1){
                int j = 2*temp + 1;
                if(j < k-1){
                    if(A[j] < A[j+1])
                        j = j + 1;
                }
                if(tempV >= A[j])
                    heap = true;
                else{
                    A[temp] = A[j];
                    temp = j;
                }
            }
            A[temp] = tempV;
        }
    }

    public void getArrayMinK2(int[] A,int k){
        heapBottomUp(A,k);
        while(true){
            int count = 0;
            for(int i = k;i < A.length;i++){
                if(A[i] < A[0]){
                    swap(A,i,0);
                    heapBottomUp(A,k);
                }
                else
                    count++;
            }
            if(count == A.length-k)
                break;
        }
        System.out.println("\n"+"使用方法3进行部分堆排序后的结果：");
        for(int i = 0;i < A.length;i++)
            System.out.print(A[i]+" ");
        System.out.println("\n部分排序选出数组中最小的"+k+"个数：");
        for(int i = 0;i < k;i++)
            System.out.print(A[i]+" ");
    }

    //方法4：线性选择法
    public void getArrayMinK3(int[] A,int k){
        int start = 0;
        int end = A.length - 1;
        int tempK = LomutoPartition(A,start,end);
        while(tempK != k){
            if(tempK > k){
                end = tempK - 1;
                tempK = LomutoPartition(A,start,end);
            }
            if(tempK < k){
                start = tempK + 1;
                tempK = LomutoPartition(A,start,end);
            }
        }
        System.out.println("\n"+"使用方法4进行快速选择排序后的结果：");
        for(int i = 0;i < A.length;i++)
            System.out.print(A[i]+" ");
        System.out.println("\n部分排序选出数组中最小的"+k+"个数：");
        for(int i = 0;i < k;i++)
            System.out.print(A[i]+" ");
    }

    /**
     * 非排序算法
     * @param args
     */
    public static void noSort(String[] args) {
        int[] arr = {-1, 10, 29, 194, 65, 78, 3, 15, 156, 180,
                -6, 11};

        int[] res = new int[3];
        int max = Integer.MIN_VALUE;
        int index = -1;

        //循环三次找到三个最大数
        for(int j = 0; j < 3; j++) {
            for(int i = 0; i < arr.length; i++) {
                if(arr[i] > max) {
                    max = arr[i];
                    index = i;
                }
            }
            res[j] = max;
            arr[index] = Integer.MIN_VALUE;
            max = Integer.MIN_VALUE;
        }
        //输出
        for(int i : res)
            System.out.println(i);
    }

    public ArrayList<Integer> noSort2(int [] input, int k) {
        //找出其中最小的K个数，也就是说只需要对前K个数进行排序，可采用冒泡排序的方法
        ArrayList<Integer> a = new ArrayList<>();
        //先计算一下数组的长度
        int len = input.length;
        if(k>len){
            return a;
        }
        for(int i=0;i<k;i++){
            for(int j=i+1;j<len;j++){
                if(input[j]<input[i]){
                    int temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }
            }
            a.add(input[i]);
        }
        return a;
    }

    public static void main(String[] args){
        Largest5 test = new Largest5();
        int[] A = {9,8,7,5,4,3,2,1,6,3,4,5,12,32,3,2,1,4,6,34};
        test.quickSort(A, 0, A.length-1);
        System.out.println("对数组进行排序后结果：");
        for(int i = 0;i < A.length;i++)
            System.out.print(A[i]+" ");
        System.out.println("\n"+"输出数组最小的5个数：");
        test.printArrayK(A, 5);
        int[] B = {9,8,7,5,4,3,2,1,6,3,4,5,12,32,3,2,1,4,6,34};
        test.getArrayMinK(B, 5);
        int[] C = {2,9,7,6,5,8};
        test.heapBottomUp(C, 6);
        System.out.println("\nC数组：");
        for(int i = 0;i < C.length;i++)
            System.out.print(C[i]+" ");
        int[] D = {9,8,7,5,4,3,2,1,6,3,4,5,12,32,3,2,1,4,6,34};
        test.getArrayMinK2(D, 5);
        int[] E = {9,8,7,5,4,3,2,1,6,3,4,5,12,32,3,2,1,4,6,34};
        test.getArrayMinK3(E, 5);
    }
}


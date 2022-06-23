package datastructure.array;

/**
 * 给你一个正整数吗，生成一个包含1到n2所有元素，且元素按顺时针顺序略选排列的n * n正放心矩阵matrix
 * 示例：1 2 3
 * 8 9 4
 * 7 6 5
 * 输入：3
 * 输出：【【1,2,3】【8,9,4】 【7，6，5】】
 */
public class P5_59_SpiralMatrixII_conplexForLoop {
    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int startX = 0, startY = 0;//定义每一个圈的起始位置
        int loop = n / 2;//每个圈循环几次，例如n为奇数3，那么loop = 1只是循环一圈，矩阵中间的值需要单独处理
        int mid = n / 2;//矩阵中间的位置，例如：n为3，中间的位置就是（1,1），n为5，中间位置为（2,2）
        int count = 1;//用来给矩阵中每一个空格赋值
        int offset = 1;//每一圈循环，需要控制每一条边遍历的长度
        int i, j;
        while (loop > 0) {
            i = startX;
            j = startY;
            //下面开始的四个for就是模拟转了一圈
            //模拟填充上行从左到右（左闭右开）
            for (j = startY; j < startY + n - offset; j++) {
                matrix[i][j] = count++;//i不变，j++
            }
            //模拟填充右列从上到下（左闭右开）
            for (i = startX; i < startX + n - offset; i++) {
                matrix[i][j] = count++;//j不变，i++
            }
            //模拟填充下行从右到左（左闭右开）
            for (; j > startY; j--) {
                matrix[i][j] = count++;//i不变，j--
            }
            //模拟填充左列从下到上（左闭右开）
            for (; i > startX; i--) {
                matrix[i][j] = count++;//j不变，i++
            }
            loop--;
            //第二圈开始的时候其实位置要各自加一，例如：第一圈起始位置是（0，0），第二圈其还是位置是（1,1）
            startX++;
            startY++;
            //offset控制每一圈里每一条边遍历的长度
            offset += 2;
        }
        //如果n为奇数的话，需要单独给矩阵最中间的位置赋值
        if (n % 2 == 1) {
            matrix[mid][mid] = count;
        }
        return matrix;
    }

    public static int[][] matrixGenerateSelf(int n) {
        int startX = 0, startY = 0;
        int loop = n / 2;
        int i = 0, j = 0;
        int offset = 1;
        int[][] matrix = new int[n][n];
        int count = 1;
        int mid = n / 2;
        while (loop > 0) {
            i = startX;
            j = startY;
            for (j = startX; j < startX + n - offset; j++) {
                matrix[i][j] = count++;
            }
            for (i = startY; i < startY + n - offset; i++) {
                matrix[i][j] = count++;
            }
            for (; j > startX; j--) {
                matrix[i][j] = count++;
            }
            for (; i > startY; i--) {
                matrix[i][j] = count++;
            }
            startX++;
            startY++;
            loop--;
            offset += 2;
        }
        if (n % 2 == 1) {
            matrix[mid][mid] = count;
        }
        return matrix;
    }

    public static void main(String[] args) {
        for (int[] ints : matrixGenerateSelf(3)) {
            for (int n : ints) {
                System.out.print(n);
            }
            System.out.println();
        }
    }
}

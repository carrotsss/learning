package zuo_exercise.class32;

public class Problem_0190_ReverseBits {

	// 代码看着很魔幻吧？
	// 给个例子，假设n二进制为：
	// 1011 0111 0011 1001 0011 1111 0110 1010 
	// 解释一下，第一行，是把n左边16位，和n右边16位交换
	// n = (n >>> 16) | (n << 16);
	// 因为 n >>> 16 就是左边16位被移动到了右侧
	// 同时 n << 16  就是右边16位被移动到了左侧
	// 又 | 在了一起，所以，n变成了
	// 0011 1111 0110 1010 1011 0111 0011 1001
	
	// 第二行，
	// n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
	// (n & 0xff00ff00)  
	// 这一句意思是，左侧开始算0~7位，保留；8~15位，全变0；16~23位，保留；24~31位，全变0
	// 0011 1111 0000 0000 1011 0111 0000 0000
	// (n & 0xff00ff00) >>> 8 这句就是上面的值，统一向右移动8位，变成：
	// 0000 0000 0011 1111 0000 0000 1011 0111
	//
	//
	// (n & 0x00ff00ff)
	// 这一句意思是，左侧开始算0~7位，全变0；8~15位，保留；16~23位，全变0；24~31位，保留
	// 0000 0000 0110 1010 0000 0000 0011 1001
	// (n & 0x00ff00ff) << 8 这句就是上面的值，统一向左移动8位，变成：
	// 0110 1010 0000 0000 0011 1001 0000 0000
	// 那么 ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8)
	// 什么效果？就是n的0~7位和8~15位交换了，16~23位和24~31位交换了
	// 0110 1010 0011 1111 0011 1001 1011 0111

	// 也就是说，整个过程是n的左16位，和右16位交换
	// n的左16位的内部，左8位和右8位交换；n的右16位的内部，左8位和右8位交换
	// 接下来的一行，其实是，从左边开始算，0~7位内部，左4和右4交换；8~15位，左4和右4交换；...
	// 接下来的一行，其实是，从左边开始算，0~3位内部，左2和右2交换；4~7位，左2和右2交换；...
	// 最后的一行，其实是，从左边开始算，0~1位内部，左1和右1交换；2~3位，左1和右1交换；...
	public static int reverseBits(int n) {
		// n的高16位，和n的低16位，交换
		n = (n >>> 16) | (n << 16);
		n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
		n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
		n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
		n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
		return n;
	}

}


package com.atguigu.annotation.orm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @classDesc: 功能描述:(定义字段属性)
 * @author: 余胜军
 * @createTime: 2017年8月27日 上午12:13:32
 * @version: v1.0
 * @copyright:上海每特教育科技有限公司
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SetProperty {

	/**
	 * 
	 * @methodDesc: 功能描述:(字段名称)
	 * @author: 余胜军
	 * @param: @return
	 * @createTime:2017年8月27日 上午12:14:02
	 * @returnType:@return String
	 * @copyright:上海每特教育科技有限公司
	 */
	String name();

	/**
	 * 
	 * @methodDesc: 功能描述:(长度)
	 * @author: 余胜军
	 * @param: @return
	 * @createTime:2017年8月27日 上午12:14:25
	 * @returnType:@return int
	 * @copyright:上海每特教育科技有限公司
	 */
	int leng();

}

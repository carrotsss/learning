
package com.atguigu.annotation.orm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @classDesc: 功能描述:(自定义表映射注解 )
 * @author: 余胜军
 * @createTime: 2017年8月27日 上午12:09:53
 * @version: v1.0
 * @copyright:上海每特教育科技有限公司
 */
@Target(value = { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SetTable {

	/**
	 * 
	 * @methodDesc: 功能描述:(对应数据库表名称)
	 * @author: 余胜军
	 * @param: @return
	 * @createTime:2017年8月27日 上午12:10:49
	 * @returnType:@return String
	 * @copyright:上海每特教育科技有限公司
	 */
	String value();

}

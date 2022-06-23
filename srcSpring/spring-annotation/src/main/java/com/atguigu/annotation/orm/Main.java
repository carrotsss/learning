
package com.atguigu.annotation.orm;

import java.lang.reflect.Field;

/**
 * 
 * @classDesc: 功能描述:(使用java自定义注解 模拟ORM框架注解版本 )
 * @author: 余胜军
 * @createTime: 2017年8月27日 上午12:41:52
 * @version: v1.0
 * @copyright:上海每特教育科技有限公司
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		// 1.反射class
		Class<?> classForName = Class.forName("com.atguigu.annotation.orm.Sudent");
		// 2.获取表名称注解F
		SetTable setTable = classForName.getAnnotation(SetTable.class);
		// 3.获取所有的成员属性
		Field[] declaredFields = classForName.getDeclaredFields();
		StringBuffer sf = new StringBuffer();
		sf.append(" select ");
		String fromName = setTable.value();
		for (int i = 0; i < declaredFields.length; i++) {
			Field field = declaredFields[i];
			// 4.属性字段
			SetProperty sb = field.getAnnotation(SetProperty.class);
			sf.append(" " + sb.name() + " ");
			if (i == declaredFields.length - 1) {
				sf.append(" from ");
			} else {
				sf.append(" , ");
			}
		}
		sf.append(" " + fromName);
		System.out.println(sf.toString());
	}

}

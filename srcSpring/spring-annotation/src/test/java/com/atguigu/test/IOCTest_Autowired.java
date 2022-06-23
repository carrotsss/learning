package com.atguigu.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.bean.Boss_HiveArgs;
import com.atguigu.config.MainConifgOfAutowired;

public class IOCTest_Autowired {
	
	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifgOfAutowired.class);
		
//		BookService bookService = applicationContext.getBean(BookService.class);
//		System.out.println(bookService);
		
		//BookDao bean = applicationContext.getBean(BookDao.class);
		//System.out.println(bean);
		
//		Boss_HiveArgs bossHiveArgs = applicationContext.getBean(Boss_HiveArgs.class);
//		System.out.println(bossHiveArgs);
//		Car car = applicationContext.getBean(Car.class);
//		System.out.println(car);
		
//		Color color = applicationContext.getBean(Color.class);
//		System.out.println(color);
		System.out.println(applicationContext);
		applicationContext.close();
	}

}

package com.abc.demo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.abc.domain.Student;
import com.abc.mapper.StudentMapper;

public class TestDemoSessionTemplate {
	private static ApplicationContext ctx = null;

	// static area, only when class is init will be execute, ex, first time use
	// the class as static variable or create the class object
	static {
		ctx = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
	}

	public static void main(String[] args) {
		SqlSession sqlSession = (SqlSession) ctx.getBean("sqlSessionTemplate");
		Student student = new Student();
		student.setGender("man");
		student.setGrade("2016");
		student.setMajor("computer");
		student.setName("andy");

		// sqlSession.insert("com.abc.mapper.StudentMapper.add", student);
		// sqlSession.close();
		StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
		int count = studentMapper.add(student);
		
		
		System.out.println(count);
	}
}

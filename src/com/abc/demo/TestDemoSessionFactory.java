package com.abc.demo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.abc.domain.Student;
import com.abc.mapper.StudentMapper;

public class TestDemoSessionFactory {

	private static ApplicationContext ctx = null;

	// static area, only when class is init will be execute, ex, first time use
	// the class as static variable or create the class object
	static {
		ctx = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
	}

	public static void main(String[] args) {
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ctx.getBean("sqlSessionFactory");
		// System.out.println(sqlSessionFactory.getClass());

		SqlSession sqlSession = sqlSessionFactory.openSession();
		StudentMapper studentMapper = null;
		try {
			studentMapper = sqlSession.getMapper(StudentMapper.class);
			List<Student> students = studentMapper.getAll();

			for (Student student : students) {
				System.out.println(student.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
}

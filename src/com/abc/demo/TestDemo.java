package com.abc.demo;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.abc.domain.Student;

public class TestDemo {
	private static SqlSessionFactory factory = null;

	// static area, only when class is init will be execute, ex, first time use
	// the class as static variable or create the class object
	static {
		String resource = "mybatis-config.xml";
		try {
			// load the config file
			Reader reader = Resources.getResourceAsReader(resource);

			// create the SqlSessionFactory Object
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SqlSession session = factory.openSession();
		int count = 0;
		Student student = new Student();

		try {
			// create the student object 
			student.setGender("男");
			student.setGrade("2016");
			student.setMajor("computer science");
			student.setName("Andy");

			// insert into database 
			count = session.insert("com.abc.mapper.StudentMapper.add", student);
			System.out.println("insert: " + count + " records");
			System.out.println(student.getId());

			count = session.delete("com.abc.mapper.StudentMapper.deleteById", student.getId());
			System.out.println("delete: " + count + " records");
			
			// commit to the database, otherwise the record will not get insert
			// into it 
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close the sql session
			session.close();
		}
	}
}

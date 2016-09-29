package com.abc.demo;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.abc.domain.Student;
import com.abc.mapper.StudentMapper;

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
		Student student1 = new Student();
		Student student2 = new Student();
		Student student3 = new Student();
		List<Student> students = null;
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);

		try {
			// create the student object
			student1.setGender("man");
			student1.setGrade("2016");
			student1.setMajor("computer science");
			student1.setName("Andy");

			// insert into database
			/**
			 * old method - that doesnt use the mapper to do the job
			 * count = session.insert("com.abc.mapper.StudentMapper.add", student1);
			 */
			count = studentMapper.add(student1);
			System.out.println("insert: " + count + " records");
			System.out.println("new student ID : " + student1.getId());
			System.out.println(
					"==========================================================================================");

			// search student record by student id
			/**
			 * old method - that doesnt use the mapper to do the job
			 * student2 = session.selectOne("com.abc.mapper.StudentMapper.getById", student1.getId()); 
			 */
			
			student2 = studentMapper.getById(student1.getId());
			System.out.println("searching for the student records: ");
			System.out.println("student record: " + student2.toString());
			System.out.println(
					"==========================================================================================");

			// find all the students
			students = studentMapper.getAll();
			System.out.println("searching for all the student records: ");
			for (Student student : students) {
				System.out.println(student.toString());
			}
			System.out.println(
					"==========================================================================================");

			// update student record by student id
			student2.setGender("woman");
			student2.setGrade("2017");
			student2.setMajor("art");
			student2.setName("annie");
			/**
			 * old method - that doesnt use the mapper to do the job
			 * count = session.update("com.abc.mapper.StudentMapper.updateById", student2);
			 */
			count = studentMapper.updateById(student2);
			System.out.println("update: " + count + " records");
			System.out.println("student record: " + student2.toString());
			System.out.println(
					"==========================================================================================");

			// find all the students
			students = studentMapper.getAll();
			System.out.println("searching for all the student records: ");
			for (Student student : students) {
				System.out.println(student.toString());
			}
			System.out.println(
					"==========================================================================================");

			
			// delete the one student records
			/**
			 * old method - that doesnt use the mapper to do the job
			 * count = session.delete("com.abc.mapper.StudentMapper.deleteById", student1.getId());
			 */
			count = studentMapper.deleteById(student1.getId());
			System.out.println("delete: " + count + " records");
			System.out.println(
					"==========================================================================================");

			// find all the students
			students = studentMapper.getAll();
			System.out.println("searching for all the student records: ");
			for (Student student : students) {
				System.out.println(student.toString());
			}
			System.out.println(
					"==========================================================================================");

			
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

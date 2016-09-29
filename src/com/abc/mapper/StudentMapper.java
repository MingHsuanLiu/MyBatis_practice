package com.abc.mapper;

import java.util.List;

import com.abc.domain.Student;

public interface StudentMapper {
	public Integer add(Student student);
	public Integer deleteById(Integer id);
	public Integer updateById(Student student);
	public Student getById(Integer id);
	public List<Student> getAll();

}

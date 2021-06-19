package com.sri.sm.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sri.sm.domain.Student;
import com.sri.sm.rowmapper.StudentRowMapper;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Student> loadStudents() {

		String query = "select * from students";
		List<Student> listStudents = jdbcTemplate.query(query, new StudentRowMapper());

		return listStudents;
	}

	

	@Override
	public Student getStudent(int id) {

		String sql = "SELECT * FROM STUDENTS WHERE ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new StudentRowMapper());

	}

	@Override
	public int addStudent(Student student) {

		String sql = "insert into STUDENTS (name, mobile, country, email) values (?, ?, ?, ?)";
		int id = jdbcTemplate.update(sql, student.getName(), student.getMobile(), student.getCountry(), student.getEmail());
		return id;
	}

	@Override
	public void updateStudent(Student student) {

		String SQL = "update Students set name = ?,mobile=?,country=?,email=? where id = ?";
		int id = jdbcTemplate.update(SQL, student.getName(), student.getMobile(), student.getCountry(),student.getEmail(), student.getId());

	}

	@Override
	public void deleteStudent(Student student) {
		
		String SQL = "delete from Students where id = ?";
		int id = jdbcTemplate.update(SQL, student.getId());
		
	}

}

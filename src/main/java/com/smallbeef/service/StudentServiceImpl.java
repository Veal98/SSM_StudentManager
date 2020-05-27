package com.smallbeef.service;

import com.smallbeef.bean.Student;
import com.smallbeef.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;

    @Override
    public int getTotal() {
        return studentDao.getTotal();
    }

    @Override
    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentDao.deleteStudent(id);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    @Override
    public Student getStudent(int id) {
        return studentDao.getStudent(id);
    }


    @Override
    public List<Student> list(int start, int count) {
        return studentDao.list(start, count);
    }
}

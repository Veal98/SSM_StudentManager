package com.smallbeef.dao;

import com.smallbeef.bean.Student;

import java.util.List;

public interface StudentDao {
    /**
     * 获取学生总数
     * @return
     */
    int getTotal();

    /**
     * 添加一个学生
     * @param student
     */
    void addStudent(Student student);

    /**
     * 根据 id 删除一个人学生
     * @param id
     */
    void deleteStudent(int id);

    /**
     * 修改一个学生信息
     * @param student
     */
    void updateStudent(Student student);

    /**
     * 根据 id 获取一个学生信息
     * @param id
     * @return
     */
    Student getStudent(int id);

    /**
     * 查询从start位置开始的count条数据
     */
    List<Student> list(int start, int count);
}

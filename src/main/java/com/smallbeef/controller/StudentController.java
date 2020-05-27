package com.smallbeef.controller;

import com.smallbeef.util.Page;
import com.smallbeef.bean.Student;
import com.smallbeef.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 增加一个学生信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addStudent")
    public String addStudent(HttpServletRequest request, HttpServletResponse response){
        Student student = new Student();

        // 获取前端传值
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        Date birthday = null;
        // 将 String 类型的日期按照 yyyy-MM-dd 的格式转换为 java.util.Date 类
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthday = simpleDateFormat.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        student.setStudent_id(studentId);
        student.setName(name);
        student.setAge(age);
        student.setSex(sex);
        student.setBirthday(birthday);

        studentService.addStudent(student);
        return "redirect:listStudent";
    }

    /**
     * 根据 id 删除一个学生信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteStudent")
    public String deleteStudent(int id){
        studentService.deleteStudent(id);
        return "redirect:listStudent";
    }


    /**
     * 修改一个学生信息,进入修改界面editStudent后再调用
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateStudent")
    public String updateStudent(HttpServletRequest request, HttpServletResponse response){
        Student student = new Student();

        // 获取前端传值
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        Date birthday = null;
        // 将 String 类型的日期按照 yyyy-MM-dd 的格式转换为 java.util.Date 类
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthday = simpleDateFormat.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        student.setStudent_id(studentId);
        student.setName(name);
        student.setAge(age);
        student.setSex(sex);
        student.setBirthday(birthday);

        studentService.updateStudent(student);
        return "redirect:listStudent";
    }

    /**
     * 分页显示学生信息
     */
    @RequestMapping("/listStudent")
    public String listStudent(HttpServletRequest request, HttpServletResponse response) {

        // 获取分页参数
        int start = 0;
        int count = 6;

        try {
            start = Integer.parseInt(request.getParameter("page.start"));
            count = Integer.parseInt(request.getParameter("page.count"));
        } catch (Exception e) {
        }

        // 创建分页模型
        Page page = new Page(start, count);

        // 按照页码查询学生信息
        List<Student> students = studentService.list(page.getStart(), page.getCount());
        int total = studentService.getTotal();
        page.setTotal(total);

        // 将查询出来的学生信息放在域中
        request.setAttribute("students", students);
        request.setAttribute("page", page);

        return "listStudent";
    }

    /**
     * 用于修改学生信息界面的信息回显
     * @param id
     * @return
     */
    @RequestMapping("/editStudent")
    public ModelAndView editStudent(int id){
        // 创建一个模型视图对象
        ModelAndView mv = new ModelAndView();
        // 查询学生信息
        Student student = studentService.getStudent(id);
        // 将数据放置到 ModelAndView 对象视图中
        mv.addObject("student",student);
        // 放入 jsp 界面
        mv.setViewName("editStudent");
        return mv;
    }
}

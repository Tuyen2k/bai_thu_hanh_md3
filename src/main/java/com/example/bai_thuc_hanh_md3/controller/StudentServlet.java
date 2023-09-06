package com.example.bai_thuc_hanh_md3.controller;

import com.example.bai_thuc_hanh_md3.model.Classroom;
import com.example.bai_thuc_hanh_md3.model.Student;
import com.example.bai_thuc_hanh_md3.server.iplm.ClassroomManage;
import com.example.bai_thuc_hanh_md3.server.iplm.StudentManage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/homes")
public class StudentServlet extends HttpServlet {
    private StudentManage studentManage;
    private ClassroomManage classroomManage;

    @Override
    public void init() throws ServletException {
        studentManage = new StudentManage();
        classroomManage = new ClassroomManage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "delete": delete(request,response);
                break;
            case "update": updateGet(request,response);
                break;
            default: displayStudent(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create": createPost(request,response);
                break;
            case "update": updatePost(request,response);
                break;
            case "search": search(request,response);
                break;
            default: displayStudent(request,response);
        }
    }
    private void displayStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentManage.findAll();
        List<Classroom> classrooms = classroomManage.findAll();
        request.setAttribute("students",students);
        request.setAttribute("classrooms",classrooms);
        RequestDispatcher rq = request.getRequestDispatcher("display_product.jsp");
        rq.forward(request,response);
    }
    private void createPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        LocalDate DOB = LocalDate.parse(request.getParameter("date"));
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        int id_class = Integer.parseInt(request.getParameter("classroom"));
        Classroom classroom = classroomManage.findOne(id_class);
        Student student = new Student(name,email,DOB,address,phone,classroom);
        studentManage.create(student);
        HttpSession session = request.getSession();
        session.setAttribute("message","Create Success!");
        session.setAttribute("flag",true);
        response.sendRedirect("homes");
    }
    private void updateGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String strId = request.getParameter("id_student");
        HttpSession session = request.getSession();
        if (strId != null){
            int id = Integer.parseInt(strId);
            Student student = studentManage.findOne(id);
            if (student.getId() != 0){
                List<Classroom> classrooms = classroomManage.findAll();
                session.setAttribute("student",student);
                session.setAttribute("classrooms",classrooms);
                response.sendRedirect("update_product.jsp");
            }else {
                session.setAttribute("message","There is no student with this id!");
                session.setAttribute("flag",true);
                response.sendRedirect("homes");
            }
        }
        else {
            response.sendRedirect("not_found.jsp");
        }
    }
    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String strId = request.getParameter("id_student");
        HttpSession session = request.getSession();
        if (strId != null){
            int id = Integer.parseInt(strId);
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String strDate = request.getParameter("date");
            LocalDate DOB = LocalDate.parse(strDate);
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            int id_class = Integer.parseInt(request.getParameter("classroom"));
            Classroom classroom = classroomManage.findOne(id_class);
            Student student = new Student(id, name,email,DOB,address,phone,classroom);
            studentManage.update(student);
            session.setAttribute("message","Update Success!");
            session.setAttribute("flag",true);
            response.sendRedirect("homes");
        }
        else {
            response.sendRedirect("not_found.jsp");
        }
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String strId = request.getParameter("id_student");
        HttpSession session = request.getSession();
        if (strId != null){
            int id = Integer.parseInt(strId);
            studentManage.delete(id);
            session.setAttribute("message","Delete Success!");
            session.setAttribute("flag",true);
            response.sendRedirect("homes");
        }
        else {
            response.sendRedirect("not_found.jsp");
        }
    }
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("keyword");
        List<Student> students = studentManage.search(name);
        List<Classroom> classrooms = classroomManage.findAll();
        request.setAttribute("students",students);
        request.setAttribute("classrooms",classrooms);
        RequestDispatcher rq = request.getRequestDispatcher("display_product.jsp");
        rq.forward(request,response);
    }

}
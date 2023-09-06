package com.example.bai_thuc_hanh_md3.server.iplm;

import com.example.bai_thuc_hanh_md3.DAO.StudentDAO;
import com.example.bai_thuc_hanh_md3.model.Student;
import com.example.bai_thuc_hanh_md3.server.IStudentService;

import java.util.List;

public class StudentManage implements IStudentService {
    private StudentDAO studentDAO;

    public StudentManage() {
        studentDAO = new StudentDAO();
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public Student findOne(int id) {
        return studentDAO.findById(id);
    }

    @Override
    public void create(Student student) {
        studentDAO.create(student);
    }

    @Override
    public void update(Student student) {
        studentDAO.update(student);
    }

    public void delete(int id) {
        studentDAO.delete(id);
    }

    public List<Student> search(String name) {
        return studentDAO.search(name);
    }
}

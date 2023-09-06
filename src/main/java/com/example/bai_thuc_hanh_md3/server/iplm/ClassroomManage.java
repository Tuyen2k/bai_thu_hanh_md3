package com.example.bai_thuc_hanh_md3.server.iplm;

import com.example.bai_thuc_hanh_md3.DAO.ClassroomDAO;
import com.example.bai_thuc_hanh_md3.model.Classroom;
import com.example.bai_thuc_hanh_md3.server.IClassroomService;

import java.util.List;

public class ClassroomManage implements IClassroomService {
    private ClassroomDAO classroomDAO;
    public ClassroomManage(){
        classroomDAO = new ClassroomDAO();
    }
    @Override
    public List<Classroom> findAll() {
        return classroomDAO.findAll();
    }

    @Override
    public Classroom findOne(int id) {
        return classroomDAO.findById(id);
    }

    @Override
    public void create(Classroom classroom) {

    }

    @Override
    public void update(Classroom classroom) {

    }
}

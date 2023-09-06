package com.example.bai_thuc_hanh_md3.DAO;

import com.example.bai_thuc_hanh_md3.model.Classroom;
import com.example.bai_thuc_hanh_md3.myConnection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassroomDAO {
    private MyConnection myConnection = MyConnection.getMyConnection();
    private Connection connection;
    private String SELECT_BY_ID = "select * from classroom where id_class = ?;";
    private String SELECT_ALL = "select * from classroom;";
    public ClassroomDAO(){
        connection = myConnection.getConnection();
    }

    public Classroom findById(int id){
        Classroom classroom = new Classroom();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                classroom = new Classroom(id,name);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return classroom;
    }
    public List<Classroom> findAll(){
        List<Classroom> classrooms = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id_class");
                String name = resultSet.getString("name");
                classrooms.add(new Classroom(id,name));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return classrooms;
    }
}

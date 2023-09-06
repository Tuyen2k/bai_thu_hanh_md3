package com.example.bai_thuc_hanh_md3.DAO;

import com.example.bai_thuc_hanh_md3.model.Classroom;
import com.example.bai_thuc_hanh_md3.model.Student;
import com.example.bai_thuc_hanh_md3.myConnection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private MyConnection myConnection = MyConnection.getMyConnection();
    private Connection connection;
    private ClassroomDAO  classroomDAO;
    private String SELECT_ALL = "select * from student;";
    private String SELECT_BY_ID = "select * from student where id_student = ?;";
    private String SELECT_BY_NAME = "select * from student where name like ?;";
    private String INSERT_INTO = "insert into student(name, email, DOB, address, phone_number, id_class) VALUE (?,?,?,?,?,?);";
    private String UPDATE = "update student set name = ?, email = ?, DOB = ?, address = ?, phone_number = ?, id_class = ? where id_student = ?;";
    private String DELETE = "delete from student where id_student = ?;";
    public StudentDAO(){
        connection = myConnection.getConnection();
        classroomDAO = new ClassroomDAO();
    }
    public List<Student> findAll(){
        List<Student> students = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id_student");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                LocalDate DOB = resultSet.getObject("DOB", LocalDate.class);
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                int id_class = resultSet.getInt("id_class");
                Classroom classroom = classroomDAO.findById(id_class);
                students.add(new Student(id,name,email,DOB,address,phoneNumber,classroom));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }
    public Student findById(int id){
        Student student = new Student();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                LocalDate DOB = resultSet.getObject("DOB", LocalDate.class);
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                int id_class = resultSet.getInt("id_class");
                Classroom classroom = classroomDAO.findById(id_class);
                student = new Student(id,name,email,DOB,address,phoneNumber,classroom);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return student;
    }
    public void create(Student student){
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getEmail());
            preparedStatement.setString(3,student.getDOB().toString());
            preparedStatement.setString(4,student.getAddress());
            preparedStatement.setString(5,student.getPhoneNumber());
            preparedStatement.setInt(6,student.getClassroom().getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void update(Student student){
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(7,student.getId());
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getEmail());
            preparedStatement.setString(3,student.getDOB().toString());
            preparedStatement.setString(4,student.getAddress());
            preparedStatement.setString(5,student.getPhoneNumber());
            preparedStatement.setInt(6,student.getClassroom().getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void delete(int id){
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<Student> search(String str){
        List<Student> students = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME)) {
            preparedStatement.setString(1,'%'+str+'%');
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id_student");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                LocalDate DOB = resultSet.getObject("DOB", LocalDate.class);
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                int id_class = resultSet.getInt("id_class");
                Classroom classroom = classroomDAO.findById(id_class);
                students.add(new Student(id,name,email,DOB,address,phoneNumber,classroom));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }

}

package com.example.demo.repositories;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements IRepository<Employee> {

    @Override
    public List<Employee> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Employee> allEmployees = new ArrayList<Employee>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employees");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Employee temp = new Employee(
                        rs.getInt("employeeID"),
                        rs.getString("name")
                );
                allEmployees.add(temp);
            }

        }catch(SQLException e){
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allEmployees;
    }

    @Override
    public Employee getSingleById(int id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employees WHERE id=?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                        rs.getInt("employeeID"),
                        rs.getString("name"));
            }

        }catch(SQLException e){
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Employee employee) {
        Connection conn = DatabaseConnectionManager.getConnection();
        try {
            PreparedStatement psts = conn.prepareStatement("INSERT INTO wishing_well.users (name) VALUES (?)");
            psts.setString(1, employee.getName());
            psts.executeUpdate();

        } catch (SQLException e) {
            System.out.println("duplicate entry");
            throw new RuntimeException(e);
        }
        return false;
    }
}

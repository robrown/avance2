package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Department;
import model.Employee;
import util.DBConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	Connection connection = null;
	ResultSet resultSet = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	
	@Override
	public List<Employee> get() {
		
		List<Employee> list = null;
		Employee employee = null;
		
		try {
			
			list = new ArrayList<Employee>();
			String sql = "SELECT e.id as id_employee, e.name as name_employee, e.hire_date, d.id as id_department, d.name as name_department FROM tbl_employee e  inner join tbl_department d on e.department_id = d.id";
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				employee = new Employee();
				Department department = new Department();
				
				department.setId(resultSet.getInt("id_department"));
				department.setName(resultSet.getString("name_department"));
				
				employee.setId(resultSet.getInt("id_employee"));
				employee.setName(resultSet.getString("name_employee"));
				employee.setDepartment(department);
				employee.setHireDate(resultSet.getString("hire_date"));
				list.add(employee);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Employee get(int id) {
		Employee employee = null;
		Department department = null;
		try {
			employee = new Employee();
			department = new Department();
			String sql = "SELECT e.id as id_employee, e.name as name_employee, e.hire_date, d.id as id_department, d.name as name_department FROM tbl_employee e  inner join tbl_department d on e.department_id = d.id where e.id="+id;
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {				
				department.setId(resultSet.getInt("id_department"));
				department.setName(resultSet.getString("name_department"));
				
				employee.setId(resultSet.getInt("id_employee"));
				employee.setName(resultSet.getString("name_employee"));
				employee.setDepartment(department);
				employee.setHireDate(resultSet.getString("hire_date"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public boolean save(Employee e) {
		boolean flag = false;
		try {
			String sql = "INSERT INTO tbl_employee(name, department_id, hire_date)VALUES"
					+ "('"+e.getName()+"', '"+e.getDepartment().getId()+"', '"+e.getHireDate()+"')";
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delete(int id) {
		boolean flag = false;
		try {
			String sql = "DELETE FROM tbl_employee where id="+id;
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean update(Employee employee) {
		boolean flag = false;
		try {
			String sql = "UPDATE tbl_employee SET name = '"+employee.getName()+"', "
					+ "department_id = '"+employee.getDepartment().getId()+"', hire_date = '"+employee.getHireDate()+"' where id="+employee.getId();
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}

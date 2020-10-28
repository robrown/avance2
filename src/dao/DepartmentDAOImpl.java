package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Department;
import util.DBConnectionUtil;

public class DepartmentDAOImpl implements DepartmentDAO {
	Connection connection = null;
	ResultSet resultSet = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	
	@Override
	public List<Department> get() {
		List<Department> list = null;
		Department department = null;
		
		try {
			
			list = new ArrayList<Department>();
			String sql = "SELECT * FROM tbl_department";
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				department = new Department();
				department.setId(resultSet.getInt("id"));
				department.setName(resultSet.getString("name"));
				list.add(department);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

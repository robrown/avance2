package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Login;
import util.DBConnectionUtil;

public class LoginDAOImpl implements LoginDAO{
	//Clase para la autenticación, falta encriptar contraseña...
	@Override
	public String loginCheck(Login loginBean) {
		String query="select * from tbl_login where email=? and password=?";
		
		try{
			Connection con=DBConnectionUtil.openConnection();
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,loginBean.getEmail());
			ps.setString(2,loginBean.getPassword());
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				return "true";
			}
			else{
				return "false";
			}
		}catch(Exception e){
			System.out.print("Hubo un error en la consulta");
			e.printStackTrace();
		}
		
		return "error";
	}

}

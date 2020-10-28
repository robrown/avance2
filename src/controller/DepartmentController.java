package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DepartmentDAO;
import dao.DepartmentDAOImpl;
import model.Department;


public class DepartmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	DepartmentDAO departmentDAO = null;
	
	public DepartmentController() {
		departmentDAO = new DepartmentDAOImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action == null) {
			action = "LIST";
		}
		
		switch(action) {
			
			case "LIST":
				listDepartment(request, response);
				break;
				
			default:
				listDepartment(request, response);
				break;
				
		}
		
	}


	private void listDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///Método que lista los departamentos 
		///Devuelve un tipo List<> al jsp para su renderizado
		
		List<Department> theList = departmentDAO.get();
		
		request.setAttribute("list", theList);
		
		dispatcher = request.getRequestDispatcher("/views/department-list.jsp");
		
		dispatcher.forward(request, response);
	}

}

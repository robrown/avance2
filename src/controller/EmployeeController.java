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
import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.Department;
import model.Employee;

public class EmployeeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	RequestDispatcher dispatcher = null;
	EmployeeDAO employeeDAO = null;
	DepartmentDAO departmentDAO = null;
	
	public EmployeeController() {
		employeeDAO = new EmployeeDAOImpl();
		departmentDAO = new DepartmentDAOImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action == null) {
			action = "LIST";
		}
		
		switch(action) {
			
			case "LIST":
				listEmployee(request, response);
				break;
				
			case "ADD":
				createEmployee(request, response);
				break;
				
			case "EDIT":
				getSingleEmployee(request, response);
				break;
				
			case "DELETE":
				deleteEmployee(request, response);
				break;
				
			default:
				listEmployee(request, response);
				break;
				
		}
		
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///Método que elimina un empleado según el id recibido por parámetro
		String id = request.getParameter("id");
	
		if(employeeDAO.delete(Integer.parseInt(id))) {
			request.setAttribute("NOTIFICATION", "¡Eliminado Correctamente!");
		}
		
		listEmployee(request, response);
	}
	
	private void createEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		///Método que crea un empleado
		List<Department> departmentList = departmentDAO.get();
		
		request.setAttribute("departments", departmentList);
		
		dispatcher = request.getRequestDispatcher("/views/employee-form.jsp");
		
		dispatcher.forward(request, response);
	}

	private void getSingleEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		///Método que obtiene un empleado según el id recibido por parámetro
		String id = request.getParameter("id");
		
		Employee theEmployee = employeeDAO.get(Integer.parseInt(id));
		List<Department> departmentList = departmentDAO.get();
		
		request.setAttribute("employee", theEmployee);
		request.setAttribute("departments", departmentList);
		
		dispatcher = request.getRequestDispatcher("/views/employee-form.jsp");
		
		dispatcher.forward(request, response);
	}

	private void listEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///Método que devuelve una lista de empleados
		List<Employee> theList = employeeDAO.get();
		
		request.setAttribute("list", theList);
		
		dispatcher = request.getRequestDispatcher("/views/employee-list.jsp");
		
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		Employee employee = new Employee();
		Department department = new Department();
		
		department.setId(Integer.parseInt(request.getParameter("cmbDepartment")));
		
		employee.setName(request.getParameter("name"));
		employee.setHireDate(request.getParameter("hireDate"));
		employee.setDepartment(department);
		
		// Mensajes de Notificación Material Toast
		if(id.isEmpty() || id == null) {
			
			if(employeeDAO.save(employee)) {
				request.setAttribute("NOTIFICATION", "¡Guardado Correctamente!");
			}
		
		}else {
			
			employee.setId(Integer.parseInt(id));
			if(employeeDAO.update(employee)) {
				request.setAttribute("NOTIFICATION", "¡Actualizado Correctamente!");
			}
			
		}
		
		listEmployee(request, response);
	}

}

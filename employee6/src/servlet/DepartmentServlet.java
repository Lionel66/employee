package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DepartmentDao;
import dao.EmployeeDao;
import entity.Department;
import entity.Employee;
import net.sf.json.JSONArray;
import util.Constant;
import util.Pagination;

public class DepartmentServlet extends HttpServlet {
	private static final String path="WEB-INF/department/";
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			String type = request.getParameter("type");
			if (type == null) {
				search(request, response);//show(request, response);
			} else if ("showAdd".equals(type)) {
				showAdd(request, response);
			} else if ("add".equals(type)) {
				add(request, response);
			} else if ("showUpdate".equals(type)) {
				showUpdate(request, response);
			} else if ("update".equals(type)) {
				update(request, response);
			} else if ("delete".equals(type)) {
				delete(request, response);
			}
			
//			else if ("search".equals(type)) {
//				search(request, response);
//			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	private void search(HttpServletRequest request, HttpServletResponse response) {
		try {
		Department condition = new Department();
		String name = request.getParameter("name");
		int empCount = -1;
		if(request.getParameter("empCount")!=null&&!"".equals(request.getParameter("empCount"))) {
			empCount=Integer.parseInt(request.getParameter("empCount"));
		}
		condition.setName(name);
		condition.setEmpCount(empCount);
		DepartmentDao depDao = new DepartmentDao();
		
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}

		int count = depDao.searchCount(condition);

		Pagination p = new Pagination(ye,count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Department> list = depDao.search(condition,p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		request.setAttribute("p", p);
		request.setAttribute("c", condition);
		request.setAttribute("list",list);
		request.getRequestDispatcher(path+"list.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void show(HttpServletRequest request, HttpServletResponse response) {
//		try {
//
//			EmployeeDao empDao = new EmployeeDao();
//			int ye = 1;
//			if (request.getParameter("ye") != null) {
//				ye = Integer.parseInt(request.getParameter("ye"));
//			}
//
//			int count = empDao.searchCount();
//
//			Pagination p = new Pagination(ye,count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
//			List<Employee> list = empDao.search(p.getBegin(), Constant.EMP_NUM_IN_PAGE);
//			request.setAttribute("p", p);
//			request.setAttribute("list", list);
//			request.getRequestDispatcher(path+"list.jsp").forward(request, response);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		try {

			request.getRequestDispatcher(path+"add.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			Department dep = new Department();
			String name = request.getParameter("name");
		
			dep.setName(name);
		
		
			DepartmentDao depDao = new DepartmentDao();
			boolean flag = depDao.add(dep);

			response.sendRedirect("dep");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			DepartmentDao depDao = new DepartmentDao();
			Department dep = depDao.search(id);
			request.setAttribute("dep", dep);
			request.getRequestDispatcher(path+"update.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			Department dep = new Department();
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
		
			
			dep.setId(id);
			dep.setName(name);
			
			DepartmentDao depDao = new DepartmentDao();
			boolean flag = depDao.update(dep);

			response.sendRedirect("dep");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));

			DepartmentDao depDao = new DepartmentDao();
			boolean flag = depDao.delete(id);

			response.sendRedirect("dep");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}



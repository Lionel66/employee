package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.ProjectDao;
import dao.ScoreDao;
import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.Constant;
import util.Grade;
import util.Pagination;

public class ScoreServlet extends HttpServlet {
	private static final String path="WEB-INF/score/";
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			String type = request.getParameter("type");
			if (type == null) {
				search(request, response);//show(request, response);
			}else if ("manage".equals(type)) {
				manage(request, response);
			}else if ("input".equals(type)) {
				input(request, response);
			}
//			else if ("search".equals(type)) {
//				search(request, response);
//			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void basic(HttpServletRequest request, HttpServletResponse response) {
		ScoreDao scDao=new ScoreDao();
		EmployeeDao empDao = new EmployeeDao();
		DepartmentDao depDao = new DepartmentDao();
		ProjectDao proDao=new ProjectDao();
		Employee emp = new Employee();
		Department dep = new Department();
		Project pro=new Project();
		
		
		String name=request.getParameter("name");
    Score condition = new Score();
   
	int depId = -1;
	if (request.getParameter("depId") != null && !"".equals(request.getParameter("depId"))) {
		depId = Integer.parseInt(request.getParameter("depId"));
	}
	int proId = -1;
	if (request.getParameter("proId") != null && !"".equals(request.getParameter("proId"))) {
		proId = Integer.parseInt(request.getParameter("proId"));
	}
	int value = -1;
	if (request.getParameter("value") != null && !"".equals(request.getParameter("value"))) {
		value = Integer.parseInt(request.getParameter("value"));
	}
	String grade=request.getParameter("grade");
	
	emp.setName(name);
	
	dep.setId(depId);
	emp.setDep(dep);
	condition.setEmp(emp);
	pro.setId(proId);
	condition.setPro(pro);
	condition.setValue(value);
	condition.setGrade(Grade.getGrade(grade));
	
	
	
	int ye = 1;
	if (request.getParameter("ye") != null) {
		ye = Integer.parseInt(request.getParameter("ye"));
	}

	int count = scDao.searchCount(condition);

	Pagination p = new Pagination(ye,count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
	List<Score> list = scDao.search(condition,p.getBegin(), Constant.EMP_NUM_IN_PAGE);
	
	List<Department> depList=depDao.search(); 
	List<Project> proList=proDao.search(); 
	request.setAttribute("p", p);
	request.setAttribute("c", condition);
	request.setAttribute("list",list);
	
	request.setAttribute("depList",depList);
	request.setAttribute("proList",proList);
	Grade[] temps=Grade.values();
	Grade[] grades=new Grade[temps.length-1];
	for(int i=0;i<grades.length;i++) {
		grades[i]=temps[i];
	}
	
	request.setAttribute("grades",grades);
	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			basic(request,response);
		request.getRequestDispatcher(path+"scList.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void manage(HttpServletRequest request, HttpServletResponse response) {
		try {
			basic(request,response);
		request.getRequestDispatcher(path+"manage.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void input(HttpServletRequest request, HttpServletResponse response) {
		try {
		int id=Integer.parseInt(request.getParameter("id"));
		int value=Integer.parseInt(request.getParameter("value"));
		Score score=new Score();
		score.setValue(value);
		 ScoreDao scDao=new ScoreDao();
		 boolean flag=false;
		 PrintWriter out;
		
			out = response.getWriter();
		
		if(id==0) {
			int empId=Integer.parseInt(request.getParameter("empId"));
			int proId=Integer.parseInt(request.getParameter("proId"));
			 Employee emp = new Employee();
			    emp.setId(empId);
			    Project pro=new Project();
			    pro.setId(proId);
			    score.setEmp(emp);
			    score.setPro(pro);
			    id=scDao.add(score);
			    if(id>0) {
			    	flag=true;
			    }
			    score.setId(id);
		}else {
			score.setId(id);
			flag=scDao.update(score);
		}
		Score sc=scDao.search(id);
	if(flag) {
		JSON json=JSONObject.fromObject(sc);
		out.println(json);
	}else {
		out.print(flag);
	}
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}



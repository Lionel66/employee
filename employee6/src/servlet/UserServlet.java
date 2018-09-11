
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entity.User;
import util.CreateMD5;
import util.RandomNumber;
import entity.ValidateCode;

public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
		response.setContentType("text/html;charset=utf-8");
		
			request.setCharacterEncoding("utf-8");
		

		String type = request.getParameter("type");

		if (type == null) {
			showLogin(request, response);
		} else if ("doLogin".equals(type)) {
			doLogin(request, response);
		}else if(type.equals("randomImage")) {
			randomImage(request, response);
		}else if(type.equals("showRegister")) {
			showRegister(request, response);
		}else if(type.equals("doRegister")) {
			doRegister(request, response);
		}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void showLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = "";
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if ("username".equals(cookies[i].getName())) {
						name = cookies[i].getValue();
					}
				}
			}
			request.setAttribute("name", name);
			request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			PrintWriter out = response.getWriter();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String random = request.getParameter("random");
			if(random.equals(session.getAttribute("rand"))) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(CreateMD5.getMD5(password));
			UserDao ud = new UserDao();
			boolean flag = ud.search(user);
			if (flag) {
				
				session.setAttribute("user", username);
				Cookie cookie = new Cookie("username", username);
				cookie.setMaxAge(60);
				response.addCookie(cookie);
			} 
			out.print(flag);
			
			}else {
				request.setAttribute("mes", "验证码错误");
				request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void randomImage(HttpServletRequest request, HttpServletResponse response) {
		RandomNumber rn=new RandomNumber();
		try {
			//设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		ValidateCode vc=rn.generateImage();
		ServletOutputStream outStream= response.getOutputStream();
		//输出图像到页面
		ImageIO.write(vc.getImage(), "JPEG", outStream);
		outStream.close();
		request.getSession().setAttribute("rand", vc.getRand());
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void showRegister(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = "";
			
			request.setAttribute("name", name);
			request.getRequestDispatcher("WEB-INF/login/register.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doRegister(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			User user = new User();
			user.setUsername(username);
			user.setPassword(CreateMD5.getMD5(password));
			UserDao ud = new UserDao();
			
			boolean flag=ud.search1(user);
		
			if(flag) {
				request.setAttribute("mes","用户名重复");
				request.getRequestDispatcher("WEB-INF/login/register.jsp").forward(request, response);;
				
			}else {
				ud.add(user);
				response.sendRedirect("user");
			}
				
			
			//request.getRequestDispatcher("WEB-INF/login/register.jsp").forward(request, response);;
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

}

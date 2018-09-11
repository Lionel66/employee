package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;

import entity.User;

public class UserDao extends BaseDao {
	public boolean search(User user){
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
	    boolean flag=false;
	    try {
		
		//3:建立连接
		conn=getConnection();
		//4:建立Statement sql语句执行器
		 String sql="select * from user where username=? and password=?";
	    pstat = conn.prepareStatement(sql);
	    //5：执行sql语句并得到结果
	   pstat.setString(1, user.getUsername());
	   pstat.setString(2, user.getPassword());
	   rs=pstat.executeQuery();
	   // 6:对结果集进行处理
	    	 if(rs.next()){
	    		 flag=true;
	 	    	// out.println("登录成功");
	 	    	// 转发页面
	 	    	// request.getRequestDispatcher("success.jsp").forward(request, response);
	 	    	// 重定向
	 	    }
	    	 
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	
			      closeAll(conn,pstat,rs);
			} 
	    
	    	//7：关闭
		return flag;
		
	}
	public boolean search1(User user){
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
	    boolean flag=false;
	    try {
		
		//3:建立连接
		conn=getConnection();
		//4:建立Statement sql语句执行器
		 String sql="select * from user where username=? ";
	    pstat = conn.prepareStatement(sql);
	    //5：执行sql语句并得到结果
	   pstat.setString(1, user.getUsername());
	  
	   rs=pstat.executeQuery();
	   // 6:对结果集进行处理
	    	 if(rs.next()){
	    		 flag=true;
	 	    	// out.println("登录成功");
	 	    	// 转发页面
	 	    	// request.getRequestDispatcher("success.jsp").forward(request, response);
	 	    	// 重定向
	 	    }
	    	 
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	
			      closeAll(conn,pstat,rs);
			} 
	    
	    	//7：关闭
		return flag;
		
	}
	public boolean add(User user){
		Connection conn=null;
		PreparedStatement pstat=null;
		int rs=0;
	    
	    try {
		
		//3:建立连接
		conn=getConnection();
		//4:建立Statement sql语句执行器
		 String sql="insert into user(username,password) values(?,?)";
	    pstat = conn.prepareStatement(sql);
	    //5：执行sql语句并得到结果
	   pstat.setString(1, user.getUsername());
	   pstat.setString(2, user.getPassword());
	   rs=pstat.executeUpdate();
	   // 6:对结果集进行处理
	    	 
	    	 
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	
			      closeAll(conn,pstat,null);
			} 
	    
	    	//7：关闭
		return rs>0;
		
	}

}

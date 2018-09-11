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
		
		//3:��������
		conn=getConnection();
		//4:����Statement sql���ִ����
		 String sql="select * from user where username=? and password=?";
	    pstat = conn.prepareStatement(sql);
	    //5��ִ��sql��䲢�õ����
	   pstat.setString(1, user.getUsername());
	   pstat.setString(2, user.getPassword());
	   rs=pstat.executeQuery();
	   // 6:�Խ�������д���
	    	 if(rs.next()){
	    		 flag=true;
	 	    	// out.println("��¼�ɹ�");
	 	    	// ת��ҳ��
	 	    	// request.getRequestDispatcher("success.jsp").forward(request, response);
	 	    	// �ض���
	 	    }
	    	 
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	
			      closeAll(conn,pstat,rs);
			} 
	    
	    	//7���ر�
		return flag;
		
	}
	public boolean search1(User user){
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
	    boolean flag=false;
	    try {
		
		//3:��������
		conn=getConnection();
		//4:����Statement sql���ִ����
		 String sql="select * from user where username=? ";
	    pstat = conn.prepareStatement(sql);
	    //5��ִ��sql��䲢�õ����
	   pstat.setString(1, user.getUsername());
	  
	   rs=pstat.executeQuery();
	   // 6:�Խ�������д���
	    	 if(rs.next()){
	    		 flag=true;
	 	    	// out.println("��¼�ɹ�");
	 	    	// ת��ҳ��
	 	    	// request.getRequestDispatcher("success.jsp").forward(request, response);
	 	    	// �ض���
	 	    }
	    	 
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	
			      closeAll(conn,pstat,rs);
			} 
	    
	    	//7���ر�
		return flag;
		
	}
	public boolean add(User user){
		Connection conn=null;
		PreparedStatement pstat=null;
		int rs=0;
	    
	    try {
		
		//3:��������
		conn=getConnection();
		//4:����Statement sql���ִ����
		 String sql="insert into user(username,password) values(?,?)";
	    pstat = conn.prepareStatement(sql);
	    //5��ִ��sql��䲢�õ����
	   pstat.setString(1, user.getUsername());
	   pstat.setString(2, user.getPassword());
	   rs=pstat.executeUpdate();
	   // 6:�Խ�������д���
	    	 
	    	 
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	
			      closeAll(conn,pstat,null);
			} 
	    
	    	//7���ر�
		return rs>0;
		
	}

}

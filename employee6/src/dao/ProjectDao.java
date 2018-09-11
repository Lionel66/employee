package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import entity.Department;
import entity.Employee;
import entity.Project;

public class ProjectDao extends BaseDao {

	public List<Project> search() {
		List<Project> list = new ArrayList<Project>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4:建立Statement sql语句执行器
			stat = conn.createStatement();
			// 5：执行sql语句并得到结果
			String sql = "select * from project";
			rs = stat.executeQuery(sql);

			// 6:对结果集进行处理
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				
				// Department dep=new Department();
				// dep.setId(rs.getInt("d_id"));
				// dep.setName(rs.getString("dName"));
				// dep.setEmpCount(rs.getInt("emp_count"));
				// emp.setDep(dep);
				list.add(pro);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return list;
	}
	public List<Project> search(Project condition,int begin,int size) {
		List<Project> list=new ArrayList<Project>();
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8","root","123");
			
		    stat = conn.createStatement();
		    String where=" where 1=1 ";
		    if(condition.getName()!=null&&!condition.getName().equals("")) {
		    	where+="and name='"+condition.getName()+"'";
		    }
            
            
           
            String sql="select * from project "+where+" limit "+begin+","+size;
		     rs=stat.executeQuery(sql);
		   
		    while (rs.next()) {
		    	Project pro=new Project();
		    	pro.setId(rs.getInt("id"));
		    	pro.setName(rs.getString("name"));
		    
		    	list.add(pro);
		    }
		    	
		    	
		    
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn,stat,rs);
		}
		
		return list;
	}
	public int searchCount() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		int count=0;
		try {
			conn = getConnection();
			// 4:建立Statement sql语句执行器
			stat = conn.createStatement();
			// 5：执行sql语句并得到结果
			String sql = "select count(*)s from project";
			rs = stat.executeQuery(sql);

			// 6:对结果集进行处理
			if(rs.next()) {
				count=rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return count;
	}
	public int searchCount(Project condition) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		int count=0;
		try {
			conn = getConnection();
			// 4:建立Statement sql语句执行器
			stat = conn.createStatement();
			// 5：执行sql语句并得到结果
			 String where=" where 1=1 ";
			    if(condition.getName()!=null&&!condition.getName().equals("")) {
			    	where+="and name='"+condition.getName()+"'";
			    }
	           
			String sql = "select count(*)s from project"+where;
			rs = stat.executeQuery(sql);

			// 6:对结果集进行处理
			if(rs.next()) {
				count=rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return count;
	}
	public List<Project> search(int begin,int size) {
		List<Project> list = new ArrayList<Project>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4:建立Statement sql语句执行器
			stat = conn.createStatement();
			// 5：执行sql语句并得到结果
			String sql = "select * from project limit "+begin+","+size;
			
			rs = stat.executeQuery(sql);

			// 6:对结果集进行处理
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				
				// Department dep=new Department();
				// dep.setId(rs.getInt("d_id"));
				// dep.setName(rs.getString("dName"));
				// dep.setEmpCount(rs.getInt("emp_count"));
				// emp.setDep(dep);
				list.add(pro);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return list;
	}

	public Project search(int id) {
		Project pro = new Project();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4:建立Statement sql语句执行器
			stat = conn.createStatement();
			// 5：执行sql语句并得到结果
			String sql = "select * from project where id=" + id;
			rs = stat.executeQuery(sql);

			// 6:对结果集进行处理
			while (rs.next()) {
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
			
				// Department dep=new Department();
				// dep.setId(rs.getInt("d_id"));
				// dep.setName(rs.getString("dName"));
				// dep.setEmpCount(rs.getInt("emp_count"));
				// emp.setDep(dep);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return pro;
	}

	public List<Project> search(String ids) {
		List<Project> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4:建立Statement sql语句执行器
			stat = conn.createStatement();
			String sql = "select * from project where id in(" + ids + ")";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
				// Department dep=new Department();
				// dep.setId(rs.getInt("d_id"));
				// dep.setName(rs.getString("dName"));
				// dep.setEmpCount(rs.getInt("emp_count"));
				// emp.setDep(dep);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return list;
	}

	public boolean add(Project pro) {
		int rs = 0;

		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConnection();
			// 4:建立Statement sql语句执行器
			// 5：执行sql语句并得到结果
			String sql = "insert into project(name) values(?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pro.getName());
	
			rs = pstat.executeUpdate();

			// 6:对结果集进行处理

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
		return rs > 0;
	}

	public boolean update(Project pro) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConnection();
			String sql = "update project set name=? where id=?";
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, pro.getName());
			
			// pstat.setInt(4,emp.getDep().getId());
			pstat.setInt(2, pro.getId());
			int rs = pstat.executeUpdate();

			// 6:对结果集进行处理
			if (rs > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
		return flag;
	}

	public boolean delete(int id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConnection();
			String sql = "delete from project where id=?";
			pstat = conn.prepareStatement(sql);

			pstat.setInt(1, id);
			int rs = pstat.executeUpdate();
			// 6:对结果集进行处理
			if (rs > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
		return flag;
	}






	



}

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

public class DepartmentDao extends BaseDao {

	public List<Department> search() {
		List<Department> list = new ArrayList<Department>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4:建立Statement sql语句执行器
			stat = conn.createStatement();
			// 5：执行sql语句并得到结果
			String sql = "select * from department";
			rs = stat.executeQuery(sql);

			// 6:对结果集进行处理
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmpCount(rs.getInt("emp_count"));
				// Department dep=new Department();
				// dep.setId(rs.getInt("d_id"));
				// dep.setName(rs.getString("dName"));
				// dep.setEmpCount(rs.getInt("emp_count"));
				// emp.setDep(dep);
				list.add(dep);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return list;
	}
	public List<Department> search(Department condition,int begin,int size) {
		List<Department> list=new ArrayList<Department>();
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
            
            if(condition.getEmpCount()!=-1) {
            	where+="and ifnull(emp_count,0)="+condition.getEmpCount();
            }
           
            String sql="select * from department "+where+" limit "+begin+","+size;
		     rs=stat.executeQuery(sql);
		   
		    while (rs.next()) {
		    	Department dep=new Department();
		    	dep.setId(rs.getInt("id"));
		    	dep.setName(rs.getString("name"));
		    	dep.setEmpCount(rs.getInt("emp_count"));
		    	list.add(dep);
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
			String sql = "select count(*)s from department";
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
	public int searchCount(Department condition) {
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
	           
	            if(condition.getEmpCount()!=-1) {
	            	where+="and ifnull(emp_count,0)="+condition.getEmpCount();
	            }
			String sql = "select count(*)s from department"+where;
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
//	public List<Department> search(int begin,int size) {
//		List<Department> list = new ArrayList<Department>();
//		Connection conn = null;
//		Statement stat = null;
//		ResultSet rs = null;
//		try {
//			conn = getConnection();
//			// 4:建立Statement sql语句执行器
//			stat = conn.createStatement();
//			// 5：执行sql语句并得到结果
//			String sql = "select * from employee limit "+begin+","+size;
//			
//			rs = stat.executeQuery(sql);
//
//			// 6:对结果集进行处理
//			while (rs.next()) {
//				Department dep = new Department();
//				dep.setId(rs.getInt("id"));
//				dep.setName(rs.getString("name"));
//				dep.setEmpCount(rs.getInt("emp_Count"));
//				// Department dep=new Department();
//				// dep.setId(rs.getInt("d_id"));
//				// dep.setName(rs.getString("dName"));
//				// dep.setEmpCount(rs.getInt("emp_count"));
//				// emp.setDep(dep);
//				list.add(dep);
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			closeAll(conn, stat, rs);
//		}
//
//		return list;
//	}

	public Department search(int id) {
		Department dep = new Department();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4:建立Statement sql语句执行器
			stat = conn.createStatement();
			// 5：执行sql语句并得到结果
			String sql = "select * from department where id=" + id;
			rs = stat.executeQuery(sql);

			// 6:对结果集进行处理
			while (rs.next()) {
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmpCount(rs.getInt("emp_count"));
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

		return dep;
	}

	public List<Department> search(String ids) {
		List<Department> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4:建立Statement sql语句执行器
			stat = conn.createStatement();
			String sql = "select * from department where id in(" + ids + ")";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmpCount(rs.getInt("emp_count"));
				list.add(dep);
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

	public boolean add(Department dep) {
		int rs = 0;

		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConnection();
			// 4:建立Statement sql语句执行器
			// 5：执行sql语句并得到结果
			String sql = "insert into department(name,emp_Count)values(?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dep.getName());
			pstat.setInt(2, dep.getEmpCount());
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

	public boolean update(Department dep) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConnection();
			String sql = "update department set name=?,emp_count=? where id=?";
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dep.getName());
			pstat.setInt(2, dep.getEmpCount());
			// pstat.setInt(4,emp.getDep().getId());
			pstat.setInt(3, dep.getId());
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
			// Class.forName("com.mysql.jdbc.Driver");
			// 3:建立连接
			// Connection
			// conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8","root","123");
			// 4:建立Statement sql语句执行器
			// Statement stat = conn.createStatement();
			// 5：执行sql语句并得到结果
			String sql = "delete from department where id=?";
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

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
import java.util.Set;

import javax.naming.spi.DirStateFactory.Result;

import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;
import util.Grade;

public class ScoreDao extends BaseDao {

	public List<Score> search() {
		List<Score> list=new ArrayList<Score>();
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		try {
			conn = getConnection();
			//4:建立Statement sql语句执行器
		    stat = conn.createStatement();
		    //5：执行sql语句并得到结果
		     rs=stat.executeQuery("select * from v_emp_sc");
		   // 6:对结果集进行处理
		    while (rs.next()) {
		    	Score sc=new Score();
		    	sc.setId(rs.getInt("sc_id"));
		    	Employee emp=new Employee();
		    	emp.setId(rs.getInt("e_id"));
		    	emp.setName(rs.getString("e_name"));
		    	Department dep=new Department();
		    	dep.setId(rs.getInt("d_id"));
		    	dep.setName(rs.getString("d_name"));
		    	emp.setDep(dep);
		    	sc.setEmp(emp);
		    	Project pro=new Project();
		    	pro.setId(rs.getInt("p_id"));
		    	pro.setName(rs.getString("p_name"));
		    	
		    	sc.setPro(pro);
		    	sc.setValue((Integer)rs.getObject("value"));
		    	Grade g=Grade.getGrade(rs.getString("grade"));
		    	
		    	sc.setGrade(g);
		    	
		    	list.add(sc);
		    }
		    	
		    	
		    
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn,stat,rs);
		}
		
		return list;
	}
	
		public List<Score> search(Score condition,int begin,int size){
			List<Score> list=new ArrayList<Score>();
			Connection conn=null;
			Statement stat=null;
			ResultSet rs=null;
			int value=-1;
			try {
				conn = getConnection();
				//4:建立Statement sql语句执行器
			    stat = conn.createStatement();
			    String where=" where 1=1 ";
			    if(condition.getEmp().getName()!=null&&!condition.getEmp().getName().equals("")) {
			    	where+=" and e_name='"+condition.getEmp().getName()+"'";
			    }
			    if(condition.getEmp().getDep().getId() != null && condition.getEmp().getDep().getId() != -1) {
			    	where+=" and d_id="+condition.getEmp().getDep().getId();
			    }
			    if(condition.getPro().getId()!= null && condition.getPro().getId() != -1) {
			    	where+=" and p_id="+condition.getPro().getId();
			    }
			    if(condition.getValue()!=-1) {
			    	where+=" and value="+condition.getValue()+"";
			    }
			    if(condition.getGrade().getValue()!= null && !condition.getGrade().getValue().equals("")) {
			    	where+= " and grade='"+condition.getGrade().getValue()+"'";
			    }
			    //5：执行sql语句并得到结果
			    String sql=" select * from v_emp_sc"+ where + " limit " + begin + "," + size;
			    
			     rs=stat.executeQuery(sql);
			   // 6:对结果集进行处理
			    while (rs.next()) {
			    	Score sc=new Score();
			    	sc.setId(rs.getInt("sc_id"));
			    	Employee emp=new Employee();
			    	emp.setName(rs.getString("e_name"));
			    	emp.setId(rs.getInt("e_id"));
			    	sc.setEmp(emp);
			    	Department dep=new Department();
			    	dep.setName(rs.getString("d_name"));
			    	dep.setId(rs.getInt("d_id"));
			    	emp.setDep(dep);
			    	Project pro=new Project();
			    	pro.setName(rs.getString("p_name"));
			    	pro.setId(rs.getInt("p_id"));
			    	sc.setPro(pro);
			    	sc.setValue((Integer)rs.getObject("value"));
			    	Grade g=Grade.getGrade(rs.getString("grade"));
			    	sc.setGrade(g);
			    	
			    	list.add(sc);
			    }
			    	
			    	
			    
			}  catch (SQLException e) {
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
			int count = 0;
			try {
				conn = getConnection();
				// 4:建立Statement sql语句执行器
				stat = conn.createStatement();
				// 5：执行sql语句并得到结果
				String sql = "select count(*) from v_emp_sc";
				rs = stat.executeQuery(sql);

				// 6:对结果集进行处理
				if (rs.next()) {
					count = rs.getInt(1);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeAll(conn, stat, rs);
			}

			return count;
		}
		public int searchCount(Score condition){
			List<Score> list=new ArrayList<Score>();
			Connection conn=null;
			Statement stat=null;
			ResultSet rs=null;
			int count = 0;
			
			try {
				conn = getConnection();
				//4:建立Statement sql语句执行器
			    stat = conn.createStatement();
			    String where=" where 1=1 ";
			    if(condition.getEmp().getName()!=null&&!condition.getEmp().getName().equals("")) {
			    	where+=" and e_name='"+condition.getEmp().getName()+"'";
			    } 
			    if(condition.getEmp().getDep().getId() != null && condition.getEmp().getDep().getId() != -1) {
			    	where+=" and d_id="+condition.getEmp().getDep().getId();
			    }
			    if(condition.getPro().getId()!= null && condition.getPro().getId() != -1) {
			    	where+=" and p_id="+condition.getPro().getId();
			    }
			    if(condition.getValue()!=-1) {
			    	where+=" and value="+condition.getValue()+"";
			    }
			    if(condition.getGrade().getValue() != null && !condition.getGrade().getValue().equals("")) {
			    	where+=" and grade='"+condition.getGrade().getValue()+"'";
			    }
			    String sql=" select count(*) from v_emp_sc "+where;
			    
			     rs=stat.executeQuery(sql);
			     
			     if (rs.next()) {
						count = rs.getInt(1);
					}
			    	
			    	
			    
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeAll(conn,stat,rs);
			}
			
			return count;
		}
			
			
		public List<Score> search(int begin ,int size) {
			List<Score> list=new ArrayList<Score>();
			Connection conn=null;
			Statement stat=null;
			ResultSet rs=null;
			try {
				conn = getConnection();
				//4:建立Statement sql语句执行器
			    stat = conn.createStatement();
			    //5：执行sql语句并得到结果
			     rs=stat.executeQuery(" select * from v_emp_sc" + begin + "," + size);
			   // 6:对结果集进行处理
			    while (rs.next()) {
			    	Score sc=new Score();
			    	sc.setId(rs.getInt("sc_id"));
			    	Employee emp=new Employee();
			    	emp.setId(rs.getInt("e_id"));
			    	emp.setName(rs.getString("e_name"));
			    	Department dep=new Department();
			    	dep.setId(rs.getInt("d_id"));
			    	dep.setName(rs.getString("d_name"));
			    	emp.setDep(dep);
			    	sc.setEmp(emp);
			    	Project pro=new Project();
			    	pro.setId(rs.getInt("p_id"));
			    	pro.setName(rs.getString("p_name"));
			    	
			    	sc.setPro(pro);
			    	sc.setValue((Integer)rs.getObject("value"));
			    	Grade g=Grade.getGrade(rs.getString("grade"));
			    	
			    	sc.setGrade(g);
			    	
			    	list.add(sc);
			    }
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeAll(conn,stat,rs);
			}
			
			return list;
		}
		
		public Score search(int id) {
			Score sc=new Score();
			List<Score> list=new ArrayList<Score>();
			Connection conn=null;
			Statement stat=null;
			ResultSet rs=null;
			try {
				conn = getConnection();
				//4:建立Statement sql语句执行器
			    stat = conn.createStatement();
			    //5：执行sql语句并得到结果
			     rs=stat.executeQuery("select * from v_emp_sc where sc_id=" + id);
			   // 6:对结果集进行处理
			    while (rs.next()) {
			    	
			    	sc.setId(rs.getInt("sc_id"));
			    	Employee emp=new Employee();
			    	emp.setId(rs.getInt("e_id"));
			    	emp.setName(rs.getString("e_name"));
			    	Department dep=new Department();
			    	dep.setId(rs.getInt("d_id"));
			    	dep.setName(rs.getString("d_name"));
			    	emp.setDep(dep);
			    	sc.setEmp(emp);
			    	Project pro=new Project();
			    	pro.setId(rs.getInt("p_id"));
			    	pro.setName(rs.getString("p_name"));
			    	
			    	sc.setPro(pro);
			    	sc.setValue((Integer)rs.getObject("value"));
			    	Grade g=Grade.getGrade(rs.getString("grade"));
			    	
			    	sc.setGrade(g);
			    	
			    	
			    }
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeAll(conn,stat,rs);
			}
			
			return sc;
		}
		
//		public boolean save(Set<Score> set) {
//			for(Score sc: set) {
//				if(sc.getId()==0) {
//					add(sc);
//				}else {
//					update(sc);
//				}
//			}
//			return false;
//			
//		}
		public int add(Score sc) {
			
			Connection conn=null;
			PreparedStatement pstat=null;
			int rs=0;
			int id=0;
			try {
				conn = getConnection();
				
			    //5：执行sql语句并得到结果
			    String sql="insert into score(e_id,p_id,value) values (?,?,?)";
			  //4:建立Statement sql语句执行器
			    pstat = conn.prepareStatement(sql);
			    pstat.setInt(1, sc.getEmp().getId());
			    pstat.setInt(2, sc.getPro().getId());
			    pstat.setInt(3, sc.getValue());
			    rs=pstat.executeUpdate();
			    pstat.close();
			    sql="select last_insert_id()";	
			    pstat = conn.prepareStatement(sql);
			    ResultSet r=pstat.executeQuery();
			    if(r.next()) {
			    	id=r.getInt(1);
			    }
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeAll(conn,pstat,null);
			}
			
			return id;
		}
		
		
		
	   public boolean update(Score sc) {
			
			Connection conn=null;
			PreparedStatement pstat=null;
			int rs=0;
			try {
				conn = getConnection();
				
			    //5：执行sql语句并得到结果
			    String sql="update score set value=? where id=?";
			  //4:建立Statement sql语句执行器
			    pstat = conn.prepareStatement(sql);
			    pstat.setInt(1,sc.getValue());
			    pstat.setInt(2,sc.getId());
			    rs=pstat.executeUpdate();
			    	
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeAll(conn,pstat,null);
			}
			
			return rs>0;
		}

		}



package entity;

import java.io.Serializable;

public class Employee implements Serializable {
	private int department;
	private int id;
	private String name;
	private String sex;
	private int age;
	private Department dep;
	private String photo;
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Department getDep() {
		return dep;
	}
	public void setDep(Department dep) {
		this.dep = dep;
	}
	public int getDepartment() {
		return department;
	}
	public void setDepartment(int deparment) {
		this.department = deparment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		
			this.sex = sex;
		}
		
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}

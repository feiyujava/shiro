package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "permission_t")
public class Permission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5913305396957355753L;
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	@ManyToMany
    @JoinTable(name = "role_permission_t",joinColumns= {@JoinColumn(name="pid")},
    	inverseJoinColumns = {@JoinColumn(name = "rid")})
	private List<Role> roles;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", roles=" + roles + "]";
	}
	
	
}

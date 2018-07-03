package org.saravana.hibernate.hib.dto.two;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class EntityOne {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	@OneToMany
	@JoinColumn(name="foreign_key")
	private List<EntityChild> childList=new ArrayList<EntityChild>();
	
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
	public List<EntityChild> getChild() {
		return childList;
	}
	public void setChild(List<EntityChild> child) {
		this.childList = child;
	}

	

}

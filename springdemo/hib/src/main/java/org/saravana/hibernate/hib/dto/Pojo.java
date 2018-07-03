package org.saravana.hibernate.hib.dto;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@NamedQuery(name="Pojo.byId",query="select fieldA from Pojo where id=:id")
@NamedNativeQuery(name="Pojo.byName",query="select * from pojo where fielda=?",resultClass=Pojo.class)
public class Pojo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String fieldA;

	public String getFieldA() {
		return fieldA;
	}

	public void setFieldA(String fieldA) {
		this.fieldA = fieldA;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

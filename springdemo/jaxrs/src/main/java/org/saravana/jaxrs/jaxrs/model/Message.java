package org.saravana.jaxrs.jaxrs.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String message;
	private Date date;
	private String author;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="message_key")
	private Map<Long, Comment> comments=new HashMap<>();
	@Transient
	private List<Link> links=new ArrayList<>();
	
	public Message() {
		this.date = new Date();
	}
	public Message(long id, String message, String author) {
		super();
		this.id = id;
		this.message = message;
		this.date = new Date();
		this.author = author;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@XmlTransient
	public Map<Long, Comment> getComments() {
		return comments;
	}
	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public void addLink(String url, String rel){
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	}
	
	public Comment getComment(long id){
		Map<Long,Comment> commentMap=getComments();
		for(Entry<Long, Comment> entry:commentMap.entrySet()){
			if(((Comment)entry.getValue()).getId()==id){
				return (Comment)entry.getValue();
			}
		}
		return null;
	}
}

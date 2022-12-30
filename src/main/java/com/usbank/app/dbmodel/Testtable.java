package com.usbank.app.dbmodel;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Testtable {
	@PrimaryKey 
	public int points;
	
	public String category;
	public String lastname;
	
	//public int  UUID;
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
//	public int getUUID() {
//		return UUID;
//	}
//	public void setUUID(int uUID) {
//		UUID = uUID;
//	}
	public Testtable(int points, String category, String lastname) {
		super();
		this.points = points;
		this.category = category;
		this.lastname = lastname;
		//UUID = uUID;
	}
	
	public Testtable() {
		
	}
	
	
}

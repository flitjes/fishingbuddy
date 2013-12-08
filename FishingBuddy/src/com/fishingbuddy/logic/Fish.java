package com.fishingbuddy.logic;

public class Fish {
	String name;
	String description;
	int db_fish_id;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDb_Fish_Id() {
		return db_fish_id;
	}
	public void setDb_Fish_Id(int id) {
		this.db_fish_id = id;
	}
	public Fish(String name) {
		this.name = name;
	}
	public Fish(String name, String description) {
		this.name = name;
		this.description = description;
	}
	public Fish(int id, String name, String description) {
		this.name = name;
		this.description = description;
		this.db_fish_id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}

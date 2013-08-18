package com.fishingbuddy.logic;

import android.graphics.Bitmap;
import android.location.Location;

public class Swim {
	private String name = null;
	private Location location = null;
	private String description = null;
	private Bitmap picture = null;

	public Swim(String name, Location location, String description) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.location = location;
		this.description = description;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Bitmap getPicture() {
		return picture;
	}

	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}

}

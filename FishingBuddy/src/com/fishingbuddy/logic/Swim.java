package com.fishingbuddy.logic;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;

public class Swim {
	private String name = null;
	private LatLng location = null;
	private String description = null;
	private Bitmap picture = null;

	public Swim(String name, LatLng location, String description) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.location = location;
		this.description = description;		
	}
	public void UpdateSwim(String name,LatLng location, String description){
		if(name != null)
			this.name = name;
		if(location != null)
			this.location =location;
		if(description != null)
			this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LatLng getLocation() {
		return location;
	}

	public void setLocation(LatLng location) {
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

package com.fishingbuddy.logic;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;

public class Swim {
	private int db_swim_id;
	private int db_fishingwater_id;
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
	
	public Swim(int fw_id,String name, LatLng location, String description) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.location = location;
		this.description = description;		
		setdbFishingwater_id(fw_id);
	}
	
	/*Database uses this constructor*/
	public Swim(int swim_id, int fw_id, String name, LatLng location, String description) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.location = location;
		this.description = description;		
		setdbSwim_id(swim_id);
		setdbFishingwater_id(fw_id);
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
	

	public int getdbSwim_id() {
		return db_swim_id;
	}

	public void setdbSwim_id(int swim_id) {
		this.db_swim_id = swim_id;
	}

	public int getdbFishingwater_id() {
		return db_fishingwater_id;
	}

	public void setdbFishingwater_id(int fishingwater_id) {
		this.db_fishingwater_id = fishingwater_id;
	}
	

}

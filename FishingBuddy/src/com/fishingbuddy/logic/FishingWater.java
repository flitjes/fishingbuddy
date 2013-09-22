package com.fishingbuddy.logic;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class FishingWater {
	private int db_fishingwater_id;/*DB id*/
	private String name = null;
	private LatLng location = null;
	private List<Swim> swim = null;
	private String description = null;
	public List<Swim> getSwim() {
		return swim;
	}

	public void setSwim(List<Swim> swim) {
		this.swim = swim;
	}

	public List<Fish> getFish() {
		return fish;
	}

	public void setFish(List<Fish> fish) {
		this.fish = fish;
	}

	private List<Fish> fish = null;
	
	public FishingWater(String name, LatLng location,String description) {
		// TODO Auto-generated constructor stub
		this.setName(name);
		this.setLocation(location);
		this.setDescription(description);
		swim = new ArrayList<Swim>();
		fish = new ArrayList<Fish>();
	}
	/*Database uses this constructor*/
	public FishingWater(int id, String name, LatLng location,String description) {
		// TODO Auto-generated constructor stub
		this.setName(name);
		this.setLocation(location);
		this.setDescription(description);
		this.setId(id);
		swim = new ArrayList<Swim>();
		fish = new ArrayList<Fish>();
	}
	
	public int CreateSwim(String name, LatLng location, String description){
		Swim c_swim;
		c_swim = new Swim(name, location, description);
		swim.add(c_swim);
		return  swim.size() - 1;
	}
	public void UpdateFishingWater(String name, String description,LatLng location){
		if(name != null)
			this.name = name;
		if(description != null)
			this.description = description;
		if(location != null)
			this.location = location;
	}
	
	public void UpdateSwim(int index, String name, String description,LatLng location){
		getSwim().get(index).UpdateSwim(name, location, description);
	}
	
	public boolean AddFish(Fish fish)
	{
		return this.fish.add(fish);
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

	public int getId() {
		return db_fishingwater_id;
	}

	public void setId(int id) {
		this.db_fishingwater_id = id;
	}
	
}

package com.fishingbuddy.logic;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;

public class FishingWater {
	private String name = null;
	private Location location = null;
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
	
	public FishingWater(String name, Location location,String description) {
		// TODO Auto-generated constructor stub
		this.setName(name);
		this.setLocation(location);
		this.setDescription(description);
		swim = new ArrayList<Swim>();
		fish = new ArrayList<Fish>();
	}
	
	public int CreateSwim(String name, Location location, String description){
		Swim c_swim;
		c_swim = new Swim(name, location, description);
		swim.add(c_swim);
		return  swim.size() - 1;
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

}

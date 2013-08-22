package com.fishingbuddy.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

import android.app.Application;
import android.location.Location;

public class FishingManager extends Application{	
	private List<FishingWater> fishingwater = new ArrayList<FishingWater>();
	private static Fisherman fisherman = null;
	private List<Fish> all_known_fish = new ArrayList<Fish>();
	private GPSManager gpsm = new GPSManager(this);
	public FishingManager() {
		/*Get all known fish*/
		fillListWithFish();
	}
	
	public boolean CreateFisherman(String name, Date birthday){
		fisherman = new Fisherman(name, birthday);
		if(fisherman == null)
			return false;
		return true;
	}
	
	public LatLng CurrentPosition(){		
		return gpsm.getLocation();
	}
	public boolean CreateFishingWater(String name, LatLng loc,String description){
		return fishingwater.add(new FishingWater(name,loc,description));
	}
	public boolean CreateFishingWater(String name, String description){
		return fishingwater.add(new FishingWater(name,gpsm.getLocation(),description));
	}
	
	public boolean DeleteFishingWater(int fishingwater_index){
		fishingwater.remove(fishingwater_index);
		return true;
	}
	
	public int CreateSwimForFishingWater(int fishinwater_index, String name, String description){
		if(getFishingwater().size() != fishinwater_index)
			return 0;
		int index = getFishingwater().get(fishinwater_index).CreateSwim(name, gpsm.getLocation(), description);		
		return index;
	}
	
	public int CreateSwimForFishingWater(int fishinwater_index, String name, LatLng location, String description){
		int index = getFishingwater().get(fishinwater_index).CreateSwim(name, location, description);		
		return index;
	}
	
	public boolean DeleteSwimFromFishingWater(int fishingwater_index, int swim_index){
		getFishingwater().get(fishingwater_index).getSwim().remove(swim_index);
		return true;
	}
	
	public List<FishingWater> getFishingwater() {
		return fishingwater;
	}
	public void setFishingwater(List<FishingWater> fishingwater) {
		this.fishingwater = fishingwater;
	}
	private boolean fillListWithFish()
	{
		/*Todo: Get a list with all fish from somehwere*/
		boolean done = false;
		setAll_known_fish(null);
		return done;
	}

	public List<Fish> getAll_known_fish() {
		return all_known_fish;
	}

	public void setAll_known_fish(List<Fish> all_known_fish) {
		this.all_known_fish = all_known_fish;
	}
}

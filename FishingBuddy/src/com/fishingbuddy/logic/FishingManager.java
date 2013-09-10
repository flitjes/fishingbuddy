package com.fishingbuddy.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Application;

import com.fishingbuddy.logic.Gear.Gear;
import com.google.android.gms.maps.model.LatLng;

public class FishingManager extends Application{	
	private List<FishingWater> fishingwater = new ArrayList<FishingWater>();
	private static Fisherman fisherman = null;
	private List<Fish> all_known_fish = new ArrayList<Fish>();
	private GPSManager gpsm = new GPSManager(this);	
	private List<Catch> catches = new ArrayList<Catch>();
	private Gear gear = new Gear();
	
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
	
	public void CreateCatch(Catch c)
	{
		catches.add(c);
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
	public void UpdateFishingWater(int fishingwater_index, String name, String description, LatLng location){
		getFishingwater().get(fishingwater_index).UpdateFishingWater(name, description, location);
	}
	public void UpdateSwimForFishingWater(int fishinwater_index, int swim_index, String name, String description,LatLng location){
		getFishingwater().get(fishinwater_index).UpdateSwim(swim_index, name, description, location);
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
		Fish f = new Fish("Carp");		
		all_known_fish.add(f);
		f = new Fish("Mirror Carp");
		all_known_fish.add(f);
		f = new Fish("Grass Carp");
		all_known_fish.add(f);
		return done;
	}

	public List<Fish> getAll_known_fish() {
		return all_known_fish;
	}

	public void setAll_known_fish(List<Fish> all_known_fish) {
		this.all_known_fish = all_known_fish;
	}

	public List<Catch> getCatches() {
		return catches;
	}

	public void setCatches(List<Catch> catches) {
		this.catches = catches;
	}

	public Gear getGear() {
		return gear;
	}

	public void setGear(Gear gear) {
		this.gear = gear;
	}
	
	
}

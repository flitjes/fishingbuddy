package com.fishingbuddy.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fishingbuddy.logic.Gear.Gear;
import com.fishingbuddy.logic.storage.FishingBuddyOpenHelper;
import com.google.android.gms.internal.db;
import com.google.android.gms.internal.fd;
import com.google.android.gms.maps.model.LatLng;

public class FishingManager extends Application{	
	private List<FishingWater> fishingwater = new ArrayList<FishingWater>();
	private static Fisherman fisherman = null;	
	private GPSManager gpsm = new GPSManager(this);	
	private List<Catch> catches = new ArrayList<Catch>();
	private Gear gear = new Gear();
	private FishingBuddyOpenHelper fbdbhelper;	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		fbdbhelper = new FishingBuddyOpenHelper(getApplicationContext());			
		Update();
	}
	public FishingManager() {		
		/*Get all known fish*/
		//fillListWithFish();
		
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
		FishingWater fw = new FishingWater(name,loc,description);
		fbdbhelper.getTbfw().addFishingWater(fw);
		Update();
		return true;
	}
	public boolean CreateFishingWaterCurrentLocation(String name, String description){
		FishingWater fw = new FishingWater(name,gpsm.getLocation(),description);
		fbdbhelper.getTbfw().addFishingWater(fw);
		Update();
		return true;
	}
	
	public boolean DeleteFishingWater(int fishingwater_index){
		//fishingwater.remove(fishingwater_index);
		fbdbhelper.deleteFwWithSwims(fishingwater.get(fishingwater_index));
		Update();
		return true;
	}
	public void UpdateFishingWater(int fishingwater_index, String name, String description, LatLng location){
		getFishingwater().get(fishingwater_index).UpdateFishingWater(name, description, location);
		fbdbhelper.getTbfw().updateFishingWater(getFishingwater().get(fishingwater_index));
		Update();
	}
	public void UpdateSwimForFishingWater(int fishinwater_index, int swim_index, String name, String description,LatLng location){
		getFishingwater().get(fishinwater_index).UpdateSwim(swim_index, name, description, location);
		fbdbhelper.getTbsw().updateSwim(getFishingwater().get(fishinwater_index).getSwim().get(swim_index));
		Update();
	}
	
	public int CreateSwimForFishingWater(int fishinwater_index, String name, String description){
		if(getFishingwater().size() != fishinwater_index)
			return 0;
		int index = getFishingwater().get(fishinwater_index).CreateSwim(name, gpsm.getLocation(), description);		
		fbdbhelper.getTbsw().addSwim(new Swim(getFishingwater().get(fishinwater_index).getId(), name, gpsm.getLocation(), description));
		Update();
		return index;
	}
	
	public int CreateSwimForFishingWater(int fishinwater_index, String name, LatLng location, String description){
		int index = getFishingwater().get(fishinwater_index).CreateSwim(name, location, description);	
		fbdbhelper.getTbsw().addSwim(new Swim(getFishingwater().get(fishinwater_index).getId(), name, location, description));
		Update();
		return index;
	}
	
	public boolean DeleteSwimFromFishingWater(int fishingwater_index, int swim_index){
		//getFishingwater().get(fishingwater_index).getSwim().remove(swim_index);
		fbdbhelper.getTbsw().deleteSwim(getFishingwater().get(fishingwater_index).getSwim().get(swim_index));
		Update();
		return true;
	}
	public void CreateFish(String name, String description){
		Fish f = new Fish(name,description);
		fbdbhelper.getTbf().addSwim(f);
	}
	
	public List<FishingWater> getFishingwater() {
		return fishingwater;
	}
	public void setFishingwater(List<FishingWater> fishingwater) {
		this.fishingwater = fishingwater;
	}
	
	private boolean fillListWithFish()
	{		
		CreateFish("Carp","");		
		CreateFish("Grass Carp","");
		CreateFish("Mirror Carp","");		
		return true;
	}

	public List<Fish> getAll_known_fish() {
		ArrayList<Fish> allFish = fbdbhelper.getTbf().getFish();
		if(allFish.size() == 0){
			fillListWithFish();
			allFish = fbdbhelper.getTbf().getFish();
		}
		
		return allFish;
	}

	/*public void setAll_known_fish(List<Fish> all_known_fish) {
		this.all_known_fish = all_known_fish;
	}*/

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
	private void Update(){
		fishingwater = fbdbhelper.getTbfw().getFishingWaters();	
		for(FishingWater fw: fishingwater){
			fw.setSwim(fbdbhelper.getTbsw().getSwimForFishingWater(fw.getId()));
		}
	}
	
}

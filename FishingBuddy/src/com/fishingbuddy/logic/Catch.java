package com.fishingbuddy.logic;

import java.util.List;

import android.graphics.Bitmap;

import com.fishingbuddy.logic.Gear.Bait;
import com.fishingbuddy.logic.Gear.HookBait;
import com.fishingbuddy.logic.Gear.Rig;
import com.fishingbuddy.logic.Weather.Model.Weather;

public class Catch {
	private Fish fish;	
	private Swim swim;
	private String description;
	private Bitmap image;
	private List<Bait> bait;
	private HookBait hookbait;
	private Rig rig;
	private Weather weather;
	
	public Catch(Fish fish, Swim swim, String description, Bitmap image,
			List<Bait> bait, HookBait hookbait, Rig rig, Weather weather) {
		super();
		this.fish = fish;
		this.swim = swim;
		this.description = description;
		this.image = image;
		this.bait = bait;
		this.hookbait = hookbait;
		this.rig = rig;
		this.weather = weather;
	}

	public Fish getFish() {
		return fish;
	}

	public void setFish(Fish fish) {
		this.fish = fish;
	}

	public Swim getSwim() {
		return swim;
	}

	public void setSwim(Swim swim) {
		this.swim = swim;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public List<Bait> getBait() {
		return bait;
	}

	public void setBait(List<Bait> bait) {
		this.bait = bait;
	}

	public HookBait getHookbait() {
		return hookbait;
	}

	public void setHookbait(HookBait hookbait) {
		this.hookbait = hookbait;
	}

	public Rig getRig() {
		return rig;
	}

	public void setRig(Rig rig) {
		this.rig = rig;
	}
}

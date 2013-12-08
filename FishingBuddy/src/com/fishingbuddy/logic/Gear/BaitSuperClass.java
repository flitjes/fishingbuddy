package com.fishingbuddy.logic.Gear;

public class BaitSuperClass {
	public enum BAITTYPE{Particle,Boilie,Pellet,POPUP, PLASTIC, Plug, Spinner, Shad};
	BAITTYPE bait_type;	
	String name;
	int size;
	public BaitSuperClass(BAITTYPE bait_type, String name, int size) {
		super();
		this.bait_type = bait_type;
		this.name = name;
		this.size = size;
	}
	public BAITTYPE getBait_type() {
		return bait_type;
	}
	public void setBait_type(BAITTYPE bait_type) {
		this.bait_type = bait_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}

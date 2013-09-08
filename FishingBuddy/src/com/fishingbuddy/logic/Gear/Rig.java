package com.fishingbuddy.logic.Gear;

public class Rig {	
	private String name, hook;
	private int hook_size;
	public Rig(String name, String hook, int hook_size) {
		super();
		this.name = name;
		this.hook = hook;
		this.hook_size = hook_size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHook() {
		return hook;
	}
	public void setHook(String hook) {
		this.hook = hook;
	}
	public int getHook_size() {
		return hook_size;
	}
	public void setHook_size(int hook_size) {
		this.hook_size = hook_size;
	}
	
}

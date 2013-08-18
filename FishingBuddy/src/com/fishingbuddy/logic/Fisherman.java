package com.fishingbuddy.logic;

import java.util.Date;

import android.graphics.Bitmap;

public class Fisherman {
	String name;
	Date birthdate;
	Bitmap picture;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public Bitmap getPicture() {
		return picture;
	}
	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}
	public Fisherman(String name,Date birthdate){
		this.name = name;
		this.birthdate = birthdate;
	}

}

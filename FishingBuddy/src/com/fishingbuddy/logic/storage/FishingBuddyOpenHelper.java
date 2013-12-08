package com.fishingbuddy.logic.storage;

import java.util.ArrayList;

import com.fishingbuddy.logic.FishingWater;
import com.fishingbuddy.logic.Swim;
import com.google.android.gms.maps.model.LatLng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FishingBuddyOpenHelper extends SQLiteOpenHelper{
	public static final int DATABASE_VERSION = 1;
	// Database Name
	public static final String DATABASE_NAME = "FishingMangager";

	public static final String FISHINGWATER_TABLE_NAME = "FISHINGWATER";
	public static final String SWIM_TABLE_NAME = "SWIM";
	public static final String FISH_TABLE_NAME = "FISH";

	private TableFishingWaters tbfw;
	private TableSwims tbsw;
	private TableFish tbf;

	public FishingBuddyOpenHelper(Context context) {
		super(context, DATABASE_NAME, null,
				DATABASE_VERSION);
		setTbfw(new TableFishingWaters(getWritableDatabase()));
		setTbsw(new TableSwims(getWritableDatabase()));
		setTbf(new TableFish(getWritableDatabase()));
	}
	public TableFish getTbf() {
		return tbf;
	}

	public void setTbf(TableFish tbf) {
		this.tbf = tbf;
	}

	
	public TableFishingWaters getTbfw() {
		return tbfw;
	}

	public void setTbfw(TableFishingWaters tbfw) {
		this.tbfw = tbfw;
	}

	public TableSwims getTbsw() {
		return tbsw;
	}

	public void setTbsw(TableSwims tbsw) {
		this.tbsw = tbsw;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TableFishingWaters.FISHINGWATER_TABLE_CREATE);
		db.execSQL(TableSwims.SWIM_TABLE_CREATE);
		db.execSQL(TableFish.FISH_TABLE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "
				+ FishingBuddyOpenHelper.FISHINGWATER_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS "
				+ FishingBuddyOpenHelper.SWIM_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS "
				+ FishingBuddyOpenHelper.FISH_TABLE_NAME);
	}
	public void deleteFwWithSwims(FishingWater fw){
		for(Swim sw : fw.getSwim()){
			getTbsw().deleteSwim(sw);
		}
		getTbfw().deleteFishingWater(fw);
	}
	
	
}

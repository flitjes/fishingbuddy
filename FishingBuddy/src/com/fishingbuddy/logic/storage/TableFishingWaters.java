package com.fishingbuddy.logic.storage;

import java.util.ArrayList;

import com.fishingbuddy.logic.FishingWater;
import com.fishingbuddy.logic.Swim;
import com.google.android.gms.maps.model.LatLng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TableFishingWaters {

	private static final String FW_ID_KEY = "id";
	private static final String FW_NAME_KEY = "NAME";
	private static final String FW_NAME_DESCRIPTION_KEY = "DESCRIPTION";
	private static final String FW_NAME_LOC_LAT_KEY = "LAT";
	private static final String FW_NAME_LOC_LONG_KEY = "LONG";
	private SQLiteDatabase db;

	public static final String FISHINGWATER_TABLE_CREATE = "CREATE TABLE "
			+ FishingBuddyOpenHelper.FISHINGWATER_TABLE_NAME + " (" + FW_ID_KEY
			+ " INTEGER PRIMARY KEY," + FW_NAME_KEY + " TEXT, "
			+ FW_NAME_DESCRIPTION_KEY + " TEXT," + FW_NAME_LOC_LAT_KEY
			+ " DOUBLE," + FW_NAME_LOC_LONG_KEY + " DOUBLE" + ");";

	public TableFishingWaters(SQLiteDatabase db){
		// TODO Auto-generated constructor stub
		this.db = db;
	}

	public void addFishingWater(FishingWater fw) {		

		ContentValues values = new ContentValues();
		values.put(FW_NAME_KEY, fw.getName());
		values.put(FW_NAME_DESCRIPTION_KEY, fw.getDescription());
		values.put(FW_NAME_LOC_LAT_KEY, fw.getLocation().latitude);
		values.put(FW_NAME_LOC_LONG_KEY, fw.getLocation().longitude);

		// Inserting Row
		db.insert(FishingBuddyOpenHelper.FISHINGWATER_TABLE_NAME, null, values);		
	}

	public FishingWater getFishingWater(int id) {	

		Cursor cursor = db.query(
				FishingBuddyOpenHelper.FISHINGWATER_TABLE_NAME, new String[] {
						FW_ID_KEY, FW_NAME_KEY, FW_NAME_LOC_LAT_KEY,
						FW_NAME_LOC_LONG_KEY, FW_NAME_DESCRIPTION_KEY },
				FW_ID_KEY + "=?", new String[] { String.valueOf(id) }, null,
				null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		FishingWater fw = new FishingWater(
				Integer.parseInt(cursor.getString(0)), cursor.getString(1),
				new LatLng(cursor.getDouble(2), cursor.getDouble(3)),
				cursor.getString(4));

		return fw;
	}
	public int updateFishingWater(FishingWater fw) {    
		 
		ContentValues values = new ContentValues();
		values.put(FW_NAME_KEY, fw.getName());
		values.put(FW_NAME_DESCRIPTION_KEY, fw.getDescription());
		values.put(FW_NAME_LOC_LAT_KEY, fw.getLocation().latitude);
		values.put(FW_NAME_LOC_LONG_KEY, fw.getLocation().longitude);
	    // updating row
	    return db.update(FishingBuddyOpenHelper.FISHINGWATER_TABLE_NAME, values, FW_ID_KEY + " = ?",
	            new String[] { String.valueOf(fw.getId()) });
	}
	public void deleteFishingWater(FishingWater fw){		
	    db.delete(FishingBuddyOpenHelper.FISHINGWATER_TABLE_NAME, FW_ID_KEY + " = ?",
	            new String[] { String.valueOf(fw.getId()) });	    
	}

	public ArrayList<FishingWater> getFishingWaters() {
		ArrayList<FishingWater> fws = new ArrayList<FishingWater>();
		// Select All Query
		String selectQuery = "SELECT  * FROM "
				+ FishingBuddyOpenHelper.FISHINGWATER_TABLE_NAME;
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				FishingWater fw = new FishingWater(Integer.parseInt(cursor
						.getString(0)), cursor.getString(1), new LatLng(
						cursor.getDouble(3), cursor.getDouble(4)),
						cursor.getString(2));
				if (fw != null)
					fws.add(fw);
			} while (cursor.moveToNext());
		}

		return fws;
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		db.close();
	}
	

	}

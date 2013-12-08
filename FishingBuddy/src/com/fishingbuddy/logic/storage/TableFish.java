package com.fishingbuddy.logic.storage;

import java.util.ArrayList;

import com.fishingbuddy.logic.Fish;
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

public class TableFish {

	private static final String FISH_ID_KEY = "id";
	private static final String FISH_NAME_KEY = "NAME";
	private static final String FISH_NAME_DESCRIPTION_KEY = "DESCRIPTION";
		
	private SQLiteDatabase db;

	public static final String FISH_TABLE_CREATE = "CREATE TABLE "
			+ FishingBuddyOpenHelper.FISH_TABLE_NAME + " (" + FISH_ID_KEY
			+ " INTEGER PRIMARY KEY," + FISH_NAME_KEY + " TEXT, "
			+ FISH_NAME_DESCRIPTION_KEY + ");";

	public TableFish(SQLiteDatabase db) {		
		// TODO Auto-generated constructor stub
		this.db = db;
	}

	public void addSwim(Fish fish) {	
		
		ContentValues values = new ContentValues();
		values.put(FISH_NAME_KEY, fish.getName());	
		values.put(FISH_NAME_DESCRIPTION_KEY, fish.getDescription());
		// Inserting Row
		db.insert(FishingBuddyOpenHelper.FISH_TABLE_NAME, null, values);		
	}
	
	public int updateSwim(Fish fish) {    
	 
		ContentValues values = new ContentValues();
		values.put(FISH_NAME_KEY, fish.getName());
		values.put(FISH_NAME_DESCRIPTION_KEY, fish.getDescription());

	    // updating row
	    return db.update(FishingBuddyOpenHelper.SWIM_TABLE_NAME, values, FISH_ID_KEY + " = ?",
	            new String[] { String.valueOf(fish.getDb_Fish_Id()) });
	}

	public ArrayList<Fish> getFish() {
		ArrayList<Fish> fishes = new ArrayList<Fish>();
		// Select All Query
		String selectQuery = "SELECT  * FROM "
				+ FishingBuddyOpenHelper.FISH_TABLE_NAME;
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Fish f = new Fish(Integer.parseInt(cursor
						.getString(0)), cursor.getString(1), cursor.getString(2));
				if (f != null)
					fishes.add(f);
			} while (cursor.moveToNext());
		}

		return fishes;
	}
	
	public void deleteSwim(Fish fish){		
	    db.delete(FishingBuddyOpenHelper.FISH_TABLE_NAME, FISH_ID_KEY + " = ?",
	            new String[] { String.valueOf(fish.getDb_Fish_Id()) });	    
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		db.close();
	}
	
}

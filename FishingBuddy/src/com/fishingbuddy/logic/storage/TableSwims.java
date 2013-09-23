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

public class TableSwims {

	private static final String SW_ID_KEY = "id";
	private static final String SW_NAME_KEY = "NAME";
	private static final String SW_NAME_DESCRIPTION_KEY = "DESCRIPTION";
	private static final String SW_NAME_LOC_LAT_KEY = "LAT";
	private static final String SW_NAME_LOC_LONG_KEY = "LONG";
	private static final String FW_ID_KEY = "FISHINGWATER_ID";
	private SQLiteDatabase db;

	public static final String SWIM_TABLE_CREATE = "CREATE TABLE "
			+ FishingBuddyOpenHelper.SWIM_TABLE_NAME + " (" + SW_ID_KEY
			+ " INTEGER PRIMARY KEY," + SW_NAME_KEY + " TEXT, "
			+ SW_NAME_DESCRIPTION_KEY + " TEXT," + SW_NAME_LOC_LAT_KEY
			+ " DOUBLE," + SW_NAME_LOC_LONG_KEY + " DOUBLE," + FW_ID_KEY
			+ " INT" + ");";

	public TableSwims(SQLiteDatabase db) {		
		// TODO Auto-generated constructor stub
		this.db = db;
	}

	public void addSwim(Swim sw) {	

		ContentValues values = new ContentValues();
		values.put(SW_NAME_KEY, sw.getName());
		values.put(SW_NAME_DESCRIPTION_KEY, sw.getDescription());
		values.put(SW_NAME_LOC_LAT_KEY, sw.getLocation().latitude);
		values.put(SW_NAME_LOC_LONG_KEY, sw.getLocation().longitude);
		values.put(FW_ID_KEY, sw.getdbFishingwater_id());

		// Inserting Row
		db.insert(FishingBuddyOpenHelper.SWIM_TABLE_NAME, null, values);		
	}
	
	public int updateSwim(Swim sw) {    
	 
		ContentValues values = new ContentValues();
		values.put(SW_NAME_KEY, sw.getName());
		values.put(SW_NAME_DESCRIPTION_KEY, sw.getDescription());
		values.put(SW_NAME_LOC_LAT_KEY, sw.getLocation().latitude);
		values.put(SW_NAME_LOC_LONG_KEY, sw.getLocation().longitude);
		values.put(FW_ID_KEY, sw.getdbFishingwater_id());
	    // updating row
	    return db.update(FishingBuddyOpenHelper.SWIM_TABLE_NAME, values, SW_ID_KEY + " = ?",
	            new String[] { String.valueOf(sw.getdbSwim_id()) });
	}

	public ArrayList<Swim> getSwimForFishingWater(int id) {
		ArrayList<Swim> sws = new ArrayList<Swim>();

		Cursor cursor = db.query(FishingBuddyOpenHelper.SWIM_TABLE_NAME,
				new String[] { SW_ID_KEY, SW_NAME_KEY,
						SW_NAME_LOC_LAT_KEY, SW_NAME_LOC_LONG_KEY,
						SW_NAME_DESCRIPTION_KEY, FW_ID_KEY }, FW_ID_KEY + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Swim sw = new Swim(Integer.parseInt(cursor.getString(0)),
						Integer.parseInt(cursor.getString(5)),
						cursor.getString(1), new LatLng(cursor.getDouble(2),
								cursor.getDouble(3)), cursor.getString(4));
				if (sw != null)
					sws.add(sw);
			} while (cursor.moveToNext());
		}

		return sws;
	}
	
	public void deleteSwim(Swim sw){		
	    db.delete(FishingBuddyOpenHelper.FISHINGWATER_TABLE_NAME, SW_ID_KEY + " = ?",
	            new String[] { String.valueOf(sw.getdbSwim_id()) });	    
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		db.close();
	}
	
}

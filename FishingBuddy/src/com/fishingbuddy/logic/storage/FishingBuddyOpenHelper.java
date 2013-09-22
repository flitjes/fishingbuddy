package com.fishingbuddy.logic.storage;

import java.util.ArrayList;

import com.fishingbuddy.logic.FishingWater;
import com.google.android.gms.maps.model.LatLng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FishingBuddyOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
 // Database Name
    private static final String DATABASE_NAME = "FishingMangager";
    private static final String FISHINGWATER_TABLE_NAME = "FISHINGWATER";
    private static final String SWIM_TABLE_NAME = "SWIM";
    
    private static final String FW_ID_KEY = "id";
    private static final String FW_NAME_KEY = "NAME";
    private static final String FW_NAME_DESCRIPTION_KEY = "DESCRIPTION";
    private static final String FW_NAME_LOC_LAT_KEY = "LAT";
    private static final String FW_NAME_LOC_LONG_KEY = "LONG";
    
    private static final String FISHINGWATER_TABLE_CREATE =
                "CREATE TABLE " + FISHINGWATER_TABLE_NAME + " (" +
                		FW_ID_KEY + " INTEGER PRIMARY KEY,"+ 
                		FW_NAME_KEY + " TEXT, " +
                		FW_NAME_DESCRIPTION_KEY + " TEXT," +
                		FW_NAME_LOC_LAT_KEY + " DOUBLE," +
                		FW_NAME_LOC_LONG_KEY + " DOUBLE"+");";

    public FishingBuddyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FISHINGWATER_TABLE_CREATE);
    }
    
    public void addFishingWater(FishingWater fw) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(FW_NAME_KEY, fw.getName());
        values.put(FW_NAME_DESCRIPTION_KEY, fw.getDescription());
        values.put(FW_NAME_LOC_LAT_KEY, fw.getLocation().latitude);
        values.put(FW_NAME_LOC_LONG_KEY, fw.getLocation().longitude);
     
        // Inserting Row
        db.insert(FISHINGWATER_TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
    
    public FishingWater getFishingWater(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        Cursor cursor = db.query(FISHINGWATER_TABLE_NAME, new String[] { FW_ID_KEY,
        		FW_NAME_KEY, FW_NAME_LOC_LAT_KEY,FW_NAME_LOC_LONG_KEY, FW_NAME_DESCRIPTION_KEY}, FW_ID_KEY + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        FishingWater fw = new FishingWater(Integer.parseInt(cursor.getString(0)),cursor.getString(1), new LatLng(cursor.getDouble(2),cursor.getDouble(3)), cursor.getString(4));
                
        return fw;
    }
    
    public ArrayList<FishingWater> getFishingWaters(){
    	ArrayList<FishingWater> fws = new ArrayList<FishingWater>();
    	 // Select All Query
        String selectQuery = "SELECT  * FROM " + FISHINGWATER_TABLE_NAME;
     
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	FishingWater fw = new FishingWater(Integer.parseInt(cursor.getString(0)),cursor.getString(1), new LatLng(cursor.getDouble(3),cursor.getDouble(4)), cursor.getString(2));
                if(fw != null)
                	fws.add(fw);
            } while (cursor.moveToNext());
        }
    	
    	return fws;
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)  {
		// TODO Auto-generated method stub
		 // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + FISHINGWATER_TABLE_NAME);
 
        // Create tables again
        onCreate(db);
	}
}

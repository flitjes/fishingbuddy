package com.fishingbuddy.gui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fishingbuddy.R;
import com.fishingbuddy.logic.FishingManager;
import com.fishingbuddy.logic.GPSManager;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class MainActivity extends Activity {	
	private AdView adView;
	 private FishingManager fm = null;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
        setContentView(R.layout.activity_main);
        
	}



	 // Initiating Menu XML file (menu.xml)
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }
    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
         
        switch (item.getItemId())
        {
        case R.id.itShowSwims:
        	Intent fwl_activity = new Intent(this,FishingWaterListActivity.class);
			startActivity(fwl_activity);
            return true;
        case R.id.itGen:
        	GenerateData();
        	return true;
        case R.id.itMaps:
    		Intent fishingmaps_activity = new Intent(this,FishingMaps.class);
    		startActivity(fishingmaps_activity);
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    
    private void GenerateData(){
        fm = (FishingManager)getApplication();
        GPSManager gpsm = new GPSManager(this);
        fm.CreateFishingWater("Den Bosch", gpsm.getLocation(),"blabla");
        fm.CreateFishingWater("Vught", gpsm.getLocation(),"blabla");
        fm.CreateSwimForFishingWater(0, "ZuiderPlas", gpsm.getLocation(), "Fuckedup fishing");
        fm.CreateSwimForFishingWater(0, "Provinciehuis", gpsm.getLocation(), "Fuckedup fishing");
        fm.CreateSwimForFishingWater(1, "Dommel", gpsm.getLocation(), "Fuckedup fishing");
        fm.CreateSwimForFishingWater(1, "Biescheloop", gpsm.getLocation(), "Fuckedup fishing");    
    }

}

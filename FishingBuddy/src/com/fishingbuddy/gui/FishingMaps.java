package com.fishingbuddy.gui;

import android.app.Activity;
import android.os.Bundle;

import com.fishingbuddy.R;
import com.fishingbuddy.logic.FishingManager;
import com.fishingbuddy.logic.FishingWater;
import com.fishingbuddy.logic.Swim;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class FishingMaps extends Activity {
	  static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	  static final LatLng KIEL = new LatLng(53.551, 9.993);
	  private GoogleMap map;
	private FishingManager fm;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_fishing_maps);
	    fm = (FishingManager)getApplication();
	    
	    
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	        .getMap();
	    
	    for(FishingWater fw : fm.getFishingwater()){
	    	Marker marker = map.addMarker(new MarkerOptions().position(fw.getLocation())
	    	        .title(fw.getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
		    for(Swim sw : fw.getSwim()){		    	
		    	Marker m = map.addMarker(new MarkerOptions()
		    									.position(sw.getLocation())
		    									.title("Fishing Water: " + fw.getName() + " Swim: " + sw.getName())   									
		    	        );
		    }	   
	    }	   



	    LatLng CURRENT = fm.CurrentPosition();
	    map.moveCamera(CameraUpdateFactory.newLatLngZoom(CURRENT, 15));

	    // Zoom in, animating the camera.
	    map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	  }
	 /* 
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fishing_maps);
        
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // Try to obtain the map from the SupportMapFragment.
        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentByTag(MAP_FRAG_NAME);

        // Not found so make a new instance and add it to the transaction for swapping
        if (mMapFragment == null) {
            mMapFragment = SupportMapFragment.newInstance();
            ft.add(R.id.the_map, mMapFragment, MAP_FRAG_NAME);
        }

        ft.commit();
       
        
    }*/
	/*
	@Override
    public void onAttachedToWindow() {
        // Load the map here such that the fragment has a chance to completely load or else the GoogleMap value may be null
        GoogleMap googleMap;
		googleMap = (mMapFragment).getMap();
		LatLng latLng = new LatLng(-33.796923, 150.922433);
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		googleMap.addMarker(new MarkerOptions()
		        .position(latLng)
		        .title("My Spot")
		        .snippet("This is my spot!")
		        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
		googleMap.getUiSettings().setCompassEnabled(true);
		googleMap.getUiSettings().setZoomControlsEnabled(true);
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

        super.onAttachedToWindow();
    }*/
}

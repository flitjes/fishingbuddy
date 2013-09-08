package com.fishingbuddy.gui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.fishingbuddy.R;
import com.fishingbuddy.logic.FishingManager;
import com.fishingbuddy.logic.FishingWater;
import com.fishingbuddy.logic.Swim;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class FishingMaps extends Activity implements OnMapClickListener, OnMyLocationChangeListener{	  
	  private GoogleMap map;
	  private UiSettings map_settings;
	private FishingManager fm;
	private View popup_layout;
	private PopupWindow popup;
	protected PowerManager.WakeLock mWakeLock;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_fishing_maps);
	    
	    /* This code together with the one in onDestroy() 
         * will make the screen be always on until this Activity gets destroyed. */
        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "FM lock");
        this.mWakeLock.acquire();
        
	    fm = (FishingManager)getApplication();
	    
	    
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	        .getMap();	    
	    map_settings = map.getUiSettings();
	    map.setMyLocationEnabled(true);
	    map.setOnMapClickListener(this);
	    map.setOnMyLocationChangeListener(this);	    

	    RefreshFwSw();

	    LatLng CURRENT = fm.CurrentPosition();
	    map.moveCamera(CameraUpdateFactory.newLatLngZoom(CURRENT, 15));

	    // Zoom in, animating the camera.
	    map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);    
	    
	  }
	  
	  
	  
	  private void RefreshFwSw(){
		    
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
	  }
	@Override
	public void onMapClick(final LatLng point) {	
		
		
	    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            switch (which){
	            case DialogInterface.BUTTON_POSITIVE:
	            	AlertDialog.Builder builderSingle = new AlertDialog.Builder(
	                        FishingMaps.this);
	                builderSingle.setIcon(R.drawable.ic_launcher);
	                builderSingle.setTitle("Select a water:");
	                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
	                		FishingMaps.this,
	                        android.R.layout.select_dialog_singlechoice);
	                for(FishingWater f : fm.getFishingwater()){
	                	arrayAdapter.add(f.getName());
	                }
	                builderSingle.setNegativeButton("Cancel",
	                        new DialogInterface.OnClickListener() {

	                            @Override
	                            public void onClick(DialogInterface dialog, int which) {
	                                dialog.dismiss();
	                            }
	                        });

	                builderSingle.setAdapter(arrayAdapter,
	                        new DialogInterface.OnClickListener() {

	                            @Override
	                            public void onClick(DialogInterface dialog, int which) {
	                                String strName = arrayAdapter.getItem(which);
	                                ShowSlFwlPopup(point,which);	                                
	                            }
	                        });
	                builderSingle.show();
	                break;

	            case DialogInterface.BUTTON_NEGATIVE:            	
	            	ShowFishingWaterPopup(point);     	
                  
	                
	                break;
	            }	            
	        }
	    };

	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setMessage("What do you want to create?").setPositiveButton("Swim", dialogClickListener)
	        .setNegativeButton("Fishing Water", dialogClickListener).show();
	}	
	
	private void ShowFishingWaterPopup(final LatLng point){
	 	   RelativeLayout viewGroup = (RelativeLayout)findViewById(R.id.popup_fwl);
		   LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		   popup_layout = layoutInflater.inflate(R.layout.popup_fwl, viewGroup);
		 
		   // Creating the PopupWindow
		   popup = new PopupWindow(this);
		   popup.setContentView(popup_layout);
		   popup.setWidth(LayoutParams.WRAP_CONTENT);
		   popup.setHeight(LayoutParams.WRAP_CONTENT);
		   popup.setFocusable(true);            	 
		  
		 
		   // Clear the default translucent background
		   popup.setBackgroundDrawable(new BitmapDrawable());
		   
	           Button btn_ok = (Button) popup_layout.findViewById(R.id.btn_popup_fwl_ok);
	           Button btn_cancel = (Button) popup_layout.findViewById(R.id.btn_popup_fwl_cancel);		                   
	           
	           btn_ok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String water_name, water_description;
					water_name = ((EditText)popup_layout.findViewById(R.id.edWaterName)).getText().toString();
		            water_description = ((EditText)popup_layout.findViewById(R.id.edWaterDescription)).getText().toString();
		            fm.CreateFishingWater(water_name,point, water_description);
		            RefreshFwSw();
		            popup.dismiss();		            
					
				}
			});
	           
	           btn_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					popup.dismiss();							
					
				}
			});
	           popup.showAtLocation(findViewById(R.id.map), Gravity.CENTER, 0, 0);
		}

	private void ShowSlFwlPopup(final LatLng point, final int fishingwater_id){
		  RelativeLayout viewGroup = (RelativeLayout)findViewById(R.id.popup_sl);
	   	   LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   	   popup_layout = layoutInflater.inflate(R.layout.popup_swims, viewGroup);
	   	 
	   	   // Creating the PopupWindow
	   	   popup = new PopupWindow(this);
	   	   popup.setContentView(popup_layout);
	   	   popup.setWidth(LayoutParams.WRAP_CONTENT);
	   	   popup.setHeight(LayoutParams.WRAP_CONTENT);
	   	   popup.setFocusable(true);            	 
	   	  
	   	 
	   	   // Clear the default translucent background
	   	   popup.setBackgroundDrawable(new BitmapDrawable());
	   	   
	              Button btn_ok = (Button) popup_layout.findViewById(R.id.btn_popup_sl_ok);
	              Button btn_cancel = (Button) popup_layout.findViewById(R.id.btn_popup_sl_cancel);		                   
	              
	              btn_ok.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						String swim_name, swim_description;
						swim_name = ((EditText)popup_layout.findViewById(R.id.edSwimName)).getText().toString();
			            swim_description = ((EditText)popup_layout.findViewById(R.id.edSwimDescription)).getText().toString();
			            fm.CreateSwimForFishingWater(fishingwater_id,swim_name,point,swim_description);		
			            RefreshFwSw();
			            popup.dismiss();				            
						
					}
				});
	              
	              btn_cancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						popup.dismiss();							
						
					}
				});
            popup.showAtLocation(findViewById(R.id.map), Gravity.CENTER, 0, 0);
	}
	   @Override
	    public void onDestroy() {
	        this.mWakeLock.release();
	        super.onDestroy();
	    }



	@Override
	public void onMyLocationChange(Location location) {
		map.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
		
	}
}

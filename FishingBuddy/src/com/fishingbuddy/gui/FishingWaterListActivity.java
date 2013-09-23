package com.fishingbuddy.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.fishingbuddy.R;
import com.fishingbuddy.logic.FishingManager;
import com.fishingbuddy.logic.GPSManager;

public class FishingWaterListActivity extends Activity {	

	 final int CONTEXT_MENU_DELETE_ITEM =1;
	 final int CONTEXT_MENU_UPDATE_ITEM =2;
	 final int CONTEXT_MENU_SHOW_SWIMS = 3;
	 private ListView listView = null;
	 private FishingManager fm = null; 
	 
	 
	 private View popup_layout = null;
	 private static PopupWindow popup = null;
	 private AdapterView.AdapterContextMenuInfo info = null;
	 @Override
	 public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
	           
	  menu.add(Menu.NONE, CONTEXT_MENU_DELETE_ITEM, Menu.NONE, "Delete Fishing water");
	  menu.add(Menu.NONE, CONTEXT_MENU_UPDATE_ITEM, Menu.NONE, "Update Fishing water");
	  menu.add(Menu.NONE, CONTEXT_MENU_SHOW_SWIMS, Menu.NONE, "Show Swims");
	 }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
       setContentView(R.layout.activity_waterlist);
       listView = (ListView) findViewById(R.id.lvWaters); 
       
       fm = (FishingManager)getApplication();
       
        RefreshFishingWaters();
       
       //listView.setOnItemClickListener(this);
             
       registerForContextMenu(listView);                

	}

	
	@Override
	 public boolean onContextItemSelected(MenuItem item) {
	 
	      info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();  
	      	 
	      switch (item.getItemId()) {
	              case CONTEXT_MENU_DELETE_ITEM:
	            	  	fm.DeleteFishingWater(info.position);
	            	  	RefreshFishingWaters();
	                   return(true);
	             case CONTEXT_MENU_UPDATE_ITEM:	     
	            	 	ShowPopup(info.position);
	                   return(true);  
	             case CONTEXT_MENU_SHOW_SWIMS:
	            	 ShowSwims(info.position);
	            	 return (true);
	             
	      }
	  return(super.onOptionsItemSelected(item));
	}
	
	 // Initiating Menu XML file (menu.xml)
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.fwl_menu, menu);
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
        case R.id.flw_new_menu:        	
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	@Override
	protected void onResume() {
		super.onResume();
		RefreshFishingWaters();
	}
	private void ShowSwims(int position){
		Bundle b = new Bundle();
		b.putInt(SwimsListActivity.KEY_ID, position);
		Intent sl_activity = new Intent(this,SwimsListActivity.class);
		sl_activity.putExtras(b);
		startActivity(sl_activity);
	}
	
	private void RefreshFishingWaters(){
			WaterAdapter adapter = new WaterAdapter(this,R.layout.water_row, fm.getFishingwater());
	       listView.setAdapter(adapter); 
	}
	
	private void ShowPopup(final int fishingwater_index){
 	   RelativeLayout viewGroup = (RelativeLayout)findViewById(R.id.popup_fwl);
	   LayoutInflater layoutInflater = (LayoutInflater) getSystemService(getApplication().LAYOUT_INFLATER_SERVICE);
	   	   
	   popup_layout = layoutInflater.inflate(R.layout.popup_fwl, viewGroup);
	   
	   ((EditText)popup_layout.findViewById(R.id.edWaterName)).setText(fm.getFishingwater().get(fishingwater_index).getName());
	   ((EditText)popup_layout.findViewById(R.id.edWaterDescription)).setText(fm.getFishingwater().get(fishingwater_index).getDescription());
	 
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
	            fm.UpdateFishingWater(fishingwater_index,water_name,water_description,null);					            
	            popup.dismiss();
	            RefreshFishingWaters();
				
			}
		});
           
           btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popup.dismiss();							
				
			}
		});
           popup.showAtLocation(findViewById(R.id.lvWaters), Gravity.CENTER, 0, 0);
	}
	/*@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub		
		Bundle b = new Bundle();
		b.putInt(SwimsListActivity.KEY_ID, position);
		Intent sl_activity = new Intent(this,SwimsListActivity.class);
		sl_activity.putExtras(b);
		startActivity(sl_activity);
		
	}*/		
	

}

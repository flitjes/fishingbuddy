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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.fishingbuddy.R;
import com.fishingbuddy.logic.FishingManager;
import com.fishingbuddy.logic.GPSManager;

public class SwimsListActivity extends Activity implements OnItemClickListener{
	static public String KEY_ID = "KEY_ID";
	final int CONTEXT_MENU_DELETE_ITEM =1;
	final int CONTEXT_MENU_UPDATE_ITEM =2;	 
	 private ListView listView = null;
	 private FishingManager fm = null;
	 private int fishingwater_id = -1;
	 
	 private View popup_layout = null;
	 private static PopupWindow popup = null;
	 private AdapterView.AdapterContextMenuInfo info = null;
	 
	 @Override
	 public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
	           
	  menu.add(Menu.NONE, CONTEXT_MENU_DELETE_ITEM, Menu.NONE, "Delete Swim");
	  menu.add(Menu.NONE, CONTEXT_MENU_UPDATE_ITEM, Menu.NONE, "Update Swim");	  
	 }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
       setContentView(R.layout.activity_swimlist);
       listView = (ListView) findViewById(R.id.lvSwims);
       listView.setOnItemClickListener(this);
       Intent in = getIntent();
       fishingwater_id = in.getExtras().getInt(KEY_ID);
       
       fm = (FishingManager)getApplication();
       RefreshSwims();
       registerForContextMenu(listView);                

	}
	
	
	@Override
	 public boolean onContextItemSelected(MenuItem item) {
	 
	      info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();  
	      	 
	      switch (item.getItemId()) {
	              case CONTEXT_MENU_DELETE_ITEM:
	            	  	fm.DeleteSwimFromFishingWater(fishingwater_id,info.position);
	            	  	RefreshSwims();
	                   return(true);
	             case CONTEXT_MENU_UPDATE_ITEM:	     
	            	 	ShowPopup(info.position,true);
	                   return(true); 
	             
	             
	      }
	  return(super.onOptionsItemSelected(item));
	}
	
	 // Initiating Menu XML file (menu.xml)
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sl_menu, menu);
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
        case R.id.sl_new_menu:
        	
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	@Override
	protected void onResume() {
		super.onResume();	
		RefreshSwims();
	}
	
	private void RefreshSwims(){
	      SwimAdapter adapter = new SwimAdapter(this,R.layout.swim_row, fm.getFishingwater().get(fishingwater_id).getSwim());
	       listView.setAdapter(adapter);        
	         
	}
	private void ShowPopup(final int swim_index, boolean edit){
		  RelativeLayout viewGroup = (RelativeLayout)findViewById(R.id.popup_sl);
   	   LayoutInflater layoutInflater = (LayoutInflater) getSystemService(getApplication().LAYOUT_INFLATER_SERVICE);   	   
   	   popup_layout = layoutInflater.inflate(R.layout.popup_swims, viewGroup);
   	((EditText)popup_layout.findViewById(R.id.edSwimDescription)).setText(fm.getFishingwater().get(fishingwater_id).getSwim().get(swim_index).getDescription());
	   ((EditText)popup_layout.findViewById(R.id.edSwimName)).setText(fm.getFishingwater().get(fishingwater_id).getSwim().get(swim_index).getName());
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
		            fm.UpdateSwimForFishingWater(fishingwater_id, swim_index, swim_name,swim_description,null);					            
		            popup.dismiss();					            
					RefreshSwims();
				}
			});
              
              btn_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					popup.dismiss();							
					
				}
			});
              if(!edit){
           	   btn_cancel.setEnabled(false);
           	   btn_cancel.setVisibility(View.GONE);;
           	   btn_ok.setEnabled(false);
           	   btn_ok.setVisibility(View.GONE);
           	   ((EditText)popup_layout.findViewById(R.id.edSwimName)).setEnabled(false);
           	   ((EditText)popup_layout.findViewById(R.id.edSwimDescription)).setEnabled(false);
              }
              popup.showAtLocation(findViewById(R.id.lvSwims), Gravity.CENTER, 0, 0);
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
		// TODO Auto-generated method stub
		ShowPopup(position,false);
	}
}

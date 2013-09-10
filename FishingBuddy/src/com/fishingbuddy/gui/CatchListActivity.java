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
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.fishingbuddy.R;
import com.fishingbuddy.logic.FishingManager;
import com.fishingbuddy.logic.GPSManager;

public class CatchListActivity extends Activity{
	static public String KEY_ID = "KEY_ID";
	final int CONTEXT_MENU_DELETE_ITEM =1;
	final int CONTEXT_MENU_UPDATE_ITEM =2;	 
	 private ListView listView = null;
	 private FishingManager fm = null;	 
	 
	 private View popup_layout = null;
	 private static PopupWindow popup = null;
	 private AdapterView.AdapterContextMenuInfo info = null;
	 
	 @Override
	 public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
	           
	  menu.add(Menu.NONE, CONTEXT_MENU_DELETE_ITEM, Menu.NONE, "Delete Catch");
	  menu.add(Menu.NONE, CONTEXT_MENU_UPDATE_ITEM, Menu.NONE, "Update Catch");	  
	 }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
       setContentView(R.layout.activity_catchlist);
       listView = (ListView) findViewById(R.id.lvCatches);      
              
       fm = (FishingManager)getApplication();
       RefreshCatches();
       registerForContextMenu(listView);                

	}
	
	
	@Override
	 public boolean onContextItemSelected(MenuItem item) {
	 
	      info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();  
	      	 
	      switch (item.getItemId()) {
	              case CONTEXT_MENU_DELETE_ITEM:
	            	  
	                   return(true);
	             case CONTEXT_MENU_UPDATE_ITEM:	     
	            	 
	                   return(true); 
	             
	             
	      }
	  return(super.onOptionsItemSelected(item));
	}
	

	@Override
	protected void onResume() {
		super.onResume();	
		RefreshCatches();
	}
	
	private void RefreshCatches(){
	      CatchAdapter adapter = new CatchAdapter(this,R.layout.catch_row, fm.getCatches());
	       listView.setAdapter(adapter);        
	         
	}
	
}

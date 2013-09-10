package com.fishingbuddy.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.fishingbuddy.R;
import com.fishingbuddy.logic.Catch;
import com.fishingbuddy.logic.FishingManager;

public class CatchListActivity extends Activity{	
	 final int CONTEXT_MENU_DELETE_ITEM =1;
	 final int CONTEXT_MENU_UPDATE_ITEM =2;
	 final int CONTEXT_MENU_SHOW_CATCH = 3;	 
	 private ListView listView = null;
	 private FishingManager fm = null;	 
	 
	 private View popup_layout = null;
	 private static PopupWindow popup = null;
	 private AdapterView.AdapterContextMenuInfo info = null;
	 
	 @Override
	 public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
	           
	  menu.add(Menu.NONE, CONTEXT_MENU_DELETE_ITEM, Menu.NONE, "Delete Catch");
	  menu.add(Menu.NONE, CONTEXT_MENU_UPDATE_ITEM, Menu.NONE, "Update Catch");
	  menu.add(Menu.NONE, CONTEXT_MENU_SHOW_CATCH, Menu.NONE, "Show Catch");
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
	             case CONTEXT_MENU_SHOW_CATCH:	 
	            	 Bundle b = new Bundle();
	            	 Catch c = fm.getCatches().get(info.position);
	            	 b.putString(CatchActivity.KEY_NAME, c.getName());
	            	 b.putString(CatchActivity.KEY_SWIM, c.getSwim().getName());
	            	 b.putString(CatchActivity.KEY_DESCRIPTION, c.getDescription());
	            	 b.putString(CatchActivity.KEY_WEIGTH, Double.toString(c.getWeight()));
	            	 Intent catch_activity = new Intent(this,
	 						CatchActivity.class);
	            	 catch_activity.putExtras(b);
	 					startActivity(catch_activity);
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

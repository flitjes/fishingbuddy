package com.fishingbuddy.gui;

import java.util.ArrayList;

import org.json.JSONException;
import org.xml.sax.Parser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fishingbuddy.R;
import com.fishingbuddy.logic.Catch;
import com.fishingbuddy.logic.Fish;
import com.fishingbuddy.logic.FishingManager;
import com.fishingbuddy.logic.FishingWater;
import com.fishingbuddy.logic.GPSManager;
import com.fishingbuddy.logic.Swim;
import com.fishingbuddy.logic.Gear.Bait;
import com.fishingbuddy.logic.Gear.HookBait;
import com.fishingbuddy.logic.Gear.Rig;
import com.fishingbuddy.logic.Weather.JSONWeatherParser;
import com.fishingbuddy.logic.Weather.WeatherHttpClient;
import com.fishingbuddy.logic.Weather.Model.Weather;
import com.fishingbuddy.logic.storage.FishingBuddyOpenHelper;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends Activity implements OnClickListener {

	private FishingManager fm = null;

	enum DISPLAY_TYPE {
		FISH, SWIM, WATER, RIG, HOOKBAIT, BAIT
	};

	Weather weather = new Weather();
	Button CreateCatch,ShowCatch, GotoSwim, Maps;
	public Catch catch_current;
	private int selected_water_index = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		fm = (FishingManager) getApplication();		
		CreateCatch = (Button) findViewById(R.id.btnCatch);
		ShowCatch = (Button) findViewById(R.id.btnShowCatches);
		GotoSwim = (Button) findViewById(R.id.btnShowSwims);
		Maps = (Button) findViewById(R.id.btnMaps);
		CreateCatch.setOnClickListener(this);
		ShowCatch.setOnClickListener(this);
		GotoSwim.setOnClickListener(this);
		Maps.setOnClickListener(this);
		catch_current = new Catch();

	}

	// Initiating Menu XML file (menu.xml)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Event Handling for Individual menu item selected Identify single menu
	 * item by it's id
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.itShowSwims:
			Intent fwl_activity = new Intent(this,
					FishingWaterListActivity.class);
			startActivity(fwl_activity);
			return true;
		case R.id.itGen:
			//GenerateData();
			/*
			 * JSONWeatherTask task = new JSONWeatherTask(); task.execute(new
			 * GPSManager(this).getLocation());
			 */
			return true;
		case R.id.itMaps:
			Intent fishingmaps_activity = new Intent(this, FishingMaps.class);
			startActivity(fishingmaps_activity);
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private boolean created = false;

	/*private void GenerateData() {
		if (!created) {			
			GPSManager gpsm = new GPSManager(this);
			int x = 0, y = 1;

			fm.CreateFishingWater("Den Bosch", gpsm.getLocation(), "blabla");
			fm.CreateFishingWater("Dommel", new LatLng(51.6635, 5.3046),
					"blabla");
			fm.CreateFishingWater("Biescheloop", new LatLng(51.6451, 5.3201),
					"blabla");
			fm.CreateSwimForFishingWater((fm.getFishingwater().size() - 3),
					"ZuiderPlas", new LatLng(51.674, 5.319), "Fuckedup fishing");
			fm.CreateSwimForFishingWater((fm.getFishingwater().size() - 3),
					"Provinciehuis", new LatLng(51.677, 5.331),
					"Fuckedup fishing");
			fm.CreateSwimForFishingWater((fm.getFishingwater().size() - 2),
					"A2", new LatLng(51.6644, 5.3053), "Fuckedup fishing");
			fm.CreateSwimForFishingWater((fm.getFishingwater().size() - 1),
					"Biescheloop", new LatLng(51.6478, 5.3200),
					"Fuckedup fishing");
			created = true;	
			

		}
	}*/

	private class JSONWeatherTask extends AsyncTask<LatLng, Void, Weather> {

		@Override
		protected Weather doInBackground(LatLng... params) {
			Weather weather = new Weather();
			String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

			try {
				weather = JSONWeatherParser.getWeather(data);

				// Let's retrieve the icon
				weather.iconData = ((new WeatherHttpClient())
						.getImage(weather.currentCondition.getIcon()));

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return weather;

		}

		@Override
		protected void onPostExecute(Weather weather) {
			super.onPostExecute(weather);
			Log.d("Ẅeather", "Temperature is:" + weather.temperature.getTemp());
			/*
			 * Catch c = new
			 * Catch(fm.getAll_known_fish().get(0),fm.getFishingwater
			 * ().get(0).getSwim
			 * ().get(0),"Test",null,fm.getGear().getBait(),fm.getGear
			 * ().getHook_bait().get(0), fm.getGear().getRigz().get(0),weather);
			 * fm.CreateCatch(c);
			 */
			catch_current.setWeather(weather);			
		}
	}
	

	private boolean BLOCKING = true;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnCatch:
			JSONWeatherTask task = new JSONWeatherTask();
			task.execute(new GPSManager(this).getLocation());
			CreateCatchTree();
			break;
		case R.id.btnShowCatches:			
				Intent catch_activity = new Intent(this,
						CatchListActivity.class);
				startActivity(catch_activity);
			break;
		case R.id.btnShowSwims:
			Intent fwl_activity = new Intent(this,
					FishingWaterListActivity.class);
			startActivity(fwl_activity);
			break;
		case R.id.btnMaps:
			Intent fishingmaps_activity = new Intent(this, FishingMaps.class);
			startActivity(fishingmaps_activity);
			break;

		}

	}

	private void CreateCatchTree() {
		ArrayList<String> fish = new ArrayList<String>(), swim = new ArrayList<String>(), water = new ArrayList<String>(), rig = new ArrayList<String>(), bait = new ArrayList<String>(), hookbait = new ArrayList<String>();
		/*Start creating the tree:
		 * Fish
		 * Hookbait
		 * Rig
		 * Bait
		 * FishingWater
		 * Swim 
		 * */
		/* Choose Fish */
		for (Fish f : fm.getAll_known_fish()) {
			fish.add(f.getName());
		}
		ShowAlertPickingItem(fish, DISPLAY_TYPE.FISH);
	}

	private void ShowAlertPickingItem(ArrayList<String> items,
			final DISPLAY_TYPE TYPE) {
		AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
		builderSingle.setIcon(R.drawable.ic_launcher);
		switch (TYPE) {
		case FISH:
			builderSingle.setTitle("Select a Fish:");
			break;
		case SWIM:
			builderSingle.setTitle("Select a Swim:");
			break;
		case WATER:
			builderSingle.setTitle("Select a Water:");
			break;
		case RIG:
			builderSingle.setTitle("Select a Rig:");
			break;
		case HOOKBAIT:
			builderSingle.setTitle("Select a Hook Bait:");
			break;
		case BAIT:
			builderSingle.setTitle("Select a Bait:");
			break;

		}
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				this, android.R.layout.select_dialog_singlechoice);
		for (String item : items) {
			arrayAdapter.add(item);
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
					ArrayList<String> items = new ArrayList<String>();

					@Override
					public void onClick(DialogInterface dialog, int which) {
						String strName = arrayAdapter.getItem(which);
						switch (TYPE) {
						case FISH:
							catch_current.setFish(fm.getAll_known_fish().get(
									which));
							/* Choose Hookbait */
							for (HookBait hb : fm.getGear().getHook_bait()) {
								items.add(hb.getName());
							}
							ShowAlertPickingItem(items, DISPLAY_TYPE.HOOKBAIT);
							break;
						case HOOKBAIT:
							catch_current.setHookbait(fm.getGear()
									.getHook_bait().get(which));
							/* Choose Rig */
							for (Rig r : fm.getGear().getRigz()) {
								items.add(r.getName());
							}
							ShowAlertPickingItem(items, DISPLAY_TYPE.RIG);
							break;

						case RIG:
							catch_current.setRig(fm.getGear().getRigz()
									.get(which));

							/* Choose Bait */
							for (Bait b : fm.getGear().getBait()) {
								items.add(b.getName() + " Ø: " + b.getSize());
							}
							ShowAlertPickingItem(items, DISPLAY_TYPE.BAIT);
							break;

						case BAIT:
							ArrayList<Bait> baits = new ArrayList<Bait>();
							baits.add(fm.getGear().getBait().get(which));
							catch_current.setBait(baits);

							/* Choose Water */
							for (FishingWater fw : fm.getFishingwater()) {
								items.add(fw.getName());
							}
							ShowAlertPickingItem(items, DISPLAY_TYPE.WATER);
							break;
						case WATER:
							selected_water_index = which;
							/* Choose Swim */
							for (Swim sw : fm.getFishingwater()
									.get(selected_water_index).getSwim()) {
								items.add(sw.getName());
							}
							ShowAlertPickingItem(items, DISPLAY_TYPE.SWIM);
							break;
						case SWIM:
							catch_current.setSwim(fm.getFishingwater()
									.get(selected_water_index).getSwim()
									.get(which));
							ShowCatchPopup();
							break;

						}

					}
				});
		builderSingle.show();
	}
	private void ShowCatchPopup(){
		   RelativeLayout viewGroup = (RelativeLayout)findViewById(R.id.popup_ctch);
	   	   LayoutInflater layoutInflater = (LayoutInflater) getSystemService(getApplication().LAYOUT_INFLATER_SERVICE);
	   	   final View popup_layout = layoutInflater.inflate(R.layout.popup_catch, viewGroup);
	   	 
	   	   // Creating the PopupWindow
	   	   final PopupWindow popup = new PopupWindow(this);
	   	   popup.setContentView(popup_layout);
	   	   popup.setWidth(LayoutParams.WRAP_CONTENT);
	   	   popup.setHeight(LayoutParams.WRAP_CONTENT);
	   	   popup.setFocusable(true);            	 
	   	  
	   	 
	   	   // Clear the default translucent background
	   	   popup.setBackgroundDrawable(new BitmapDrawable());
	   	   
	              Button btn_ok = (Button) popup_layout.findViewById(R.id.btn_popup_catch_ok);
	              Button btn_cancel = (Button) popup_layout.findViewById(R.id.btn_popup_catch_cancel);		                   
	              
	              btn_ok.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						String catch_name, catch_description,catch_weigth, catch_length;
						catch_name = ((EditText)popup_layout.findViewById(R.id.edCatchName)).getText().toString();
			            catch_description = ((EditText)popup_layout.findViewById(R.id.edCatchDescription)).getText().toString();
			            catch_weigth = ((EditText)popup_layout.findViewById(R.id.edCatchWeigth)).getText().toString();
			            catch_length = ((EditText)popup_layout.findViewById(R.id.edCatchLength)).getText().toString();
			            catch_current.setName(catch_name);
			            catch_current.setDescription(catch_description);
			            double weight = 0,length = 0;
			            
			            if(!catch_weigth.contentEquals(""))
			            	weight = Double.parseDouble(catch_weigth);
			            
			            catch_current.setWeight(weight);
			            
			            if(!catch_length.contentEquals(""))
			            	length = Double.parseDouble(catch_length);           
			            	
			            catch_current.setLength(length);
			            
			            fm.CreateCatch(catch_current);
			            popup.dismiss();				            
						
					}
				});
	              
	              btn_cancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						popup.dismiss();							
						
					}
				});
          popup.showAtLocation(findViewById(R.id.btnCatch), Gravity.CENTER, 0, 0);
	}

}

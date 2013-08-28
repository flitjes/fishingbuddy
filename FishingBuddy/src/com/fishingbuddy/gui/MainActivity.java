package com.fishingbuddy.gui;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.fishingbuddy.R;
import com.fishingbuddy.logic.Catch;
import com.fishingbuddy.logic.FishingManager;
import com.fishingbuddy.logic.GPSManager;
import com.fishingbuddy.logic.Weather.JSONWeatherParser;
import com.fishingbuddy.logic.Weather.WeatherHttpClient;
import com.fishingbuddy.logic.Weather.Model.Weather;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends Activity implements OnClickListener{

	private FishingManager fm = null;
	Weather weather = new Weather();
	Button Catch, Swim, Maps;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GenerateData();
		Catch = (Button) findViewById(R.id.btnCatch);		
		Swim = (Button) findViewById(R.id.btnShowSwims);
		Maps = (Button) findViewById(R.id.btnMaps);
		Catch.setOnClickListener(this);
		Swim.setOnClickListener(this);
		Maps.setOnClickListener(this);
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
			GenerateData();
			/*JSONWeatherTask task = new JSONWeatherTask();
			task.execute(new GPSManager(this).getLocation());	*/		
			return true;
		case R.id.itMaps:
			Intent fishingmaps_activity = new Intent(this, FishingMaps.class);
			startActivity(fishingmaps_activity);
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	Boolean created = false;

	private void GenerateData() {
		if (!created) {
			fm = (FishingManager) getApplication();
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
	}

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
			Log.d("Ẅeather", "Temperature is:"
					+ weather.temperature.getTemp());		
			Catch c = new Catch(fm.getAll_known_fish().get(0),fm.getFishingwater().get(0).getSwim().get(0),"Test",null,fm.getGear().getBait(),fm.getGear().getHook_bait().get(0), fm.getGear().getRigz().get(0),weather);
			fm.CreateCatch(c);
			TextView tv = (TextView) findViewById(R.id.tvWeather);
			tv.setText("Current temp: "+ weather.temperature.getTemp() + "" );
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.btnCatch:
			JSONWeatherTask task = new JSONWeatherTask();
			task.execute(new GPSManager(this).getLocation());
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

}

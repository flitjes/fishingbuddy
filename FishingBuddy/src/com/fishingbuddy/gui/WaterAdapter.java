package com.fishingbuddy.gui;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fishingbuddy.R;
import com.fishingbuddy.logic.FishingWater;
import com.google.android.gms.maps.model.LatLng;

public class WaterAdapter extends ArrayAdapter<FishingWater>{

	private Context context = null;
    private List<FishingWater> fishingwater = null;
    private int rowResourceId = 0;
    
	public WaterAdapter(Context context,int textViewResourceId,
			List<FishingWater> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.fishingwater = objects;
		this.rowResourceId = textViewResourceId;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(rowResourceId, parent, false);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageSwimPicturew);
        TextView textView = (TextView) rowView.findViewById(R.id.tvNamew);
        TextView textLoc = (TextView) rowView.findViewById(R.id.tvLocw);        
        
        textView.setText(fishingwater.get(position).getName());
        textLoc.setText(fishingwater.get(position).getLocation().latitude + " " + fishingwater.get(position).getLocation().longitude);  
      
      
        return rowView;

    }

}


package com.fishingbuddy.gui;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fishingbuddy.R;
import com.fishingbuddy.logic.Swim;

public class SwimAdapter extends ArrayAdapter<Swim>{

	private Context context = null;
    private List<Swim> swim = null;
    private int rowResourceId = 0;
    
	public SwimAdapter(Context context,int textViewResourceId,
			List<Swim> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.swim = objects;
		this.rowResourceId = textViewResourceId;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(rowResourceId, parent, false);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageSwimPicture);
        TextView textView = (TextView) rowView.findViewById(R.id.tvName);
        TextView textLoc = (TextView) rowView.findViewById(R.id.tvLoc);
        TextView textDes = (TextView) rowView.findViewById(R.id.tvDescription);
        
        textView.setText(swim.get(position).getName());
        textLoc.setText(roundToSixDecimals(swim.get(position).getLocation().latitude) + " " + roundToSixDecimals(swim.get(position).getLocation().longitude));  
        textDes.setText(swim.get(position).getDescription());
      
        return rowView;

    }
	private double roundToSixDecimals(double d){
		DecimalFormat twoDForm = new DecimalFormat("#.######");
		return Double.valueOf(twoDForm.format(d));
	}

}


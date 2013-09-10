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
import com.fishingbuddy.logic.Catch;

public class CatchAdapter extends ArrayAdapter<Catch>{

	private Context context = null;
    private List<Catch> catches = null;
    private int rowResourceId = 0;
    
	public CatchAdapter(Context context,int textViewResourceId,
			List<Catch> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.catches = objects;
		this.rowResourceId = textViewResourceId;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(rowResourceId, parent, false);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageCatchPicture);
        TextView textView = (TextView) rowView.findViewById(R.id.tvNamec);
        TextView textLoc = (TextView) rowView.findViewById(R.id.tvLocc);
        TextView textDes = (TextView) rowView.findViewById(R.id.tvDescriptionc);
        TextView textWeigth = (TextView) rowView.findViewById(R.id.tvWeigthc);
        
        textView.setText(catches.get(position).getName());
        textLoc.setText(catches.get(position).getSwim().getName());  
        textDes.setText(catches.get(position).getDescription());
        textWeigth.setText(catches.get(position).getWeight() + "");
      
        return rowView;

    }

}


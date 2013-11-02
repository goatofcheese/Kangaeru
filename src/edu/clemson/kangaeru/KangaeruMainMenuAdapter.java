package edu.clemson.kangaeru;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KangaeruMainMenuAdapter extends BaseAdapter {

	private Context mContext;
	private int width, height;
	private final String[] activityNames;
	
	public KangaeruMainMenuAdapter(Context context, String[] values) {
		mContext = context;
		activityNames = values;
		Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;
	}
	
	public int getCount() {
		return activityNames.length;
	}

	public Object getItem(int arg0) {
		return null;
	}

	public long getItemId(int arg0) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent){
		
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View gridView;
		
		if (convertView == null) {
			gridView = new View(mContext);
			gridView = inflater.inflate(R.layout.main_menu_item_view, null);
			ImageView icon = (ImageView) gridView.findViewById(R.id.item_icon);
			icon.setLayoutParams(new LinearLayout.LayoutParams(width/2, (height/2) - (height/8)));
			System.err.println("position " + position);
			switch(position){
				case 0:
					icon.setImageResource(R.drawable.book);
					break;
				case 1:
					icon.setImageResource(R.drawable.page);
					break;
				case 2:
					icon.setImageResource(R.drawable.frog8);
					break;
				case 3:
					icon.setImageResource(R.drawable.writing2);
					break;
				default:
					icon.setImageResource(R.drawable.frog1);
			
			}
			TextView label = (TextView) gridView.findViewById(R.id.item_label);
			label.setText(activityNames[position]);
		}
		else
			gridView = convertView;
		
		return gridView;
	}

	
    
}

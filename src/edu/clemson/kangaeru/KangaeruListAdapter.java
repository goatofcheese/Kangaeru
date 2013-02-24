package edu.clemson.kangaeru;

import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.lang.String;

public class KangaeruListAdapter extends ArrayAdapter<String> {

    private List<String> items;
    private Context context;
    private TypedArray icons;
	
	public KangaeruListAdapter(Context context, int viewResourceId, TypedArray drawables,
			List<String> act_list) {
		super(context, viewResourceId, act_list);
		this.context = context;
		this.items = act_list;
		this.icons = drawables;
	}
	
	  public View getView(int position, View convertView, ViewGroup parent) {
	        
	        if (convertView == null) {
	            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = inflater.inflate(R.layout.main_menu_item_view, null);
	        }

	        ((TextView) convertView.findViewById(R.id.item_label)).setText((CharSequence) items.get(position));
	        
	        ((ImageView) convertView.findViewById(R.id.item_icon)).setImageResource(icons.getResourceId(position, -1)); 
	        return convertView;
	    }
	
	

}

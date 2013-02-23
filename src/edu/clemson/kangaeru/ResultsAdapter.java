package edu.clemson.kangaeru;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsAdapter extends ArrayAdapter<Integer> {

	private Context mContext;
	private List<Integer> mKeys;
	private List<Bitmap> mBitmaps;
	
	public ResultsAdapter(Context context, List<Integer> keys, List<Bitmap> bitmaps) {
		super(context, R.layout.results_list_entry, keys);
		mContext = context;
		mKeys = keys;
		mBitmaps = bitmaps;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
		View rowView = inflater.inflate(R.layout.results_list_entry, parent, false);
		
		TextView key = (TextView) rowView.findViewById(R.id.resultKey);
		ImageView bitmap = (ImageView) rowView.findViewById(R.id.resultBitmap);
		if(key == null || bitmap == null)
			System.err.println("there's a null view");
		key.setText(mKeys.get(position).toString());
		bitmap.setImageBitmap(mBitmaps.get(position));
		
		return rowView;
	}
}

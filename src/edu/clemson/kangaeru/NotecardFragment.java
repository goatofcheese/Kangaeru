package edu.clemson.kangaeru;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class NotecardFragment extends Fragment implements OnClickListener {

	private TextView squiggleTV, readingsTV, meaningsTV, sentenceTV;
	
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			   Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.notecardfragment, container, false);
		squiggleTV = (TextView) view.findViewById(R.id.fragmentsquiggle);
		readingsTV = (TextView) view.findViewById(R.id.fragmentreadings);
		meaningsTV = (TextView) view.findViewById(R.id.fragmentmeanings);
		sentenceTV = (TextView) view.findViewById(R.id.fragmentsentence);
		squiggleTV.setTextColor(Color.GREEN);
		return view;
	}

}

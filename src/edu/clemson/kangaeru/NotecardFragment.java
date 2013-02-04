package edu.clemson.kangaeru;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotecardFragment extends Fragment {

	private TextView squiggleTV, readingsTV, meaningsTV, sentenceTV;
	private Cursor list;
	
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onAttach(Activity a){
		super.onAttach(a);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			   Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.notecardfragment, container, false);
		view.setOnTouchListener(new OnSwipeTouchListener(){
		    public void onSwipeTop() {
		        System.err.println("Swiped top");
		    }
		    public void onSwipeRight() {
		    	System.err.println("Swiped right");
		    	prevNotecard();
		    }
		    public void onSwipeLeft() {
		    	System.err.println("Swiped left");
		    	nextNotecard();
		    }
		    public void onSwipeBottom() {
		    	System.err.println("Swiped bottom");
		    }
		});
		squiggleTV = (TextView) view.findViewById(R.id.fragmentsquiggle);
		readingsTV = (TextView) view.findViewById(R.id.fragmentreadings);
		meaningsTV = (TextView) view.findViewById(R.id.fragmentmeanings);
		sentenceTV = (TextView) view.findViewById(R.id.fragmentsentence);
		return view;
	}
	
	public void setCursor(Cursor c){
		list = c;
		if(c.moveToFirst())
			setStrings(c.getString(0), c.getString(1), c.getString(2), c.getString(3));
	}
	
	public void nextNotecard(){
		if(!list.isAfterLast())
			list.moveToNext();
		else
			list.moveToFirst();
		setStrings(list.getString(0), list.getString(1), list.getString(2), list.getString(3));
	}
	
	public void prevNotecard(){
		if(!list.isBeforeFirst())
			list.moveToPrevious();
		else
			list.moveToLast();
		setStrings(list.getString(0), list.getString(1), list.getString(2), list.getString(3));
	}
	
	public void setStrings(String squiggle, String readings, String meanings, String sentence){
		squiggleTV.setText(squiggle);
		readingsTV.setText(readings);
		meaningsTV.setText(meanings);
		sentenceTV.setText(sentence);
	}

}

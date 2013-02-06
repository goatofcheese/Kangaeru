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
	private String squiggle, readings, meanings, sentence, empty = "          ";
	private Cursor list;
	private boolean[] frontText = {true, true, true, true};
	private boolean front = true;
	
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
		        swapSide();
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
		    	swapSide();
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
		if(c != null){
			c.moveToFirst();
			setStrings(c.getString(0), c.getString(1), c.getString(2), c.getString(3));
		}
		else
			setStrings(empty, empty, empty, empty);
		updateDisplay();
	}
	
	public void nextNotecard(){
		if(list == null)
			return;
		if(!list.isAfterLast())
			list.moveToNext();
		else
			list.moveToFirst();
		setStrings(list.getString(0), list.getString(1), list.getString(2), list.getString(3));
		updateDisplay();
	}
	
	public void prevNotecard(){
		if(list == null)
			return;
		if(!list.isBeforeFirst())
			list.moveToPrevious();
		else
			list.moveToLast();
		setStrings(list.getString(0), list.getString(1), list.getString(2), list.getString(3));
		updateDisplay();
	}
	
	public void setStrings(String insquiggle, String inreadings, String inmeanings, String insentence){
		squiggle = insquiggle;
		readings = inreadings;
		meanings = inmeanings;
		sentence = insentence;
	}
	
	private void updateDisplay(){
		if(frontText[0] == front)
			squiggleTV.setText(squiggle);
		else
			squiggleTV.setText(empty);
		if(frontText[1] == front)
			readingsTV.setText(readings);
		else
			readingsTV.setText(empty);
		if(frontText[2] == front)
			meaningsTV.setText(meanings);
		else
			meaningsTV.setText(empty);
		if(frontText[3] == front)
			sentenceTV.setText(sentence);
		else
			sentenceTV.setText(empty);
	}

	private void swapSide(){
		front = !front;
		updateDisplay();
	}
	
	public void setFrontBack(boolean[] fronts){
		for(int i = 0; i < 4; i++)
			frontText[i] = fronts[i];
	}
	
	public boolean getFrontBack(int index){
		return frontText[index];
	}
	
}

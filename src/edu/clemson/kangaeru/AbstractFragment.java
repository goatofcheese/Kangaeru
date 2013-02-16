package edu.clemson.kangaeru;

import android.app.Fragment;
import android.database.Cursor;

public abstract class AbstractFragment extends Fragment {

	protected Cursor list;
	protected OnSwipeTouchListener cursorSwapper = new OnSwipeTouchListener(){
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
	};
	
	public void setCursor(Cursor c){
		list = c;
		if(c != null && c.getCount() > 0){
			c.moveToFirst();
			setStrings();
		}
		else
			nullStrings();
	}
	
	public void nextNotecard(){
		if(list == null)
			return;
		if(!list.isLast())
			list.moveToNext();
		else
			list.moveToFirst();
		updateDisplay();
	}
	
	public void prevNotecard(){
		if(list == null)
			return;
		if(!list.isFirst())
			list.moveToPrevious();
		else
			list.moveToLast();
		updateDisplay();
	}
		
	protected abstract void setStrings();
	protected abstract void nullStrings();
	protected abstract void swapSide();
	protected abstract void updateDisplay();
	
	
	}

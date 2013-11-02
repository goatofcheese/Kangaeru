package edu.clemson.kangaeru;

import android.app.Fragment;
import android.database.Cursor;

public abstract class AbstractFragment extends Fragment {

	protected int cursorCount, maxCount;
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
		cursorCount = 0;
		maxCount = 0;
		if(c != null){
			if(c.getCount() > 0){
				maxCount = c.getCount() - 1;
				c.moveToFirst();
				setStrings();
			}
			else
				nullStrings();
		}
		else
			nullStrings();
	}
	
	public void nextNotecard(){
		if(list == null || list.getCount() < 1)
			return;
		if(!list.isLast())
			list.moveToNext();
		else
			list.moveToFirst();
		cursorCount = (cursorCount < maxCount) ? cursorCount + 1 : 0;
		updateDisplay();
	}
	
	public void prevNotecard(){
		if(list == null || list.getCount() < 1)
			return;
		if(!list.isFirst())
			list.moveToPrevious();
		else
			list.moveToLast();
		cursorCount = (cursorCount) > 0 ? cursorCount - 1 : maxCount;
		updateDisplay();
	}
		
	protected abstract void setStrings();
	protected abstract void nullStrings();
	protected abstract void swapSide();
	protected abstract void updateDisplay();

	
	
	}

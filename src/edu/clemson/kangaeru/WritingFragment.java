package edu.clemson.kangaeru;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WritingFragment extends AbstractFragment {

	
	private TextView kanji;
	private String empty = "          ";
	private WritingPractice parent;
	
	@Override
	public void onAttach(Activity a){
		super.onAttach(a);
		parent = (WritingPractice) a;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			   Bundle savedInstanceState){
		
		
		View v = inflater.inflate(R.layout.writingfragment, container, false);
		v.setOnTouchListener(cursorSwapper);
		kanji = (TextView) v.findViewById(R.id.kanji);
		
		/***************************************************
		 * 
		 * As of right now special case needed for text size
		 * for normal screens, probably needs to be expanded
		 * in the future to account for every possible screen
		 * size
		 * 
		 ***************************************************/
		MyApp appState = ((MyApp)parent.getApplicationContext());
		switch(appState.getState()) {
			case Configuration.SCREENLAYOUT_SIZE_LARGE:
				// XXX TODO implement large
				break;
			case Configuration.SCREENLAYOUT_SIZE_NORMAL:
				kanji.setTextSize(90);
				break;
			case Configuration.SCREENLAYOUT_SIZE_SMALL:
				// XXX TODO implement small
				break;
			case Configuration.SCREENLAYOUT_SIZE_XLARGE:
				// XXX TODO implement xlarge
				break;
			default:
				// XXX TODO implement default
				break;
		}
		
		return v;

	}
	
	@Override
	protected void setStrings() {
		kanji.setText(list.getString(0));
	}

	@Override
	protected void nullStrings() {
		kanji.setText(empty);
	}

	@Override
	protected void swapSide() {
		//nothing gonna happen here, you don't swip up!
	}

	@Override
	protected void updateDisplay() {
		setStrings();
		((WritingPractice)getActivity()).clearCanvas(null);
	}

	@Override
	public void nextNotecard(){
		parent.saveBitmap(cursorCount);
		super.nextNotecard();
	}
	
	@Override
	public void prevNotecard(){
		parent.saveBitmap(cursorCount);
		super.prevNotecard();
	}
	
	public void hack(){
		//boy is this some really shitty code
		cursorCount--;
	}
}

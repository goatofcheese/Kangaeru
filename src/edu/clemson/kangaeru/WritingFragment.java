package edu.clemson.kangaeru;

import android.app.Activity;
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

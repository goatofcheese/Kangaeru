package edu.clemson.kangaeru;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WritingFragment extends AbstractFragment {

	
	private TextView kanji;
	private String empty = "          ";
	
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
		//nothing gonna happen here
	}

	@Override
	protected void updateDisplay() {
		setStrings();
		((WritingPractice)getActivity()).clearCanvas(null);
	}

}

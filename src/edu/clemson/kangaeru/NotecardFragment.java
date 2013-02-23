package edu.clemson.kangaeru;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NotecardFragment extends AbstractFragment {

	private ImageView backgroundIV;
	private TextView squiggleTV, readingsTV, meaningsTV, compoundTV;
	private String empty = "          ";
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
		view.setOnTouchListener(cursorSwapper);
//		backgroundIV = (ImageView) view.findViewById(R.id.notecardBackground);
//		backgroundIV.setImageResource(R.drawable.frog4);
		
		squiggleTV = (TextView) view.findViewById(R.id.fragmentsquiggle);
		readingsTV = (TextView) view.findViewById(R.id.fragmentreadings);
		meaningsTV = (TextView) view.findViewById(R.id.fragmentmeanings);
		compoundTV = (TextView) view.findViewById(R.id.fragmentcompound);
		return view;
	}
	
	public void updateDisplay(){
		setStrings();
	}
	
	public void nullStrings(){
		squiggleTV.setText(empty);
		readingsTV.setText(empty);
		meaningsTV.setText(empty);
		compoundTV.setText(empty);
	}
	
	protected void setStrings(){
		if(frontText[0] == front)
			squiggleTV.setText(list.getString(0));
		else
			squiggleTV.setText(empty);
		if(frontText[1] == front)
			readingsTV.setText(list.getString(1));
		else
			readingsTV.setText(empty);
		if(frontText[2] == front)
			meaningsTV.setText(list.getString(2));
		else
			meaningsTV.setText(empty);
		if(frontText[3] == front)
			compoundTV.setText(list.getString(3));
		else
			compoundTV.setText(empty);
		squiggleTV.setGravity(Gravity.CENTER_HORIZONTAL);
		readingsTV.setGravity(Gravity.CENTER_HORIZONTAL);
		meaningsTV.setGravity(Gravity.CENTER_HORIZONTAL);
		compoundTV.setGravity(Gravity.CENTER_HORIZONTAL);
	}

	protected void swapSide(){
		front = !front;
		updateDisplay();
	}
	
	public void setFrontBack(boolean[] fronts){
		for(int i = 0; i < 4; i++)
			frontText[i] = fronts[i];
		if(squiggleTV != null && list != null && list.getCount() > 0)
			setStrings();
	}
	
	public boolean getFrontBack(int index){
		return frontText[index];
	}
	
}

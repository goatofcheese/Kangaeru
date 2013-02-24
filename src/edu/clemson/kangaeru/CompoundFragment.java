package edu.clemson.kangaeru;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class CompoundFragment extends AbstractFragment {

	private TextView remainder;
	private EditText answerInput;
	private TextView hint1, hint2, xOutOfY;
	private String empty = "          ";
	private GuessEvaluator mAct;
	private InputMethodManager mMan;
	private int guessAmount = 1;
	private boolean[] isUnique;
	
	public interface GuessEvaluator{
		void updateImage(boolean success, boolean unique);
	}
	
	@Override 
	public void onAttach(Activity a){
		super.onAttach(a);
		mAct = (GuessEvaluator) a;
		mMan = (InputMethodManager) a.getSystemService(Context.INPUT_METHOD_SERVICE);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			   Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.compoundfragment, container, false);
		v.setOnTouchListener(cursorSwapper);
		remainder = (TextView) v.findViewById(R.id.remainder);
		answerInput = (EditText) v.findViewById(R.id.answerInput);
		answerInput.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(event.getAction() == KeyEvent.ACTION_DOWN &&
						keyCode == KeyEvent.KEYCODE_ENTER){
					if(list != null){
						if(list.getCount() > 0) //otherwise, there's a null exception
							guess();
					}
					mMan.hideSoftInputFromWindow(answerInput.getWindowToken(), 0);
					return true;
				}
				return false;
			}
		});
		
		hint1 = (TextView) v.findViewById(R.id.prompt1);
		hint2 = (TextView) v.findViewById(R.id.prompt2);
		xOutOfY = (TextView) v.findViewById(R.id.xOutOfY);
		return v;
	}
	
	
	@Override
	protected void setStrings(){
		System.err.println("column #: " + list.getColumnCount());
		
		remainder.setText(list.getString(0).substring(guessAmount));
		
		hint1.setText(list.getString(1));
		hint2.setText(list.getString(2));
		xOutOfY.setText(cursorCount + "/" + maxCount);
		
		answerInput.setGravity(Gravity.CENTER_HORIZONTAL);
		remainder.setGravity(Gravity.CENTER_HORIZONTAL);
	}
	
	@Override
	protected void nullStrings(){
		remainder.setText(empty);
		hint1.setText(empty);
		hint2.setText(empty);
		xOutOfY.setText(cursorCount + "/" + maxCount);
	}
	
	@Override
	public void setCursor(Cursor c){
		super.setCursor(c);
		isUnique = new boolean[c.getCount()];
		for(int i = 0; i < isUnique.length; i++){
			isUnique[i] = true;
		}
	}
	
	private void guess(){
		String answer = answerInput.getText().toString() + remainder.getText();
		boolean same = answer.equals(list.getString(0));
		boolean unique = isUnique[cursorCount - 1];
		if(same)
			isUnique[cursorCount - 1] = false;

		mAct.updateImage(same, unique);
	}

	@Override
	protected void swapSide() {
		//not really applicable here
	}


	@Override
	protected void updateDisplay() {
		setStrings();
	}

	public void setGuessAmount(int g){
		guessAmount = g;
	}
	
}

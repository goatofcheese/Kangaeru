package edu.clemson.kangaeru;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class SettingsDialogFragment extends DialogFragment {
	
	private ToggleButton squiggleToggle;
	private ToggleButton readingsToggle;
	private ToggleButton meaningsToggle;
	private ToggleButton sentenceToggle;
	private boolean[] settings = {true, true, true, true};
	NotecardActivity attachedActivity;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
		View v = inflater.inflate(R.layout.dialog_notecard_settings, container, false);
		
		attachedActivity = ((NotecardActivity) getActivity());
		
		squiggleToggle = (ToggleButton) v.findViewById(R.id.squiggletoggle);
		readingsToggle = (ToggleButton) v.findViewById(R.id.readingstoggle);
		meaningsToggle = (ToggleButton) v.findViewById(R.id.meaningstoggle);
		sentenceToggle = (ToggleButton) v.findViewById(R.id.sentencetoggle);
		
		squiggleToggle.setChecked(attachedActivity.getDisplayedNotecard().getFrontBack(0));
		readingsToggle.setChecked(attachedActivity.getDisplayedNotecard().getFrontBack(1));
		meaningsToggle.setChecked(attachedActivity.getDisplayedNotecard().getFrontBack(2));
		sentenceToggle.setChecked(attachedActivity.getDisplayedNotecard().getFrontBack(3));
		
    	squiggleToggle.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			onSettingsToggled(squiggleToggle, 0);
    		}
    	});

    	readingsToggle.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			onSettingsToggled(readingsToggle, 1);
    		}
    	});
    	
    	meaningsToggle.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			onSettingsToggled(meaningsToggle, 0);
    		}
    	});
    	
    	sentenceToggle.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			onSettingsToggled(sentenceToggle, 0);
    		}
    	});
    	
		return v;
	}
	
	private void onSettingsToggled(View v, int index){
		settings[index] = ((ToggleButton) v).isChecked();
		((NotecardActivity)getActivity()).getDisplayedNotecard().setFrontBack(settings);
	}
	
}

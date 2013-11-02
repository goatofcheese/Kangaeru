package edu.clemson.kangaeru;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ToggleButton;

public class SettingsDialogFragment extends DialogFragment {
	
	private ToggleButton squiggleToggle;
	private ToggleButton readingsToggle;
	private ToggleButton meaningsToggle;
	private ToggleButton compoundToggle;
	private boolean[] settings = {true, true, true, true};
	private NotecardActivity attachedActivity;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
		View v = inflater.inflate(R.layout.dialog_notecard_settings, container, false);
		
		getDialog().setTitle("Click to Toggle");
		
		attachedActivity = ((NotecardActivity) getActivity());
		
		squiggleToggle = (ToggleButton) v.findViewById(R.id.squiggletoggle);
		readingsToggle = (ToggleButton) v.findViewById(R.id.readingstoggle);
		meaningsToggle = (ToggleButton) v.findViewById(R.id.meaningstoggle);
		compoundToggle = (ToggleButton) v.findViewById(R.id.compoundtoggle);
		
		squiggleToggle.setChecked(attachedActivity.getDisplayedNotecard().getFrontBack(0));
		readingsToggle.setChecked(attachedActivity.getDisplayedNotecard().getFrontBack(1));
		meaningsToggle.setChecked(attachedActivity.getDisplayedNotecard().getFrontBack(2));
		compoundToggle.setChecked(attachedActivity.getDisplayedNotecard().getFrontBack(3));
		
		settings[0] = squiggleToggle.isChecked();
		settings[1] = readingsToggle.isChecked();
		settings[2] = meaningsToggle.isChecked();
		settings[3] = compoundToggle.isChecked();
		
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
    			onSettingsToggled(meaningsToggle, 2);
    		}
    	});
    	
    	compoundToggle.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			onSettingsToggled(compoundToggle, 3);
    		}
    	});
    	
		return v;
	}
	
	private void onSettingsToggled(View v, int index){
		settings[index] = ((ToggleButton) v).isChecked();
	}

	@Override
	public void onDismiss(DialogInterface dialog){
		super.onDismiss(dialog);
		attachedActivity.getDisplayedNotecard().setFrontBack(settings);
	}
	
}

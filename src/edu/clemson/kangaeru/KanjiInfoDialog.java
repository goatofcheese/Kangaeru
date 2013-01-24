package edu.clemson.kangaeru;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;

public class KanjiInfoDialog extends DialogFragment {

	private long id = -1;
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	// Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.title_kanji);
        String [] stuff = new String[3];
        stuff[0] = "Squiggle #:" + id;
        stuff[1] = "Readings";
        stuff[2] = "Sentence";
        builder.setItems(stuff, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
				
            }
        });
    
        // Create the AlertDialog object and return it
        return builder.create();
    }
    
    public void setKanji(long newId){
    	id = newId;
    }
}
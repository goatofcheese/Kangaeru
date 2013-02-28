package edu.clemson.kangaeru;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class KanjiInfoDialog extends Dialog
{
	private Context context;
	private final DictionaryAdapter mDictionaryAdapter;
	final long fid;
	private String info[];
	private ArrayList<String> tables;
	
	private TextView kanjiTextView;
	private TextView readingsTextView;
	private TextView meaningTextView;
	private TextView compoundTextView;
	
	private ArrayAdapter<String> listAdapter;
	private Spinner listSpinner;
	private ImageView notecardButton;
	

	
	KanjiInfoDialog(final Context c, DictionaryAdapter mDA, final long id, String[] i, ArrayList<String> t){
		super(c);
		context = c;
		mDictionaryAdapter = mDA;
		fid = id;
		info = i;
		tables = t;
		
		this.setTitle("Details");
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		setContentView(R.layout.dialog_kanji_info);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
		
		  //Fill in the dialog box with the information gathered from the database
		  
		  //animation of drawn kanji
		kanjiTextView = (TextView) this.findViewById(R.id.kanji);
		readingsTextView = (TextView) this.findViewById(R.id.readings);
		meaningTextView = (TextView) this.findViewById(R.id.meaning);
		compoundTextView = (TextView) this.findViewById(R.id.compound);

		  
		  kanjiTextView.setText(info[0]);
		  kanjiTextView.setTextSize(100); 
		  readingsTextView.setText("Readings: \t\t" + info[1]);
		  meaningTextView.setText("Meaning: \t\t" + info[2]);
		  compoundTextView.setText("Example Compound: \t\t" + info[3]);
		  
		
        listAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, tables);
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listSpinner = (Spinner) this.findViewById(R.id.list_spinner);
        listSpinner.setAdapter(listAdapter);
        notecardButton = (ImageView) findViewById(R.id.notecardButton);
        listSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, final long id) {
          	  final String selected = parent.getItemAtPosition(pos).toString();
        		  notecardButton.setOnClickListener(new View.OnClickListener() {
                      //Change the isNotecard entry in the database and close the dialog box
                      public void onClick(View v) {
                      mDictionaryAdapter.addNotecard(fid, selected);
                      dismiss();
                      }
        		  });

            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
  	  
  	  ImageView cancelButton = (ImageView) this.findViewById(R.id.cancelButton);
		  cancelButton.setOnClickListener(new View.OnClickListener() {
			  //Close the dialog and return to main DictionaryActivity
			  public void onClick(View v) {
				  dismiss();
			  }
		  });


	}
	
}
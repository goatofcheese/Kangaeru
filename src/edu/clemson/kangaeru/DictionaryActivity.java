package edu.clemson.kangaeru;

import java.util.ArrayList;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import edu.clemson.kangaeru.FilterDialogFragment.QueryTaker;

public class DictionaryActivity extends ListActivity implements QueryTaker{

	final Context context = this;
	private DictionaryAdapter mDictionaryAdapter;
	private Dialog kanjiInfoDialog;
	private FilterDialogFragment filterDialog; 
	private String query = "";
	private Cursor c;
	private InputMethodManager mMan;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        mDictionaryAdapter = new DictionaryAdapter(this);
        mDictionaryAdapter.open();
        mMan = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        fillData(query);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_dictionary, menu);
        return true;
    }

    private void fillData(String query) {
    	
        // Get all of the kanji from the database and create the item list
    	if(query.length() == 0)
    		c = mDictionaryAdapter.fetchAllEntries();
    	else
    		c = mDictionaryAdapter.filter(query);
        startManagingCursor(c);

        String[] from = new String[] {	DictionaryAdapter.KEY_SQUIGGLE,
        								DictionaryAdapter.KEY_READINGS };
        int[] to = new int[] { R.id.listsquiggle, R.id.listreadings };
        
        // Now create an array adapter and set it to display using our row
        SimpleCursorAdapter notes =
        		new SimpleCursorAdapter(this, R.layout.dictionary_list_entry, c, from, to);
        ListView lv = getListView();
        setListAdapter(notes);
        
        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {

        	  final long finalId = id;
        	  
        	  //fetch the database entry corresponding to the item clicked
        	  Cursor item = mDictionaryAdapter.fetchEntry(id);
        	  String squiggle = item.getString(item.getColumnIndex("squiggle"));
        	  String readings = item.getString(item.getColumnIndex("readings"));
        	  String meaning = item.getString(item.getColumnIndex("meaning"));
        	  if(item.getColumnIndex("sentence") != -1)
        		  System.err.println("For whatever reason, sentence is still in the database");
        	  String compound = item.getString(item.getColumnIndex("compound"));
        	  ArrayList<String> tables = (ArrayList<String>) mDictionaryAdapter.getLists();
        	  
        	  //create the dialog box to show the detailed information
              kanjiInfoDialog = new Dialog(context);
      		  kanjiInfoDialog.setContentView(R.layout.dialog_kanji_info);
      		  kanjiInfoDialog.setTitle("Details");
      		  
      		    //Fill in the dialog box with the information gathered from the database
      		  
      		  //animation of drawn kanji
      		  ImageView animationImageView = (ImageView) kanjiInfoDialog.findViewById(R.id.animation);
      		  animationImageView.setImageResource(R.drawable.animation_placeholder); //to be changed
      		  
      		  //kanji character
      		  TextView kanjiTextView = (TextView) kanjiInfoDialog.findViewById(R.id.kanji);
      		  kanjiTextView.setText(squiggle);
      		  kanjiTextView.setTextSize(100); 

      		  //kanji readings
      		  TextView readingsTextView = (TextView) kanjiInfoDialog.findViewById(R.id.readings);
      		  readingsTextView.setText("Readings: \t\t" + readings);
      		
      		  //kanji meaning
      		  TextView meaningTextView = (TextView) kanjiInfoDialog.findViewById(R.id.meaning);
      		  meaningTextView.setText("Meaning: \t\t" + meaning);
      		  
      		  //example compound
      		  TextView compoundTextView = (TextView) kanjiInfoDialog.findViewById(R.id.compound);
      		  compoundTextView.setText("Example Compound: \t\t" + compound);
      		  
      		  Spinner listSpinner = (Spinner) kanjiInfoDialog.findViewById(R.id.list_spinner);
              ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, tables);
              listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
              listSpinner.setAdapter(listAdapter);
              listSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                  public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                	  final String selected = parent.getItemAtPosition(pos).toString();
                	  
              		  ImageView notecardButton = (ImageView) kanjiInfoDialog.findViewById(R.id.notecardButton);
              		  notecardButton.setOnClickListener(new OnClickListener() {
              			  //Change the isNotecard entry in the database and close the dialog box
              			  public void onClick(View v) {
              				  mDictionaryAdapter.addNotecard(finalId, selected);
              				  kanjiInfoDialog.dismiss();
              			  }
              		  });

                  }

                  public void onNothingSelected(AdapterView<?> arg0) {

                  }
              });
              
              final EditText enterList = (EditText) kanjiInfoDialog.findViewById(R.id.enterList);
              //start of what may be horrible
              enterList.setOnKeyListener(new OnKeyListener() {
            	  public boolean onKey(View v, int keyCode, KeyEvent event) {
            		  if(event.getAction() == KeyEvent.ACTION_DOWN &&
    						keyCode == KeyEvent.KEYCODE_ENTER){
            			  mMan.hideSoftInputFromWindow(enterList.getWindowToken(), 0);
            			  System.err.println("should close the keyboard");
            			  return true;
            		  }
            		  return false;
            	  }
              });
              
              //end of potential crap
              
              
        	  Button newlistButton = (Button) kanjiInfoDialog.findViewById(R.id.newlistButton);
        	  newlistButton.setOnClickListener(new OnClickListener() {
      			  //Change the isNotecard entry in the database and close the dialog box
      			  public void onClick(View v) {
      				  if(mDictionaryAdapter.addList(enterList.getText().toString()));
      				  	kanjiInfoDialog.dismiss();
      			  }
      		  });
        	  
        	  ImageView cancelButton = (ImageView) kanjiInfoDialog.findViewById(R.id.cancelButton);
      		  cancelButton.setOnClickListener(new OnClickListener() {
      			  //Close the dialog and return to main DictionaryActivity
      			  public void onClick(View v) {
      				  kanjiInfoDialog.dismiss();
      			  }
      		  });
      		  kanjiInfoDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
      		  kanjiInfoDialog.show();
          }
        });
    }
    
    public void showFilter(View v){

        filterDialog = new FilterDialogFragment();
        filterDialog.show(getFragmentManager(), "Filter");
    	
    }

	public void takeQuery(String query) {
		System.err.println(query);
		fillData(query);
		
	}
    
}

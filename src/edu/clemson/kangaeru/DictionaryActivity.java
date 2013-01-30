package edu.clemson.kangaeru;

import java.util.ArrayList;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class DictionaryActivity extends ListActivity{

	final Context context = this;
	private DictionaryAdapter mDictionaryAdapter;
	private Dialog kanjiInfoDialog;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        mDictionaryAdapter = new DictionaryAdapter(this);
        mDictionaryAdapter.open();
        fillData();
                
        //mKanjiInfoDialog = new KanjiInfoDialog();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_dictionary, menu);
        return true;
    }

    private void fillData() {
    	
        // Get all of the kanji from the database and create the item list
        Cursor c = mDictionaryAdapter.fetchAllEntries();
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
        	  System.err.println(id);

        	  final long finalId = id;
        	  
        	  //fetch the database entry corresponding to the item clicked
        	  Cursor item = mDictionaryAdapter.fetchEntry(id);
        	  String squiggle = item.getString(item.getColumnIndex("squiggle"));
        	  String readings = item.getString(item.getColumnIndex("readings"));
        	  String meaning = item.getString(item.getColumnIndex("meaning"));
        	  String sentence = item.getString(item.getColumnIndex("sentence"));
        	  
        	  //create the dialog box to show the detailed information
              kanjiInfoDialog = new Dialog(context);
      		  kanjiInfoDialog.setContentView(R.layout.dialog_kanji_info);
      		  kanjiInfoDialog.setTitle("Details");
      		  
      		  //Fill in the dialog box with the information gathered from the database
      		  ImageView animationImageView = (ImageView) kanjiInfoDialog.findViewById(R.id.animation);
      		  animationImageView.setImageResource(R.drawable.frog7); //to be changed
      		  
      		  TextView kanjiTextView = (TextView) kanjiInfoDialog.findViewById(R.id.kanji);
      		  kanjiTextView.setText("Character: \n\t" + squiggle);
      		
      		  TextView readingsTextView = (TextView) kanjiInfoDialog.findViewById(R.id.readings);
      		  readingsTextView.setText("Readings: \n\t" + readings);
      		
      		  TextView meaningTextView = (TextView) kanjiInfoDialog.findViewById(R.id.meaning);
      		  meaningTextView.setText("Meaning: \n\t" + meaning);
      		  
      		  TextView sentenceTextView = (TextView) kanjiInfoDialog.findViewById(R.id.sentence);
      		  sentenceTextView.setText("Example Sentence: \n\t" + sentence);
      		  
      		  Spinner listSpinner = (Spinner) kanjiInfoDialog.findViewById(R.id.list_spinner);
			  //List<String> list = new ArrayList<String>();
			  
      		  Button notecardButton = (Button) kanjiInfoDialog.findViewById(R.id.notecardButton);
      		  notecardButton.setOnClickListener(new OnClickListener() {
      			  //Change the isNotecard entry in the database and close the dialog box
      			  public void onClick(View v) {
      				  System.err.println("before: " + mDictionaryAdapter.checkNotecard());
      				  mDictionaryAdapter.setNotecard(finalId, 1);
      				  System.err.println("after: " + mDictionaryAdapter.checkNotecard());
      				  kanjiInfoDialog.dismiss();
      			  }
      		  });
      		  
      		  kanjiInfoDialog.show();
          }
        });
    }
    
    public void returnToMain(View v){
    	mDictionaryAdapter.close();
    	Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
    
}

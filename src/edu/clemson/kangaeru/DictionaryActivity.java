package edu.clemson.kangaeru;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
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

        	  Cursor item = mDictionaryAdapter.fetchEntry(id);
        	  String squiggle = item.getString(item.getColumnIndex("squiggle"));
        	  String readings = item.getString(item.getColumnIndex("readings"));
        	  
              kanjiInfoDialog = new Dialog(context);
      		  kanjiInfoDialog.setContentView(R.layout.dialog_kanji_info);
      		  kanjiInfoDialog.setTitle("Details");
      		  
      		  ImageView animationImageView = (ImageView) kanjiInfoDialog.findViewById(R.id.animation);
      		  animationImageView.setImageResource(R.drawable.frog7);
      		  
      		  TextView kanjiTextView = (TextView) kanjiInfoDialog.findViewById(R.id.kanji);
      		  kanjiTextView.setText(squiggle);
      		
      		  TextView readingsTextView = (TextView) kanjiInfoDialog.findViewById(R.id.readings);
      		  readingsTextView.setText(readings);
      		
      		  TextView meaningTextView = (TextView) kanjiInfoDialog.findViewById(R.id.meaning);
      		  meaningTextView.setText("TL note: keikaku means kawaii");
        	  kanjiInfoDialog.show();
        	  
          }
        });
    }
    
    public void returnToMain(View v){
    	Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
    
}

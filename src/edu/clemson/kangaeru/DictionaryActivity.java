package edu.clemson.kangaeru;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DictionaryActivity extends ListActivity{

	private DictionaryAdapter mDictionaryAdapter;
	private KanjiInfoDialog mKanjiInfoDialog;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        mDictionaryAdapter = new DictionaryAdapter(this);
        mDictionaryAdapter.open();
        fillData();
                
        mKanjiInfoDialog = new KanjiInfoDialog();
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
        View return_button = getLayoutInflater().inflate(R.layout.return_button, null);
        lv.addFooterView(return_button);
        setListAdapter(notes);
        
        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
        	  System.err.println(id);
        	  mKanjiInfoDialog.setKanji(id);
        	  mKanjiInfoDialog.show(getFragmentManager(), "KanjiInfoDialog");
              }
        });
    }
    
    public void returnToMain(View v){
    	Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
    
}

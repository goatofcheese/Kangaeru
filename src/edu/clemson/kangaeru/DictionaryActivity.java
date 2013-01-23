package edu.clemson.kangaeru;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SimpleCursorAdapter;

public class DictionaryActivity extends ListActivity{

	private DictionaryAdapter mDictionaryAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        mDictionaryAdapter = new DictionaryAdapter(this);
        mDictionaryAdapter.open();
        fillData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_dictionary, menu);
        return true;
    }

    private void fillData() {
    	//Make some kanjor
    	mDictionaryAdapter.createEntry("日", "ひ、　ニチ", "今日は");
    	mDictionaryAdapter.createEntry("本", "もと、　ホン", "本を読む");
    	mDictionaryAdapter.createEntry("学", "まな・ぶ、　ガク", "学生");
    	
        // Get all of the notes from the database and create the item list
        Cursor c = mDictionaryAdapter.fetchAllEntries();
        startManagingCursor(c);

        String[] from = new String[] {	DictionaryAdapter.KEY_SQUIGGLE,
        								DictionaryAdapter.KEY_READINGS };
        int[] to = new int[] { R.id.listsquiggle, R.id.listreadings };
        
        // Now create an array adapter and set it to display using our row
        SimpleCursorAdapter notes =
        		new SimpleCursorAdapter(this, R.layout.dictionary_list_entry, c, from, to);
        setListAdapter(notes);
    }
    
}

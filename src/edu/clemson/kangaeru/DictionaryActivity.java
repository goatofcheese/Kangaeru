package edu.clemson.kangaeru;

import java.util.ArrayList;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
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
import android.widget.Toast;
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
        	  String[] info = new String[]{"","","",""};
        	  info[0] = item.getString(item.getColumnIndex("squiggle"));
        	  info[1] = item.getString(item.getColumnIndex("readings"));
        	  info[2] = item.getString(item.getColumnIndex("meaning"));
        	  if(item.getColumnIndex("sentence") != -1)
        		  System.err.println("For whatever reason, sentence is still in the database");
        	  info[3] = item.getString(item.getColumnIndex("compound"));
        	  ArrayList<String> tables = (ArrayList<String>) mDictionaryAdapter.getLists();
        	  
        	  //create the dialog box to show the detailed information
              kanjiInfoDialog = new KanjiInfoDialog(context,mDictionaryAdapter,finalId,info,tables);
      		  kanjiInfoDialog.show();
          }
        });
        
        final EditText enterList = (EditText) this.findViewById(R.id.enterList);
        enterList.setBackgroundColor(Color.parseColor("#E0A2FB"));
        //start of what may be horrible
        enterList.setOnKeyListener(new View.OnKeyListener() {
      	  public boolean onKey(View v, int keyCode, KeyEvent event) {
      		  if(event.getAction() == KeyEvent.ACTION_DOWN &&
						keyCode == KeyEvent.KEYCODE_ENTER){
      			  mMan.hideSoftInputFromWindow(enterList.getWindowToken(), 0);
      			  return true;
      		  }
      		  return false;
      	  }

        });
        //end of potential crap
        
        
  	  Button newlistButton = (Button) this.findViewById(R.id.newlistButton);
  	  newlistButton.setBackgroundColor(Color.parseColor("#FA7014"));
  	 
  	  newlistButton.setOnClickListener(new View.OnClickListener() {
			  //Change the isNotecard entry in the database and close the dialog box
			  public void onClick(View v) {
				  if(enterList.getText().toString().trim().length() <= 0)
					  Toast.makeText(getApplicationContext(),"Enter name of list in the text box before creating a list",Toast.LENGTH_SHORT).show();
				  mDictionaryAdapter.addList(enterList.getText().toString());
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

package edu.clemson.kangaeru;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class NotecardActivity extends Activity {

	private Spinner listselect;
	private DictionaryAdapter mDictionaryAdapter;
	private NotecardFragment currentNotecard = null;
	private SettingsDialogFragment settingsDialog;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Notecards");
        setContentView(R.layout.activity_notecard);
        mDictionaryAdapter = new DictionaryAdapter(this);
        mDictionaryAdapter.open();
        
        //Notecard Fragment
        initializeFragment();
        
        //Spinner Stuff
        setSpinner();
        listselect.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            	final String selected = parent.getItemAtPosition(pos).toString();
            	final ArrayList<Integer> ids = mDictionaryAdapter.checkNotecard(selected);
                Button s = (Button) findViewById(R.id.listselect);
                s.setOnClickListener(new View.OnClickListener(){
                	public void onClick(View v){
                		openList(ids, getDisplayedNotecard());
                	}
                }); 
            }

			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
        });
        
        //End of Spinner stuff 
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_notecard, menu);
        return true;
    }
    
    private void setSpinner(){
    	listselect = (Spinner) findViewById(R.id.listspinner);
        ArrayList<String> lists = mDictionaryAdapter.getLists();
        ArrayAdapter<String> listsAdapter = new ArrayAdapter<String>(this,
        			android.R.layout.simple_spinner_item, lists);
        listsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listselect.setAdapter(listsAdapter);
    	
    }
    public void openList(ArrayList<Integer> ids, NotecardFragment displayed){
	    Toast.makeText(NotecardActivity.this,
		"Button Click : " + 
                "\nList: "+ String.valueOf(listselect.getSelectedItem()), 
			Toast.LENGTH_SHORT).show();
	    Cursor c = mDictionaryAdapter.fetchEntriesbyId(ids);
	    getDisplayedNotecard().setCursor(c); 
	    
    }
    
    public void openSettings(View v){
	    Toast.makeText(NotecardActivity.this,"Clicked Setting",Toast.LENGTH_SHORT).show();
	    FragmentManager fragmentManager = getFragmentManager();
    	settingsDialog = new SettingsDialogFragment();
    	settingsDialog.show(fragmentManager, "dialog");
        
    }
    
    public void initializeFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NotecardFragment notecardDisplay = new NotecardFragment();
        //Fragment notecardDisplay = fragmentManager.findFragmentById(R.id.fragment_content);
        fragmentTransaction.add(R.id.fragment, notecardDisplay);
        fragmentTransaction.commit();
        setDisplayedNotecard(notecardDisplay);
        
    }
    
    public void deleteList(View v){
    	mDictionaryAdapter.deleteList(listselect.getSelectedItem().toString());
    	setSpinner();
    }
    
    private void setDisplayedNotecard(NotecardFragment in){
    	currentNotecard = in;
    }
    
    public NotecardFragment getDisplayedNotecard(){
    	return currentNotecard;
    }
    
}

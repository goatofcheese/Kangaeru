package edu.clemson.kangaeru;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import edu.clemson.kangaeru.CompoundFragment.GuessEvaluator;

public class CompoundActivity extends Activity implements GuessEvaluator{

	private Spinner listselect;
	private DictionaryAdapter mDictionaryAdapter;
	private CompoundFragment compoundFragment;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound);
        mDictionaryAdapter = new DictionaryAdapter(this);
        mDictionaryAdapter.open();
       
        initializeFragment();
        
        //Spinner stuff
    	listselect = (Spinner) findViewById(R.id.listspinner2);
        ArrayList<String> lists = mDictionaryAdapter.getLists();
        ArrayAdapter<String> listsAdapter = new ArrayAdapter<String>(this,
        			android.R.layout.simple_spinner_item, lists);
        listsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listselect.setAdapter(listsAdapter);
        listselect.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            	final String selected = parent.getItemAtPosition(pos).toString();
            	final ArrayList<Integer> ids = mDictionaryAdapter.checkNotecard(selected);
                Button s = (Button) findViewById(R.id.listselect2);
                s.setOnClickListener(new View.OnClickListener(){
                	public void onClick(View v){
                		Cursor c = mDictionaryAdapter.fetchCompoundsbyKanji(ids);
                        compoundFragment.setCursor(c);	
                	}
                }); 
            }

			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
        });
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_compound, menu);
        return true;
    }
    
    private void initializeFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        compoundFragment = new CompoundFragment();
        fragmentTransaction.add(R.id.fragment2, compoundFragment);
        fragmentTransaction.commit();	
    }

	public void updateImage(boolean success) {
		String answer;
		if(success)
			answer = "success!";
		else
			answer = "failure";
		
		Toast.makeText((Activity) this, 
				answer,
				Toast.LENGTH_LONG).show();	

		//Make that frog jump;
	}
    
}

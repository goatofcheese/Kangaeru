package edu.clemson.kangaeru;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class NotecardActivity extends Activity {

	private Spinner listselect;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Notecards");
        setContentView(R.layout.activity_notecard);
        
        //Spinner Stuff
        listselect = (Spinner) findViewById(R.id.listspinner);
        List<String> list = new ArrayList<String>();
        list.add("Fakelist 1");
        list.add("Fakelist 2");
        list.add("Fakelist 3");
        ArrayAdapter<String> fakeAdapter = new ArrayAdapter<String>(this,
        			android.R.layout.simple_spinner_item, list);
        fakeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listselect.setAdapter(fakeAdapter);
        listselect.setOnItemSelectedListener(new NotecardListOnItemSelectedListener());
        Button s = (Button) findViewById(R.id.listselect);
        s.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		openList(v);
        	}
        });
        //End of Spinner stuff 
       
        //Notecard Fragment
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NotecardFragment notecardDisplay = new NotecardFragment();
        //Fragment notecardDisplay = fragmentManager.findFragmentById(R.id.fragment_content);
        fragmentTransaction.add(R.id.fragment, notecardDisplay);
        fragmentTransaction.commit();
        
        //Return button
        Button b = (Button) findViewById(R.id.returnbutton);
        b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i;
				i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_notecard, menu);
        return true;
    }
    
    public void openList(View v){
	    Toast.makeText(NotecardActivity.this,
		"Button Click : " + 
                "\nList: "+ String.valueOf(listselect.getSelectedItem()), 
			Toast.LENGTH_SHORT).show();
    }
    
    public void openSettings(View v){
    	
    }
}

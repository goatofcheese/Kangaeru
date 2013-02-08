package edu.clemson.kangaeru;


import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity{
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.activity_main);
		final ImageButton welcomeImageButton = (ImageButton) findViewById(R.id.welcomeImageButton);	
		welcomeImageButton.setBackgroundResource(R.drawable.introfrog);

		//Check to see if the user is coming back from an inner activity
		  //if so, do not show the intro screen
		Bundle b = getIntent().getExtras();
		if(b != null) {
			boolean revisit = b.getBoolean("REVISIT");
			if (revisit) { 
				welcomeImageButton.setVisibility(View.INVISIBLE);
				
			}
		}
		
		//If the user touches anywhere on the intro screen, the picture goes away to reveal the main menu
		welcomeImageButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
            	welcomeImageButton.setVisibility(View.INVISIBLE);
            }
        });


		
        //storing string resources into Array
        ListView listView = (ListView) findViewById(R.id.list);
        String[] activities_list = getResources().getStringArray(R.array.activities_list);
        TypedArray resources = getResources().obtainTypedArray(R.array.icon_list);
        List<String> act_list =(Arrays.asList(activities_list));
        
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        KangaeruListAdapter adapter = new KangaeruListAdapter(this, R.layout.single_list_item_view, resources, act_list);


   
        // Assign adapter to ListView
        listView.setAdapter(adapter); 
        

        listView.setOnItemClickListener(new OnItemClickListener() {
        	  
        	  public void onItemClick(AdapterView<?> parent, View view,
        	    int position, long id) {
                  Intent i;
                  switch(position){
                  	case 0:	//Dictionary
                  		i = new Intent(getApplicationContext(), DictionaryActivity.class);
                  		startActivity(i);
                  		break;
                  	case 1: //Complete the compound
                  		i = new Intent(getApplicationContext(), NotecardActivity.class);
                  		startActivity(i);
                  		break;
                  	case 2: //Notecard
                  		i = new Intent(getApplicationContext(), NotecardActivity.class);
                  		startActivity(i);
                  		break;
                  	default:
                  		System.err.println("something is wrong");
                  }
        	  }
        });
        
        //Loading kanji database
        SingletonDBHelper.initializeHelper(this);
        
        //Non-functional JNI stuffs
        //Zinnia z = new Zinnia(getAssets());

        //String input = "(character (width 1000)(height 1000)(strokes ((243 273)(393 450))((700 253)(343 486)(280 716)(393 866)(710 880))))";
        //byte binput[] = input.getBytes();
        //z.androidParse(z.buffer, binput, z.modelSize);
        
    }
    
}

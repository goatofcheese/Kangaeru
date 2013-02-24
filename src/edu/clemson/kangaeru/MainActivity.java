package edu.clemson.kangaeru;


import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);
        
        setContentView(R.layout.activity_main);
		final ImageButton welcomeImageButton = (ImageButton) findViewById(R.id.welcomeImageButton);	
		welcomeImageButton.setBackgroundResource(R.drawable.introduction);
        final AnimationDrawable introAnimation = (AnimationDrawable) welcomeImageButton.getBackground();
        introAnimation.start();

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
            	introAnimation.stop();
            	introAnimation.setVisible(false, false);
            	welcomeImageButton.setVisibility(View.INVISIBLE);
            }
        });

		TextView instructions = (TextView) findViewById(R.id.instructions);
		instructions.setText("doodle doo dee doo");

        //storing string resources into Array
        GridView gridView = (GridView) findViewById(R.id.menuGrid);
        String[] activities_list = getResources().getStringArray(R.array.activities_list);
        TypedArray resources = getResources().obtainTypedArray(R.array.icon_list);
        List<String> act_list =(Arrays.asList(activities_list));
        
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Fourth - the Array of data

        KangaeruMainMenuAdapter adapter = new KangaeruMainMenuAdapter(this, activities_list);


   
        // Assign adapter to ListView
        gridView.setAdapter(adapter); 
        

        gridView.setOnItemClickListener(new OnItemClickListener() {
        	  
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
                  		i = new Intent(getApplicationContext(), CompoundActivity.class);
                  		startActivity(i);
                  		break;
                  	case 3: //WritingPractice
                  		i = new Intent(getApplicationContext(), WritingPractice.class);
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
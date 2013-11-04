package edu.clemson.kangaeru;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;


public class MainActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);
        
        setContentView(R.layout.activity_main);
		 		

        GridView gridView = (GridView) findViewById(R.id.menuGrid);
        String[] activities_list = getResources().getStringArray(R.array.activities_list);
        
        // Define a new Adapter
        KangaeruMainMenuAdapter adapter = new KangaeruMainMenuAdapter(this, activities_list);
   
        // Assign adapter to ListView
        gridView.setAdapter(adapter); 
        
        /**********************************
         * 
         * Debug block to check screensize (Shamelessly pulled from stackoverflow)
         * 
         **********************************/
        
        MyApp appState = ((MyApp)getApplicationContext());

        switch(appState.getState()) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                Toast.makeText(this, "Large screen",Toast.LENGTH_LONG).show();
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                Toast.makeText(this, "Normal screen",Toast.LENGTH_LONG).show();
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                Toast.makeText(this, "Small screen",Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(this, "Screen size is neither large, normal or small" , Toast.LENGTH_LONG).show();
        }

        /**********************************
         * 
         * End block
         * 
         **********************************/
        
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
        
    }
    
}
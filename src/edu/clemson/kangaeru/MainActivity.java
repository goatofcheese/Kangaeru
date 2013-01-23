package edu.clemson.kangaeru;

import java.util.Arrays;
import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import java.lang.String;

public class MainActivity extends ListActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     	//storing string resources into Array
        String[] activities_list = getResources().getStringArray(R.array.activities_list);
        TypedArray resources = getResources().obtainTypedArray(R.array.icon_list);
        
        List<String> act_list =(Arrays.asList(activities_list));
        // Binding resources Array to ListAdapter
        this.setListAdapter(new KangaeruListAdapter(this, R.layout.activity_single_list_item, resources, act_list));
        
        ListView lv = getListView();
        
        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
  
              // Launching new Activity on selecting item
              Intent i;
              switch(position){
              	case 0:	//Dictionary
              		i = new Intent(getApplicationContext(), DictionaryActivity.class);
              		startActivity(i);
              		break;
              	case 1: //Complete the sentence
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
        
        //Non-functional JNI stuffs
        //Zinnia z = new Zinnia(getAssets());

        //String input = "(character (width 1000)(height 1000)(strokes ((243 273)(393 450))((700 253)(343 486)(280 716)(393 866)(710 880))))";
        //byte binput[] = input.getBytes();
        //z.androidParse(z.buffer, binput, z.modelSize);
        
    }
}

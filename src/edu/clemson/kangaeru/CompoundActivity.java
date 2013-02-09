package edu.clemson.kangaeru;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CompoundActivity extends Activity {

	private Spinner listselect;
	private DictionaryAdapter mDictionaryAdapter;
	private TextView remainder;
	private TextView prompt1, prompt2;
	
	private class CompoundFragment extends Fragment{
		
		private Cursor c;
		private String empty = "          ";
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				   Bundle savedInstanceState){
			View v = inflater.inflate(R.layout.compoundfragment, container, false);
			remainder = (TextView) v.findViewById(R.id.remainder);
			remainder.setText("boop");
			prompt1 = (TextView) v.findViewById(R.id.prompt1);
			prompt2 = (TextView) v.findViewById(R.id.prompt2);
			return v;
		}
		
		public void setCursor(Cursor x){
			c = x;
			c.moveToFirst();
			setStrings();
		}
		
		private void setStrings(){
			System.err.println("column #: " + c.getColumnCount());
			prompt1.setText(c.getString(1));
			prompt2.setText(c.getString(2));
		}
	}
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound);
        mDictionaryAdapter = new DictionaryAdapter(this);
        mDictionaryAdapter.open();
       
        
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
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        CompoundFragment x = new CompoundFragment();
                        fragmentTransaction.add(R.id.fragment2, x);
                        fragmentTransaction.commit();
                        x.setCursor(c);
                		
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
}

package edu.clemson.kangaeru;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

@SuppressLint("ValidFragment")
public class FilterDialogFragment extends DialogFragment{

	public interface QueryTaker{
		public void takeQuery(String query);
	}
	
	private Activity parent;
	private Spinner book;
	private Spinner chapter;
	private String[] booksList;
	private String[] chaptersList;
	
	
	@Override
	public void onAttach(Activity a){
		super.onAttach(a);
		parent = a;
		booksList = a.getResources().getStringArray(R.array.books);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
		final Activity c = parent;
		View v = inflater.inflate(R.layout.dialog_filter, container, false);
		book = (Spinner) v.findViewById(R.id.bookSpinner);
		chapter = (Spinner) v.findViewById(R.id.chapterSpinner);
		
		ArrayAdapter<String> bookAdapter = new ArrayAdapter<String> (parent, 
		        android.R.layout.simple_spinner_item, booksList);
		book.setAdapter(bookAdapter);
		
		
		book.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				switch(position){
					case 0:
						chaptersList = c.getResources().getStringArray(R.array.yookosoChapters);
						break;
					case 1:
						chaptersList = c.getResources().getStringArray(R.array.genkiChapters);
						break;
					case 2:
						chaptersList = c.getResources().getStringArray(R.array.nakamaChapters);
						break;
				}
				System.err.println("The Chapters");
				for(String s : chaptersList)
					System.err.println(s);
				ArrayAdapter<String> chapterAdapter = new ArrayAdapter<String> (parent, 
				        android.R.layout.simple_spinner_item, chaptersList);
				chapter.setAdapter(chapterAdapter);				
				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		return v;
	}
	
}

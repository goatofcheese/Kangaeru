package edu.clemson.kangaeru;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ResultsDialogFragment extends DialogFragment {

	private WritingPractice parent;
	private ListView resultsList;
	
	@Override 
	public void onAttach(Activity a){
		super.onAttach(a);
		parent = (WritingPractice) a;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
		View v = inflater.inflate(R.layout.dialog_results, container, false);
		
		SparseArray<Bitmap> results = parent.getResults();
		List<Integer> keyList = new ArrayList<Integer>(results.size());
		List<Bitmap> bitmapList = new ArrayList<Bitmap>(results.size());
		for(int i = 0; i < results.size(); i++){
			keyList.add(results.keyAt(i));
			bitmapList.add(results.valueAt(i));
		}
		
		ResultsAdapter adapter = new ResultsAdapter(parent, keyList, bitmapList);
		resultsList = (ListView) v.findViewById(R.id.resultsList);
		resultsList.setAdapter(adapter);

		
		return v;
	}
	
}

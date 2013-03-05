package edu.clemson.kangaeru;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import edu.clemson.kangaeru.CompoundFragment.GuessEvaluator;

public class CompoundActivity extends Activity implements GuessEvaluator{

	private Spinner listselect;
	private DictionaryAdapter mDictionaryAdapter;
	private CompoundFragment compoundFragment;
	private Cursor currentCursor;
	private ImageView resultImage;
	private ProgressBar progressBar;
	private AnimationDrawable resultAnimation;
	private int successCount = 0;
	
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
                		currentCursor = mDictionaryAdapter.fetchCompoundsbyKanji(ids);
                        compoundFragment.setCursor(currentCursor);
                        if(progressBar != null)
                        	progressBar.setProgress(0);
                        resultImage.setBackgroundResource(R.drawable.thinking);
                        resultAnimation = (AnimationDrawable) resultImage.getBackground();
                        resultAnimation.start();
                	}
                }); 
            }

			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
        });
        
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(size.x/2, size.x/2);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        resultImage = (ImageView) findViewById(R.id.resultImage);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        resultImage.setLayoutParams(params);
        resultImage.setBackgroundResource(R.drawable.thinking);
        resultAnimation = (AnimationDrawable) resultImage.getBackground();
        resultAnimation.start();
        
        
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
        compoundFragment.setGuessAmount(1);
        fragmentTransaction.add(R.id.fragment2, compoundFragment);
        fragmentTransaction.commit();	
    }

	public void updateImage(boolean success, boolean unique) {
		String answer;
		if(success){
			answer = "success!";
			if(unique)
				successCount++;
			double progress = ((double)successCount/currentCursor.getCount());
			progress = progress * 100;
			progressBar.setProgress((int) progress);
	        resultImage.setBackgroundResource(R.drawable.sparkles);
	        resultAnimation = (AnimationDrawable) resultImage.getBackground();
	        if(!resultAnimation.isRunning())
	        	resultAnimation.start();
		}
		else{
			answer = "failure";
			if(resultAnimation != null)
				if(resultAnimation.isRunning())
					resultAnimation.stop();
			resultImage.setBackgroundResource(R.drawable.failure);
			resultAnimation = (AnimationDrawable) resultImage.getBackground();
	        if(!resultAnimation.isRunning())
	        	resultAnimation.start();
		}
		
		/*if(successCount >= currentCursor.getCount()){
			Toast.makeText((Activity) this, 
				"Great Success! Wahaho",
				Toast.LENGTH_LONG).show();
		}*/
			
		//Make that frog jump;
	}

	public void resetImage() {
		resultImage.setBackgroundResource(R.drawable.thinking);
		resultAnimation = (AnimationDrawable) resultImage.getBackground();
        if(!resultAnimation.isRunning())
        	resultAnimation.start();
	}
    
}

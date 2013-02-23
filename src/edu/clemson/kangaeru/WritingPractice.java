package edu.clemson.kangaeru;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class WritingPractice extends Activity {

	private FingerPaint practice;
	private DictionaryAdapter mDictionaryAdapter;
	private WritingFragment writingFragment;
	private Spinner listselect;
	private Cursor currentCursor;
	private TextView clock;
	//private ArrayList<Bitmap> BMArray;
	private SparseArray<Bitmap> BMArray;
	private ImageView tempView;
	private ResultsDialogFragment resultsDialog;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_practice);
        practice = (FingerPaint) findViewById(R.id.practice);
        resizeView();
        
        mDictionaryAdapter = new DictionaryAdapter(this);
        mDictionaryAdapter.open();
       
        initializeFragment();
        
        //Spinner stuff
    	listselect = (Spinner) findViewById(R.id.listspinner3);
        ArrayList<String> lists = mDictionaryAdapter.getLists();
        ArrayAdapter<String> listsAdapter = new ArrayAdapter<String>(this,
        			android.R.layout.simple_spinner_item, lists);
        listsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listselect.setAdapter(listsAdapter);
        listselect.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            	final String selected = parent.getItemAtPosition(pos).toString();
            	final ArrayList<Integer> ids = mDictionaryAdapter.checkNotecard(selected);
                Button s = (Button) findViewById(R.id.listselect3);
                s.setOnClickListener(new View.OnClickListener(){
                	public void onClick(View v){
                		currentCursor = mDictionaryAdapter.fetchEntriesbyId(ids);
                        writingFragment.setCursor(currentCursor);
                        BMArray = new SparseArray<Bitmap>(currentCursor.getCount());
                	}
                }); 
            }

			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
        });

        tempView = (ImageView) findViewById(R.id.tempView);
        tempView.setImageResource(R.drawable.frog7);
        clock = (TextView) findViewById(R.id.clock);
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                clock.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                clock.setText("done!");
                System.err.println("ending size: " + BMArray.size());
                openResults();
            }
         }.start();
         
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_writing_practice, menu);
        return true;
    }
    
    public void resizeView(){
    	Point size = new Point();
    	getWindowManager().getDefaultDisplay().getSize(size);
    	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((size.x/2), (size.x/2));
    	params.addRule(RelativeLayout.CENTER_IN_PARENT);
    	practice.setLayoutParams(params);
    }
    
    public void clearCanvas(View v){
    	practice.clearCanvas();
    }
    
    private void initializeFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        writingFragment = new WritingFragment();
        fragmentTransaction.add(R.id.fragment3, writingFragment);
        fragmentTransaction.commit();	
    }
    
    public void saveBitmap(int index){
    	practice.setDrawingCacheEnabled(true);
    	Bitmap bm = Bitmap.createBitmap(practice.getDrawingCache());
    	BMArray.append(index, bm);
//    	tempView.setImageBitmap(bm);
//    	tempView.postInvalidate();
    	practice.setDrawingCacheEnabled(false);
    }

    public SparseArray<Bitmap> getResults(){
    	return BMArray;
    }
    
    private void openResults(){
    	resultsDialog = new ResultsDialogFragment();
    	resultsDialog.show(getFragmentManager(), "dialog");	
    }
    
}

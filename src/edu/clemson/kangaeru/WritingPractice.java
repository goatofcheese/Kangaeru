package edu.clemson.kangaeru;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

public class WritingPractice extends Activity {

	private FingerPaint practice;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_practice);
        practice = (FingerPaint) findViewById(R.id.practice);
        resizeView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_writing_practice, menu);
        return true;
    }
    
    public void resizeView(){
    	Point size = new Point();
    	getWindowManager().getDefaultDisplay().getSize(size);
    	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((size.y/5), (size.y/5));
    	params.addRule(RelativeLayout.CENTER_IN_PARENT);
    	practice.setLayoutParams(params);
    }
    
    public void clearCanvas(View v){
    	practice.clearCanvas();
    }
}

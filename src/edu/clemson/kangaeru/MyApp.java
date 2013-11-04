/****************
 * 
 * This class is globally accessible from any context. Use (MyApp)<Context>.getApplicationContext() to get a copy of it,
 * and then retrieve whatever you need. At present all it stores is the screensize.
 * 
 * Solution found at stackoverflow.com/questions/708012/android-how-to-declare-gloval-variables
 * 
 ****************/

package edu.clemson.kangaeru;

import android.app.Application;
import android.content.res.Configuration;

public class MyApp extends Application {
	private int screenSize;
	
	public int getState() {
		return this.screenSize;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
	}
	
}

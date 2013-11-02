package edu.clemson.kangaeru;

import android.content.Context;

public class SingletonDBHelper {

	private static DatabaseHelper _helper = null;
	
	public static void initializeHelper(Context ctx){
		if(_helper == null)
			_helper = new DatabaseHelper(ctx);
	}
	public static DatabaseHelper getHelper(){
		return _helper;
	}
	
	
}

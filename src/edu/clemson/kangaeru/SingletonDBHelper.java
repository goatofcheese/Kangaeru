package edu.clemson.kangaeru;

import java.io.IOException;

import android.content.Context;

public class SingletonDBHelper {

	private static DatabaseHelper _helper = null;
	
	public static void initializeHelper(Context ctx){
		_helper = new DatabaseHelper(ctx);
    	try {
        	_helper.createDataBase();
	 	} catch (IOException ioe) {
	 		throw new Error("Unable to create database");
	 	}
	}
	public static DatabaseHelper getHelper(){
		return _helper;
	}
	
	
}

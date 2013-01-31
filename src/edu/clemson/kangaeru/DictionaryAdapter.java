package edu.clemson.kangaeru;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;



public class DictionaryAdapter {
	
    private DatabaseHelper mDbHelper;
    public static final String DATABASE_TABLE = "kanji";
    public static final String KEY_ROWID = "_id";
    public static final String KEY_SQUIGGLE = "squiggle";
    public static final String KEY_READINGS = "readings";
    public static final String KEY_SENTENCE = "sentence";
    public static final String KEY_NOTECARD = "isnotecard";

    private final Context mCtx;
    
    public DictionaryAdapter(Context ctx){
    	mCtx = ctx;
    	mDbHelper = SingletonDBHelper.getHelper();
/*    	try {
        	mDbHelper.createDataBase();
	 	} catch (IOException ioe) {
	 		throw new Error("Unable to create database");
	 	}*/
    }

    public DictionaryAdapter open() throws SQLException {
     	try {
     		mDbHelper.openDataBase();
     	}catch(SQLException sqle){
     		throw sqle;
     	}
     	
        return this;
    }

    public void close() {
        mDbHelper.close();
    }
    
    public Cursor fetchAllEntries() {

        return mDbHelper.query(DATABASE_TABLE, null, null, null, null, null, null);
    }

    public Cursor fetchEntry(long rowId) throws SQLException {

        Cursor mCursor =
            mDbHelper.query(DATABASE_TABLE, null, KEY_ROWID + "=" + rowId, null,
                    null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
    public void setNotecard(long id, String list){
    	ContentValues cv = new ContentValues();
    	cv.put(KEY_ROWID, list);
    	mDbHelper.update(DATABASE_TABLE, cv, KEY_ROWID + " = ?", new String[]{String.valueOf(id)});
    }
    
    public ArrayList<String> getLists(){
    	ArrayList<String> ret = new ArrayList<String>(5);
    	
    	Cursor c = mDbHelper.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
    	int i = 0;
    	
    	System.err.println("number of things in cursor:" + c.getCount());
    	if(c.moveToFirst()){
    		while(!c.isAfterLast()){
        		String toParse = c.getString(i);
        		if((toParse.compareTo("android_metadata") != 0) && (toParse.compareTo("kanji") != 0))
        			ret.add(toParse);
        		c.moveToNext();
    		}
    	}
    	return ret;
    }
    
    public boolean addList(String name){
    	System.err.println("I should add " + name + "!");
    	return false;
    }
}

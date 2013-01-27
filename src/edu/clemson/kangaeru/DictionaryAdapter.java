package edu.clemson.kangaeru;

import java.io.IOException;
import java.io.InputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



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
    
    public void setNotecard(long id, int value){
    	System.err.println("Uhhh did I get in here? The id is: " + id);
    	ContentValues cv = new ContentValues();
    	cv.put(KEY_NOTECARD, value);
    	String where = "_id=" + value;
    	mDbHelper.update(DATABASE_TABLE, cv, where, null);
    }

    
}

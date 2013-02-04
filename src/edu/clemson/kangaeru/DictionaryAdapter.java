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
    
    public Cursor fetchEntriesbyId(ArrayList<Integer> ids) throws SQLException {
    	int i = 0;
    	String sql = "SELECT * FROM  kanji WHERE _id=";
    	for(Integer id: ids){
    		if(i == 0)
    			sql += id.toString();
    		else
    			sql += " OR _id=" + id.toString();
    		i++;
    	}
    	Cursor mCursor = mDbHelper.rawQuery(sql, null);
    	return mCursor;
    }
    
    public void addNotecard(long id, String list){
    	ContentValues cv = new ContentValues();
    	cv.put(KEY_ROWID, id);
    	mDbHelper.insert(list, null, cv);
    }
    
    public ArrayList<Integer> checkNotecard(String table){
    	Cursor c = mDbHelper.rawQuery("SELECT * FROM " + table, null);
    	ArrayList<Integer> ret = new ArrayList<Integer>();
    	if(c.moveToFirst()){
    		while(!c.isAfterLast()){
        		ret.add(c.getInt(0));
        		c.moveToNext();
    		}
    	}
    	return ret;
    }
    
    public ArrayList<String> getLists(){
    	ArrayList<String> ret = new ArrayList<String>(5);
    	Cursor c = null;
    	if(mDbHelper != null)
    		c = mDbHelper.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
    	else
    		System.err.println("null helper");
    	
    	if(c.moveToFirst()){
    		while(!c.isAfterLast()){
        		String toParse = c.getString(0);
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

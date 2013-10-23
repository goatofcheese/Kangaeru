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
    public static final String KEY_COMPOUND = "compound";
    public static final String KEY_NOTECARD = "isnotecard";
    
    public DictionaryAdapter(Context ctx){
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
    

    public Cursor fetchEntriesbyKanji(ArrayList<String> ids) throws SQLException {
    	int i = 0;
    	if (ids.size() < 1)
    		return null;
    	String sql = "SELECT * FROM  kanji WHERE squiggle=";
    	for(String id: ids){
    		if(i == 0)
    			sql += "'" + id + "'";
    		else
    			sql += " OR squiggle=" + "'" + id + "'";
    		i++;
    	}
    	Cursor mCursor = mDbHelper.rawQuery(sql, null);
    	
    	if(i > 0)
    		return mCursor;
    	else
    		return null;
    }
    
    public Cursor fetchCompoundsbyKanji(ArrayList<String> ids) throws SQLException {
    	int i = 0;
    	if (ids.size() < 1)
    		return null;
    	String sql = "SELECT compounds.* FROM  compounds, kanjicompounds WHERE (kanjicompounds.kanji=";
    	for(String id: ids){
    		if(i == 0)
    			sql += "'" + id + "'";
    		else
    			sql += " OR kanjicompounds.kanji='" + id + "'";
    		i++;
    	}
    	sql += ") AND kanjicompounds.compound = compounds.compound";
    	Cursor mCursor = mDbHelper.rawQuery(sql, null);
    	
    	if(i > 0)
    		return mCursor;
    	else
    		return null;
    }
    
    public void addNotecard(long id, String list){
    	ContentValues cv = new ContentValues();
    	cv.put(KEY_ROWID, id);
    	mDbHelper.insert(list, null, cv);
    	String sql = "INSERT INTO " + list + " (kanji) SELECT squiggle FROM kanji WHERE kanji._id=" + id;
    	mDbHelper.rawQuery(sql, null);
    }
    
    public ArrayList<String> checkNotecard(String table){
    	Cursor c = mDbHelper.rawQuery("SELECT * FROM " + table, null);
    	ArrayList<String> ret = new ArrayList<String>();
    	if(c.moveToFirst()){
    		while(!c.isAfterLast()){
        		ret.add(c.getString(0));
        		c.moveToNext();
    		}
    	}
    	return ret;
    }
    
    public Cursor filter(String filter){
    	return mDbHelper.rawQuery(filter, null);
    	
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
        		if((toParse.compareTo("android_metadata") != 0) && (toParse.compareTo("kanji") != 0)
        				&& (toParse.compareTo("compounds") != 0) && (toParse.compareTo("kanjicompounds") != 0))
        			ret.add(toParse);
        		c.moveToNext();
    		}
    	}
    	return ret;
    }
    
    public boolean addList(String name){
    	System.err.println("start name: " + name);
    	if(name.compareTo("") == 0)
    		return false;
    	Cursor c = mDbHelper.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
    	if(c.moveToFirst()){
    		while(!c.isAfterLast()){
    			String check = c.getString(0);
    			if(check.compareTo(name) == 0)
    				return false;
    			c.moveToNext();
    		}
    	}
    	name = name.replaceAll(" ", "_");
    	System.err.println("end name: " + name);
    	mDbHelper.execSQL("CREATE TABLE IF NOT EXISTS "+ name + " (kanji TEXT)");
    	return true;
    }
    
    public void deleteList(String name){
    	mDbHelper.execSQL("DROP TABLE IF EXISTS " + name);
    }
    
}

package edu.clemson.kangaeru;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DictionaryAdapter {
	
	public static final String KEY_SQUIGGLE = "squiggle";
	public static final String KEY_READINGS = "readings";
	public static final String KEY_SENTENCE = "sentence";
	public static final String KEY_ROWID = "_id";
	
    private static final String TAG = "DictionaryAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE =
        "create table kanji (_id integer primary key autoincrement, "
        + "squiggle text not null, readings text not null, sentence text not null);";

    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "kanji";
    private static final int DATABASE_VERSION = 2;

    private final Context mCtx;
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        	
            db.execSQL(DATABASE_CREATE);
            
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS kanji");
            onCreate(db);
        }
    }
    
    public DictionaryAdapter(Context ctx){
    	mCtx = ctx;
    }

    public DictionaryAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public long createEntry(String squiggle, String readings, String sentence) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_SQUIGGLE, squiggle);
        initialValues.put(KEY_READINGS, readings);
        initialValues.put(KEY_SENTENCE, sentence);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }
    
    public Cursor fetchAllEntries() {

        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_SQUIGGLE,
                KEY_READINGS, KEY_SENTENCE}, null, null, null, null, null);
    }


    
}

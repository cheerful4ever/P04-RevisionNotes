package com.myapplicationdev.android.p04_revisionnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

	//TODO Define the Database properties
	private static final String DATABASE_NAME = "";
	private static final int DATABASE_VERSION = ;


	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//TODO CREATE TABLE Note
		String createNoteTableSql = "CREATE TABLE" + TABLE_NOTE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOTE_CONTENT + " TEXT, " + COLUMN_STARS + " INTEGER)";
		 db.execSQL(createNoteTableSql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
		onCreate(db);
	}

	public void insertNote(String noteContent, int stars) {
		//TODO insert the data into the database
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(COLUMN_NOTE_CONTENT, noteContent);
		values.put(COLUMN_STARS, stars);

		db.insert(TABLE_NOTE, null, values);
		db.close();
	}

	public ArrayList<Note> getAllNotes() {
		//TODO return records in Java objects
		final ArrayList<Note> notes = new ArrayList<Note>() {
			String selectQuery = "SELECT " + COLUMN_ID + "," + COLUMN_NOTE_CONTENT + "," + COLUMN_STARS + " FROM " + TABLE_NOTE;

			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			if(cursor.movetoFirst()) {
				do{
					int id = cursor.getInt(0);
					String title = cursor.getString(1);
					int Stars = cursor.getInt(2);

					Note note = new Note(id, title, stars);
					notes.add(note);
				}
				while (cursor.moveToNext());

				cursor.close();
				db.close();
				return notes;
			}
		};
	}

    public ArrayList<String> getNoteContent() {
        //TODO return records in Strings


		// Create an ArrayList that holds String objects
        ArrayList<String> notes = new ArrayList<String>();
        // Select all the notes' content
        String selectQuery = "SELECT note_content from note";

        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);
        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row and returns true;
            // moveToNext() returns false when no more next row to move to
            do {
            	notes.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return notes;
    }
}

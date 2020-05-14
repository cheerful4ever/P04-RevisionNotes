package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

	ListView lvNote;
	ArrayAdapter aaNote;
	ArrayList<Note> notes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//TODO implement the Custom ListView
		Intent i = getIntent();

		lvNote = findViewById(R.id.lv);

		DBHelper db = new DBHelper(SecondActivity.this);
		notes = db.getAllNotes();
		db.close();

		aaNote = new RevisionNotesArrayAdapter(this, R.layout.row, notes);
		Log.d("yes","wtf");
		lvNote.setAdapter(aaNote);
	}


}

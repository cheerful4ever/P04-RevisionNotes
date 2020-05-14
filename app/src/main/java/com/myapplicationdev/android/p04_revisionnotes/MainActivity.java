package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    Button buttonInsert, buttonShowList;
    EditText editNote;
    RadioGroup radioGroupStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonInsert = findViewById(R.id.buttonInsertNote);
        buttonShowList = findViewById(R.id.buttonShowList);
        editNote = findViewById(R.id.editTextNote);
        radioGroupStars = findViewById(R.id.radioGroupStars);

        buttonInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                int selectedId = radioGroupStars.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                int value = parseInt(radioButton.getText().toString());
                // Insert a task
                db.insertNote(editNote.getText().toString(), value);
                db.close();

                editNote.setText("");
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
            }
        });

        buttonShowList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }
}

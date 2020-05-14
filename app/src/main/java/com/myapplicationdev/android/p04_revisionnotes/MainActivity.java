package com.myapplicationdev.android.p04_revisionnotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        public void onClick(View view) {
            DBHelper dbHelper = new DBHelper(MainActivity.this);

            dbHelper.insertNote("Revision #1", 4);
            dbHelper.insertNote("Record #2", 5);

            dbHelper.close();
        }
    }
}

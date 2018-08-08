package com.example.trapp.savedatasqlitedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trapp.savedatasqlitedb.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    com.example.trapp.savedatasqlitedb.DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText editText;

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText= (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new com.example.trapp.savedatasqlitedb.DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String newEntry = editText.getText().toString();
                if (editText.length() != 0){
                    AddData(newEntry);
                } else {
                    toastMessage("You must put sth in the text field");
                }
            }


        });

        btnViewData.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                        /*com.example.trapp.savedatasqlitedb.ListDataActivity.class);*/
                startActivity(intent);
            }
        });

    }

    public void AddData(String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData){
            toastMessage("Juchaisa Daten erfolgreich eingefügt");
        } else{
            toastMessage("Etwas ist schiefgegangen");
        }

    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

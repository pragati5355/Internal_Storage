package com.example.internalstorage_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText Edtfilename,Edtcontentname;
    Button btnSave,btnRead;
    String Filename,Contentname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Edtfilename = findViewById(R.id.FileName);
        Edtcontentname = findViewById(R.id.ContentName);
        btnSave = findViewById(R.id.Savebtn);
        btnRead = findViewById(R.id.Readbtn);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Filename = Edtfilename.getText().toString().trim();
                Contentname = Edtcontentname.getText().toString().trim();

                FileOutputStream fos;

                try {
                    fos=openFileOutput(Filename, Context.MODE_PRIVATE);
                    fos.write(Contentname.getBytes());
                    fos.close();

                    Log.d("TAG","File Saved");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    FileInputStream fin = openFileInput(Filename);
                    int d;
                    String temp = "";
                    while ((d = fin.read()) != -1) {

                        temp = temp + Character.toString((char) d);
                    }
                    Log.d("TAG","Read File"   +temp);
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
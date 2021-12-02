package com.example.tugas_pengarsipan_file;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import  java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {


    private static final String STORETEXT = "lat7.txt";
    Button  btnSaveI, btnReadI, btnSaveE, btnReadE;
    EditText txtContent;
    String myData = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        btnSaveI = (Button) findViewById(R.id.btnsaveI);
        btnReadI = (Button) findViewById(R.id.btnreadI);
        btnSaveE = (Button) findViewById(R.id.btnsaveE);
        btnReadE = (Button) findViewById(R.id.btnreadE);
        txtContent = (EditText) findViewById(R.id.txtContent);


        btnSaveI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    OutputStreamWriter out = new OutputStreamWriter(openFileOutput(STORETEXT, 0));out.write(txtContent.getText().toString());
                    out.close();
                    Toast.makeText(getBaseContext(),  "Berhasil di Simpan di Internal!", Toast.LENGTH_SHORT).show();
                    txtContent.getText().clear();
                } catch (Throwable t) { Toast.makeText(getBaseContext(),
                        "Error: " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReadI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputStream in = openFileInput(STORETEXT);
                    if (in != null) {
                        InputStreamReader tmp = new InputStreamReader(in);
                        BufferedReader reader = new BufferedReader(tmp);

                        String str;
                        StringBuilder buf = new StringBuilder();

                        while ((str = reader.readLine()) != null) {
                            buf.append(str);
                        }
                        in.close();
                        txtContent.setText(buf.toString());

                    }
                } catch (java.io.IOException e) {

                }

            }
            });

        btnSaveE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File myFile = new File("/sdcard/lat7sd.txt");
                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                    myOutWriter.append(txtContent.getText()); myOutWriter.close();
                    fOut.close();

                    Toast.makeText(getBaseContext(), "Berhasil di Simpan di External!", Toast.LENGTH_SHORT).show();
                    txtContent.getText().clear();

                } catch (Exception e) {
                    Toast.makeText(getBaseContext(),
                            "Error: " + e.toString(), Toast.LENGTH_LONG).show();

                }
            }
        });

        btnReadE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    File myFile = new File("/sdcard/lat7sd.txt");
//                    myFile.createNewFile();
                    FileInputStream fis = new FileInputStream(myFile);
                    DataInputStream in = new DataInputStream(fis);
                    BufferedReader br =
                            new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    while ((strLine = br.readLine()) != null) {
                        myData = strLine;
//
                        txtContent.setText(myData);
                    }
                    in.close();
//

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }


}




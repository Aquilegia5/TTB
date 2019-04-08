package com.example.patrick.ttb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView loginButt;
    private EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Login Felder
        user = (EditText) findViewById(R.id.loginUname);
        pass = (EditText) findViewById(R.id.loginPass);

        // CardViews
        loginButt = (CardView) findViewById(R.id.loginButton);

        // Login Datei
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirst = prefs.getBoolean("FirstRun", true);
        if(isFirst) {
            Save("xyz82", 5);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("FirstRun", false);
            editor.commit();
        }

        // Add Listeners
        loginButt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch(v.getId()) {
            case R.id.loginButton:
                String inUser, inPass;
                inUser = user.getText().toString();
                inPass = pass.getText().toString();

                if(Open("xyz82") <= 0){
                    finish();
                }

                if(inUser.equals("A") && inPass.equals("L")){
                    i = new Intent(this, Calendar.class);
                    Save("xyz82", 5);
                    startActivity(i);
                } else {
                    int temp = (Open("xyz82"))-1;
                    Save("xyz82", temp);
                    String str;
                    if(temp >= 0) {
                        str = temp + " tries left.";
                    } else {
                        str = "No tries left.";
                    }
                    Toast.makeText(this, str, Toast.LENGTH_LONG).show();

                    user.setText("");
                    pass.setText("");
                }
                break;
            default: break;
        }
    }

    public void Save(String fileName, int number) {
        try {
            OutputStreamWriter out =
                    new OutputStreamWriter(openFileOutput(fileName, 0));
            out.write(""+number);
            out.close();
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public boolean FileExists(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    public int Open(String fileName) {
        int content = -23;
        if (FileExists(fileName)) {
            try {
                InputStream in = openFileInput(fileName);
                if ( in != null) {
                    InputStreamReader tmp = new InputStreamReader( in );
                    BufferedReader reader = new BufferedReader(tmp);
                    content = Integer.parseInt(reader.readLine());
                    in .close();
                }
            } catch (java.io.FileNotFoundException e) {} catch (Throwable t) {
                Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return content;
    }
}

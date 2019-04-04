package com.example.patrick.ttb;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView loginButt;
    private EditText user, pass;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Login Felder
        user = (EditText) findViewById(R.id.loginUname);
        pass = (EditText) findViewById(R.id.loginPass);

        // CardViews
        loginButt = (CardView) findViewById(R.id.loginButton);

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

                if(5-counter == 0){
                    //alert
                    finish();
                    System.exit(1);
                }

                if(inUser.equals("A") && inPass.equals("L")){
                    i = new Intent(this, Calendar.class);
                    startActivity(i);
                } else {
                    counter++;
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage((5-counter) + " tries left.");
                    builder.show();
                    user.setText("");
                    pass.setText("");
                }
                break;
            default: break;
        }
    }
}

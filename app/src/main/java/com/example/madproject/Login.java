package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madproject.R;

public class Login extends AppCompatActivity {

    EditText ed1,ed2;
    Button login,newUsr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed1 = findViewById(R.id.userName);
        ed2 = findViewById(R.id.pwd);
        login = findViewById(R.id.logbtn);//btn 1
        newUsr = findViewById(R.id.newAcc);//btn2

        newUsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,Register.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
    }
    public void login(){

        String uName = ed1.getText().toString();
        String pass = ed2.getText().toString();

        if(uName.equals("") || pass.equals("")){

            Toast.makeText(this,"Username or Password Blanck",Toast.LENGTH_LONG).show();
        }
        else if(null!=checkUser(uName,pass) ){

            String userDb = checkUser(uName,pass);

            Intent i = new Intent(Login.this,Menu.class);
            i.putExtra("uname",userDb);
            startActivity(i);
        }
        else{

            Toast.makeText(this, "User name or password not match",Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        }
    }

    public String checkUser(String uName,String pass){

        SQLiteDatabase db = openOrCreateDatabase("mad", Context.MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery("select id,uName,pass from user where uName = ? and pass = ? ",new String[]{uName,pass});
        if(cursor.getCount() > 0){

            cursor.moveToNext();
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            SharedPreferences.Editor sp = getSharedPreferences("username",MODE_PRIVATE).edit();
            sp.putString("uname",username);
            sp.apply();
            cursor.close();
            return username;

        }return null;

    }
}

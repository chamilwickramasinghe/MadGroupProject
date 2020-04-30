package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.os.Build.ID;

public class Register extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5;
    Button reg,update,delete;
    TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ed1 = findViewById(R.id.uName);
        ed2 = findViewById(R.id.phone);
        ed3 = findViewById(R.id.email);
        ed4 = findViewById(R.id.pass);
        ed5 = findViewById(R.id.cnfpass);
        reg = findViewById(R.id.regbtn);
        log = findViewById(R.id.LoginLink);
        update = findViewById(R.id.updbtn);
        delete = findViewById(R.id.delbtn);

        log.setMovementMethod(LinkMovementMethod.getInstance());

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this,Login.class);
                startActivity(i);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = openOrCreateDatabase("mad", Context.MODE_PRIVATE,null);
                Toast.makeText(getApplicationContext(),"Update successfuly",Toast.LENGTH_LONG).show();
                updateData();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = openOrCreateDatabase("mad", Context.MODE_PRIVATE,null);
                deleteData(ID);
                Toast.makeText(getApplicationContext(),"Delete successfuly",Toast.LENGTH_LONG).show();
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insert();

            }
        });
    }
    public void deleteData(String ID){

        try {
            String uName = ed1.getText().toString();
            String pass  = ed4.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("mad", Context.MODE_PRIVATE,null);
            db.execSQL("DELETE from user where()");


            if(uName.isEmpty() || pass.isEmpty()){

                Toast.makeText(Register.this," Error. Try again.",Toast.LENGTH_LONG).show();

            }
            else{


                Toast.makeText(this,"Delete Successfully",Toast.LENGTH_LONG).show();

                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");
                ed5.setText("");

                ed1.requestFocus();
            }
        }
        catch (Exception ex){

        }
    }
    public void updateData(){

                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");
                ed5.setText("");

                ed1.requestFocus();


    }

    public void insert(){
        try {
            String uName = ed1.getText().toString();
            String phone = ed2.getText().toString();
            String email = ed3.getText().toString();
            String pass  = ed4.getText().toString();
            String confpass = ed5.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("mad", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT,uName VARCHAR,phone VARCHAR,email VARCHAR,pass VARCHAR,confpass VARCHAR)");

            if(!pass.equals(confpass)){

                Toast.makeText(Register.this,"Password not maching",Toast.LENGTH_LONG).show();
            }
            else if(uName.isEmpty() || phone.isEmpty() || email.isEmpty() || pass.isEmpty() || confpass.isEmpty()){

                Toast.makeText(Register.this,"Register error. Register again.",Toast.LENGTH_LONG).show();

            }
            else{
                String sql = "insert into user (uName,phone,email,pass,confpass)values(?,?,?,?,?)";
                SQLiteStatement statement = db.compileStatement(sql);
                statement.bindString(1,uName);
                statement.bindString(2,phone);
                statement.bindString(3,email);
                statement.bindString(4,pass);
                statement.bindString(5,confpass);
                statement.execute();
                Toast.makeText(this,"Regester Successfully",Toast.LENGTH_LONG).show();

                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");
                ed5.setText("");

                ed1.requestFocus();
            }
        }
        catch (Exception ex){

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

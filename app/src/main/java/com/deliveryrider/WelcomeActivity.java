package com.deliveryrider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    private Button button1 ,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
         button1 = findViewById(R.id.btnRegister);
         button2 = findViewById(R.id.btnLogin);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register ();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login ();
            }
        });
    }
    public void Register (){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    public void Login (){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}

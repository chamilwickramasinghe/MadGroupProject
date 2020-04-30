package com.deliveryrider;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtName,txtEmail,txtPhone,txtPassword,txtConformPassword,txtID;
    Button btnSave,btnView,btnDelete,btnUpdate;
    DbHandler myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DbHandler(this);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtPassword = findViewById(R.id.txtPassword);
        txtConformPassword = findViewById(R.id.txtConformPassword);

        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (myDb.InsertData(txtName.getText().toString(),txtEmail.getText().toString(),txtPhone.getText().toString(),txtPassword.getText().
                       toString(),txtConformPassword.getText().toString())){
                   Toast.makeText(MainActivity.this,"Your details saved successfully",Toast.LENGTH_LONG).show();
               }
               else
                   Toast.makeText(MainActivity.this,"Insertion failed",Toast.LENGTH_LONG).show();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = myDb.selectAll();
                if(result != null){
                    while (result.moveToNext()){
                        Log.d("Delivery rider record","Delivery ride"+result.getString(0)+result.getString(1)+result.getString(2)+
                                result.getString(3)+result.getString(4)+result.getString(5));

                    }
                }
                else
                    showMessage();
            }


        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myDb.UpdateData(txtName.getText().toString(),txtEmail.getText().toString(),txtPhone.getText().toString(),txtPassword.getText().toString(),
                        txtConformPassword.getText().toString())){
                    Toast.makeText(MainActivity.this,"Your details Update successfully",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Updating failed",Toast.LENGTH_LONG).show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"You have to ask permission from admin",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Error");
        builder.setMessage("No DeliveryRiders available ");
        builder.show();
    }
}

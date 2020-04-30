package com.deliveryrider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHandler extends SQLiteOpenHelper {
    // define database
    public static final String Database_Name = "deliveryRider.db3";
    public static final String Table_Name = "DeliveryRiders_Table";
    public static final String col_1 = "ID";
    public static final String col_2 =  "Name";
    public static final String col_3 = "Email";
    public static final String col_4 = "Phone";
    public static final String col_5 = "Password";
    public static final String col_6 = "ConformPassword";

    public DbHandler(@Nullable Context context) {
        super(context,Database_Name, null, 1);
    }
    //SQLiteDatabase db = this.getWritableDatabase();
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Table_Name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Email VARCHAR, Phone INTEGER, Password VARCHAR, ConformPassword VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

    public boolean InsertData(String name, String email, String phone, String password,String conformPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,email);
        contentValues.put(col_4,Integer.parseInt(phone));
        contentValues.put(col_5,password);
        contentValues.put(col_6,conformPassword);
        long result = db.insert(Table_Name,null,contentValues);
        if (result == -1){
            return false;
        }
        else
            return true;
    }

    public Cursor selectAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Table_Name,null);
        return cursor;

    }
   public boolean UpdateData(String name, String email, String phone, String password,String conformPassword){
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();

       contentValues.put(col_2,name);
       contentValues.put(col_3,email);
       contentValues.put(col_4,Integer.parseInt(phone));
       contentValues.put(col_5,password);
       contentValues.put(col_6,conformPassword);
       long result = db.update(Table_Name,contentValues,"ID = ?",new String[]{name});
       if (result == -1){
           return false;
       }
       else
           return true;
   }



}

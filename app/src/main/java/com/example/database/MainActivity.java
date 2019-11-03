package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.edit);

        Button loadbtn = (Button)findViewById(R.id.load);
        loadbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    loadText();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Editable s = editText.getText();
                try {
                    saveText(s.toString());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });


    }

    //検索
    public void loadText() throws  IOException{
        MySQLiteOpenHelper dbhelper = new MySQLiteOpenHelper(this);
        String m = editText.getText().toString();
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String query = "select * from " + "memodata" +
                " where " + "MEMO" + " like '%" + m + "%';";

        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            editText.setText(c.getString(1) + "\n" + c.getString(2));
//        c.close();
        }
    }

    //保存
    public void saveText(String data) throws IOException{
        String t = Calendar.getInstance().getTime().toString();
        String m = editText.getText().toString();
        MySQLiteOpenHelper dbhelper = new MySQLiteOpenHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TITLE", t);
        values.put("MEMO", m);
        long ret;
        ret = db.insert("memodata", null, values);
        if(ret == -1){
            Toast.makeText(this, "Insert失敗", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Insert成功", Toast.LENGTH_SHORT).show();
        }
        editText.setText(null);
    }
}

package com.example.sqlliteexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText employeeidId, employeenameId, employeeageId;
    private Button addButton, updateButton, viewButton, deleteButton;
    private SQLiteDatabase db;
    private static final  String TABLE_NAME="details";
    private static final String NAME="name";
    private static final String AGE="age";
    private static final String ID="id";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeeidId=findViewById(R.id.employeeidId);
        employeenameId=findViewById(R.id.employeenameId);
        employeeageId=findViewById(R.id.employeeageId);
        addButton=findViewById(R.id.addButton);
        updateButton=findViewById(R.id.updateButton);
        viewButton=findViewById(R.id.viewButton);
        deleteButton=findViewById(R.id.deleteButton);


        db=openOrCreateDatabase("Database", Context.MODE_PRIVATE,null);             //Database=database name .this line hint
        //create table_table name(name type primary key,name2 data type);
        String table=" create table if not exists details"+TABLE_NAME+"("+ID+"int primary key, "+NAME+" varchar(20), "+AGE+"int);";
        db.execSQL(table);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id=employeeageId.getText().toString();
                String name=employeenameId.getText().toString();
                String age=employeeageId.getText().toString();

                if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(age))
                {
                    //insert into tablename vales('a','b','c');                         //values show assigned in this format
            String insert="insert into "+TABLE_NAME+" values ("+id+",'"+name+"',"+age+");";
            db.execSQL(insert);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"enter valid details",Toast.LENGTH_SHORT).show();
                }

            }
        });


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id=employeeageId.getText().toString();
                String name=employeenameId.getText().toString();
                String age=employeeageId.getText().toString();

                if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(name))
                {
                    //update tablename set name='name', age='age' ,id='id' where id='id' ;                        //values show assigned in this format
                    String update = " update "+TABLE_NAME+" set "+NAME+"='"+name+"',"+AGE+"="+age+","+ID+"="+id+" where "+ID+"="+id+";";
                    db.execSQL(update);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"enter valid details",Toast.LENGTH_SHORT).show();
                }

            }
        });


        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuilder stringBuilder=new StringBuilder();
                String show=" select * from "+TABLE_NAME+";";
                Cursor cursor = db.rawQuery(show, null);
                {

                    if (cursor.moveToFirst()) {
                        do {
                            stringBuilder.append(cursor.getInt(0));
                            stringBuilder.append(cursor.getString(1));
                            stringBuilder.append(cursor.getInt(2));
                            stringBuilder.append("\n");
                        }
                        while (cursor.moveToNext());

                    } else {
                        Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                        }

                }
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id=employeeageId.getText().toString();


                if (!TextUtils.isEmpty(id))
                {
                    //update tablename set name='name', age='age' ,id='id' where id='id' ;                        //values show assigned in this format
                    String delete= "delete from"+TABLE_NAME+" where "+ID+"="+id+";";

                    db.execSQL(delete);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"enter valid details",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
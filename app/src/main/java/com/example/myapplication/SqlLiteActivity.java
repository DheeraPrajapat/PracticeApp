package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.SqlLite.DbHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SqlLiteActivity extends AppCompatActivity {
    ListView listView;
    EditText itemName;
    Button insertItem;
    ArrayList arrayList;
    DbHelper dbHelper;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite);
        View view=findViewById(R.id.content);
        listView=findViewById(R.id.itemList);
        itemName=findViewById(R.id.itemName);
        insertItem=findViewById(R.id.insertButton);
        dbHelper=new DbHelper(SqlLiteActivity.this);
        arrayList=dbHelper.getAllText();

        adapter=new ArrayAdapter(SqlLiteActivity.this, android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(adapter);

        insertItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item_name=itemName.getText().toString().trim();
                if(item_name.equalsIgnoreCase("")){
                    Toast.makeText(SqlLiteActivity.this, "Please enter the name of item!", Toast.LENGTH_SHORT).show();
                }else{
                    if(dbHelper.addItem(item_name)){
                        itemName.setText("");
                        Toast.makeText(SqlLiteActivity.this, "Item inserted...", Toast.LENGTH_SHORT).show();
                        arrayList.clear();
                        arrayList.addAll(dbHelper.getAllText());
                        adapter.notifyDataSetChanged();
                        listView.invalidateViews();
                        listView.refreshDrawableState();
                    }
                }
            }
        });
    }
}
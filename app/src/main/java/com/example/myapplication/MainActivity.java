package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<PhoneList> phoneList=new ArrayList<>();
    RecyclerView recyclerView;
    Button button1,button2,button3,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=findViewById(R.id.button1);
        button3=findViewById(R.id.button3);
        button2=findViewById(R.id.button2);
        button4=findViewById(R.id.button4);
        recyclerView=findViewById(R.id.recyclerView);
        phoneList.add(new PhoneList("Vivo",R.drawable.ic_launcher_foreground));
        phoneList.add(new PhoneList("Oppo",R.drawable.ic_launcher_foreground));
        phoneList.add(new PhoneList("Nokia",R.drawable.ic_launcher_foreground));
        phoneList.add(new PhoneList("Apple",R.drawable.ic_launcher_foreground));
        phoneList.add(new PhoneList("Samsung",R.drawable.ic_launcher_foreground));

        PhoneAdapter adapter=new PhoneAdapter(MainActivity.this,phoneList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(adapter);

        button1.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this,QrCodeActivity.class));
        });
        button3.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this,SildeAnimation.class));
        });

        button2.setOnClickListener(v->{
            IntentIntegrator intentIntegrator=new IntentIntegrator(MainActivity.this);
            intentIntegrator.setBeepEnabled(true);
            intentIntegrator.setPrompt("For falsh use Volume up key!");
            intentIntegrator.setOrientationLocked(true);
            intentIntegrator.setCaptureActivity(Capture.class);
            intentIntegrator.initiateScan();
        });
        button4.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this,SqlLiteActivity.class));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result.getContents()!=null){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }else {
            Toast.makeText(MainActivity.this, "Opps something wrong!", Toast.LENGTH_SHORT).show();
        }
    }
}
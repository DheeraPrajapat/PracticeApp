package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QrCodeActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    ImageView qrCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        editText=findViewById(R.id.inputString);
        button=findViewById(R.id.generate);
        qrCode=findViewById(R.id.qrCodeImage);

        button.setOnClickListener(v -> {
            String str=editText.getText().toString().trim();
            MultiFormatWriter writer=new MultiFormatWriter();
            try {
                BitMatrix bitMatrix=writer.encode(str, BarcodeFormat.QR_CODE,500,500);
                BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                qrCode.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        });
    }
}
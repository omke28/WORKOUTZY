package com.profilepage_workoutzy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.InetAddresses;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 123;

    CircleImageView imageView;
    FloatingActionButton btnPick;


    EditText namn, mejl, födelsedag;
    Button button, back;
    SharedPreferences sp;
    String namnStr, mejlStr, dagStr;

    TextView textViewNamn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.ImageView);
        btnPick = findViewById(R.id.btnPickImage);

        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pick an image"), GALLERY_REQUEST_CODE);
            }
        });


        namn = findViewById(R.id.namn);
        mejl = findViewById(R.id.mejl);
        födelsedag = findViewById(R.id.födelsedag);
        button = findViewById(R.id.save);
        back = findViewById(R.id.back);

        textViewNamn = findViewById(R.id.textViewNamn);

        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);


        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                namnStr = namn.getText().toString();
                mejlStr = mejl.getText().toString();
                dagStr = födelsedag.getText().toString();

                textViewNamn.setText(namnStr);

                SharedPreferences.Editor editor =  sp.edit();

                editor.putString("namn",namnStr);
                editor.putString("mejl",mejlStr);
                editor.commit();
                Toast.makeText(MainActivity.this, "Changes Saved", Toast.LENGTH_LONG).show();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(MainActivity.this, OtherPage.class);
             startActivity(intent);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            Uri imageData = data.getData();

            imageView.setImageURI(imageData);
        }
    }


}






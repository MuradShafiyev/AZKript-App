package com.muradshafiyev.cryptotool;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    GridView grid;
    String[] algo = {
            "MD5",
            "Atbash Cipher",
            "Caesar Cipher",
            "Base64",
            "Message Reversal"
    };

    int[] imageId = {
            R.drawable.ic_md5,
            R.drawable.ic_atbash_cipher,
            R.drawable.ic_caesar_cipher,
            R.drawable.ic_base64,
            R.drawable.ic_message_reversal
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_main);

        //TO HIDE ACTION BAR
//        Objects.requireNonNull(getSupportActionBar()).hide();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext()
                                ,UserProfile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.dashboard:
                        return true;
                }

                return false;
            }
        });



        CustomGrid adapter = new CustomGrid(MainActivity.this, algo, imageId);
        grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        startActivity(new Intent(getApplicationContext(), MD5_hash.class));
                        break;
                    case 1:
                        startActivity(new Intent(getApplicationContext(), AtbashCipher.class));
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(), CaesarCipher.class));
                        break;
                    case 3:
                        startActivity(new Intent(getApplicationContext(), Base64.class));
                        break;
                    case 4:
                        startActivity(new Intent(getApplicationContext(), MessageReversal.class));
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
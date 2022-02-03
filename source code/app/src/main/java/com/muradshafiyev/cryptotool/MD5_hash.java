package com.muradshafiyev.cryptotool;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5_hash extends AppCompatActivity {

    private Button encrypt, decrypt;
    private EditText plain_text, cipher_text;
    private TextView scrnOutput;
    private ImageButton imgbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md5_hash);

        encrypt = findViewById(R.id.btnEncrypt);
        decrypt = findViewById(R.id.btnDecrypt);
        scrnOutput = findViewById(R.id.txtView2);
        plain_text = findViewById(R.id.plainTxt);
        cipher_text = findViewById(R.id.cipherTxt);

        // setting onCLickLister on encrypt button
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encryptText(plain_text.getText().toString());
            }
        });

        imgbtn = findViewById(R.id.imageBtn);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


    }


    public static class MD5 {
        public static String getMD5(String plaintext){
            try {

                // Static getInstance method is called with hashing MD5
                MessageDigest md = MessageDigest.getInstance("MD5");

                // digest() method is called to calculate message digest
                //  of an input digest() return array of byte
                byte[] messageDigest = md.digest(plaintext.getBytes());

                // Convert byte array into signum representation
                BigInteger no = new BigInteger(1, messageDigest);

                // Convert message digest into hex value
                String hashtext = no.toString(16);
                while (hashtext.length() < 32) {
                    hashtext = "0" + hashtext;
                }
                return hashtext;
            }

            // For specifying wrong message digest algorithms
            catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

    }


    // method to show the final output on the output
    // textView when encrypt button is clicked
    public String encryptText(String message) {
        message = message.toLowerCase();
        String cipherText = MD5.getMD5(message);

        scrnOutput.setText(cipherText);
        cipher_text.setText(cipherText);

        // returning the final ciphertext
        return cipherText;
    }
}
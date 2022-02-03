package com.muradshafiyev.cryptotool;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Base64 extends AppCompatActivity {

    private Button encrypt, decrypt;
    private EditText plain_text, cipher_text;
    private TextView scrnOutput;
    ImageButton imgbtn;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base64);

        encrypt = findViewById(R.id.btnEncrypt);
        decrypt = findViewById(R.id.btnDecrypt);
        scrnOutput = findViewById(R.id.txtView2);
        plain_text = findViewById(R.id.plainTxt);
        cipher_text = findViewById(R.id.cipherTxt);

        // setting onCLickListener on encrypt button
        // Lambda expression
        encrypt.setOnClickListener(view -> encodeText(plain_text.getText().toString()));

//        encrypt.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onClick(View view) {
//                encodeText(plain_text.getText().toString());
//            }
//        });


        // setting onCLickLister on decrypt button
        // Lambda expression
        decrypt.setOnClickListener(view -> decodeText(cipher_text.getText().toString()));

//        decrypt.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onClick(View view) {
//                decodeText(cipher_text.getText().toString());
//            }
//        });


        imgbtn = findViewById(R.id.imageBtn);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    public static class base64_class{
        @RequiresApi(api = Build.VERSION_CODES.O)
        public static String encode(String plaintext){
            // Encode into Base64 format
            String ciphertext = java.util.Base64
                    .getEncoder().encodeToString(plaintext.getBytes());
            return ciphertext;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public static String decode(String ciphertext){
            // decode into String from encoded format
            byte[] actualByte = java.util.Base64.getDecoder()
                    .decode(ciphertext);
            String plaintext = new String(actualByte);
            return plaintext;
        }
    }

    // method to show the final output on the output
    // textView when encrypt button is clicked
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String encodeText(String message) {
        String cipherText = base64_class.encode(message);

        scrnOutput.setText(cipherText);
        cipher_text.setText(cipherText);

        // returning the final ciphertext
        return cipherText;
    }

    // method to show the final output on the output
    // textView when decrypt button is clicked
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void decodeText(String cipher) {
        scrnOutput.setText((base64_class.decode(cipher).toLowerCase()));
    }
}
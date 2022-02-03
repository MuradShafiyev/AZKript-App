package com.muradshafiyev.cryptotool;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AtbashCipher extends AppCompatActivity {


    private Button encrypt, decrypt;
    private EditText plain_text, cipher_text;
    private TextView scrnOutput;
    private ImageButton imgbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atbash_cipher);

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

        // setting onCLickLister on decrypt button
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decryptText(cipher_text.getText().toString());
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


    public static class atbash_cipher{
        public static String encrypt(String plaintext){
            String ciphertext = "";
            plaintext = removeUnwantedChar(plaintext.toLowerCase());
            for(char c : plaintext.toCharArray())
            {
                if(Character.isLetter(c)){
                    ciphertext += (char) ('a' + ('z' - c));
                } else {
                    ciphertext += c;
                }
            }
            return getSubStrings(ciphertext).trim();
        }

        private  static String removeUnwantedChar(String input){
            String str = "";
            for(char c : input.toCharArray())
            {
                if(Character.isLetterOrDigit(c))
                    str += c;
            }
            return str;
        }

        private static String getSubStrings(String input){
            String str = "";
            for(int i = 0; i < input.length(); i += 5)
            {
                if(i + 5 <= input.length()){
                    str += (input.substring(i, i + 5) + " ");
                } else {
                    str += (input.substring(i) + " ");
                }
            }
            return str;
        }


        public static String decrypt(String ciphertext){
            String plaintext = "";
            ciphertext = removeUnwantedChar(ciphertext.toLowerCase());
            for(char c : ciphertext.toCharArray())
            {
                if(Character.isLetter(c)){
                    plaintext += (char) ('z' + ('a' - c));
                } else {
                    plaintext += c;
                }
            }
            return plaintext;
        }
    }


    // method to show the final output on the output
    // textView when encrypt button is clicked
    public String encryptText(String message) {
        message = message.toLowerCase();
        String cipherText = atbash_cipher.encrypt(message);

        scrnOutput.setText(cipherText);
        cipher_text.setText(cipherText);
        // returning the final ciphertext
        return cipherText;
    }

    // method to show the final output on the output
    // textView when decrypt button is clicked
    public void decryptText(String cipher) {
        scrnOutput.setText((AtbashCipher.atbash_cipher.decrypt(cipher).toLowerCase()));
    }
}
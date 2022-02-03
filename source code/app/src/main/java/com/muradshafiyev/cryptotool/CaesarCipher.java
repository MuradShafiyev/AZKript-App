package com.muradshafiyev.cryptotool;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CaesarCipher extends AppCompatActivity {

    private Button encrypt, decrypt;
    private EditText plain_text, cipher_text, key;
    private TextView scrnOutput;
    private static final String alphabetString = "abcdefghijklmnopqrstuvwxyz";
    ImageButton imgbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar_cipher);

        // findViewById is the method that
        // finds the View by the ID it is given
        encrypt = findViewById(R.id.btnEncrypt);
        decrypt = findViewById(R.id.btnDecrypt);
        scrnOutput = findViewById(R.id.txtView2);
        plain_text = findViewById(R.id.plainTxt);
        cipher_text = findViewById(R.id.cipherTxt);
        key = findViewById(R.id.keyTxt);

        // setting onCLickLister on encrypt button
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encryptText(plain_text.getText().toString(), Integer.parseInt(key.getText().toString()));
            }
        });

        // setting onCLickLister on decrypt button
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decryptText(cipher_text.getText().toString(), Integer.parseInt(key.getText().toString()));
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

    public static class caesar_cipher{

        // Ehtiyac olan dəyişənlərin elan edilməsi
        private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static int index;
        private static int updated_index;
        private static int final_index;
        private static int index_p_t_l;
        private static int index_s_t_l;
        private static String plainTxt;
        private static String cipherTxt;
        private static String finalTxt;

        // code for encryption
        public static String encrypt1(String plaintext, int encryptionKey) {
            reset();
            // plaintext is converted to uppercase
            // so that it is easy to convert according
            // to Caesar Cipher algorithm
            plaintext = plaintext.toUpperCase();
            // using a for loop the use index and
            // change text with help of it
            for (index = 0; index < plaintext.length(); index++) {
                // checking the condition for plaintext to be
                // null at some character position
                if (plaintext.charAt(index) != ' ') {
                    index_p_t_l = alphabet.indexOf(plaintext.charAt(index));
                    // index is being updated
                    // so that next and final index
                    // be used for ciphertext
                    updated_index = encryptionKey + alphabet.indexOf(plaintext.charAt(index));
                    if (updated_index >= alphabet.length()) {
                        final_index = updated_index - alphabet.length();
                    } else
                        final_index = updated_index;
                    // substring is used so that every character
                    // can be separately converted to ciphertext
                    cipherTxt = alphabet.substring(final_index, final_index + 1);
                    finalTxt = finalTxt + cipherTxt;
                }
            }
            // returning the
            // final changed text
            return finalTxt;
        }

        // code for decryption
        public static String decrypt1(String ciphertext, int decryptionKey) {
            reset();
            ciphertext = ciphertext.toUpperCase();
            // using a for loop the use index and
            // change text with help of it
            for (index = 0; index < ciphertext.length(); index++) {
                if (ciphertext.charAt(index) != ' ') {
                    index_p_t_l = alphabet.indexOf(ciphertext.charAt(index));
                    index_s_t_l = index_p_t_l;
                    // index is updated with help of decryption
                    // key which we provided as input
                    updated_index = alphabet.indexOf(ciphertext.charAt(index)) - decryptionKey;
                    if (updated_index < 0) {
                        final_index = updated_index + alphabet.length();
                    } else
                        final_index = updated_index;
                    // reverse of encryption is done as
                    // substring here is used to convert
                    // each ciphertext character to plaintext
                    plainTxt = alphabet.substring(final_index, final_index + 1);
                    finalTxt += plainTxt;
                }
            }
            // returning the
            // final changed text
            return finalTxt;
        }

        // method to reset the text
        // in the output textview
        private static void reset() {
            finalTxt = "";
        }
    }


    // method to show the final output on the output
    // textView when encrypt button is clicked
    public String encryptText(String message, int shiftkey) {
        message = message.toLowerCase();
        String cipherText = "";
        for (int i = 0; i < message.length(); i++) {
            int charPosition = alphabetString.indexOf(message.charAt(i));
            int keyValue = (shiftkey + charPosition) % 26;
            char replaceVAL = alphabetString.charAt(keyValue);
            cipherText += replaceVAL;
            scrnOutput.setText(cipherText);
            cipher_text.setText(cipherText);
        }

        // returning the final ciphertext
        return cipherText;
    }


    // method to show the final output on the output
    // textView when decrypt button is clicked
    public void decryptText(String cipher, int key) {
        scrnOutput.setText((caesar_cipher.decrypt1(cipher, key).toLowerCase()));
    }
}
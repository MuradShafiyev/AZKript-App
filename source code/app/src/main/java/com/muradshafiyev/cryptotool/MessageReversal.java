package com.muradshafiyev.cryptotool;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MessageReversal extends AppCompatActivity {

    private Button reverse;
    private EditText plain_text, cipher_text;
    private TextView scrnOutput;
    ImageButton imgbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_reversal);

        reverse = findViewById(R.id.btnEncrypt);
        scrnOutput = findViewById(R.id.txtView2);
        plain_text = findViewById(R.id.plainTxt);
        cipher_text = findViewById(R.id.cipherTxt);

        // setting onCLickListener on encrypt button
        // Lambda expression
        reverse.setOnClickListener(view -> reverseText(plain_text.getText().toString()));


        imgbtn = findViewById(R.id.imageBtn);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    public static class message_reversal{
        public static String reverse(String str){
            String output = "";
            char c;
            for(int i = 0; i < str.length(); i++)
            {
                c = str.charAt(i);
                output = c + output;
            }
            return output;
        }
    }

    public String reverseText(String message) {
        message = message.toLowerCase();
        String outputText = MessageReversal.message_reversal.reverse(message);

        scrnOutput.setText(outputText);
        cipher_text.setText(outputText);

        return outputText;
    }
}
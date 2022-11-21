package com.guy.class23a_ands_3;

 import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SharedPreferences preferences = getSharedPreferences("MY_DB", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("SP_KEY_NAME", "Guy");
        editor.putInt("SP_KEY_COINS", 100);
        editor.apply();

        (findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                read();
            }
        });
    }

    private void read() {
        SharedPreferences prfs = getSharedPreferences("MY_DB", Context.MODE_PRIVATE);
        String name = prfs.getString("SP_KEY_NAME", "");
        int coins = prfs.getInt("SP_KEY_COINS", 0);


        ((MaterialTextView) findViewById(R.id.txt)).setText(name + "\n" + coins);
    }
}
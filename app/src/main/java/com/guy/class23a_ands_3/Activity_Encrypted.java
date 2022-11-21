package com.guy.class23a_ands_3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.google.android.material.textview.MaterialTextView;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Activity_Encrypted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences();
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


    private SharedPreferences getSharedPreferences() {
        SharedPreferences preferences = null;
        try {

            MasterKey masterKey = new MasterKey.Builder(this)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            preferences = EncryptedSharedPreferences.create(
                    this,
                    "ENC_DB",
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return preferences;
    }

    private void read() {
        SharedPreferences preferences = getSharedPreferences();
        String name = preferences.getString("SP_KEY_NAME", "");
        int coins = preferences.getInt("SP_KEY_COINS", 0);


        ((MaterialTextView) findViewById(R.id.txt)).setText(name + "\n" + coins);
    }
}
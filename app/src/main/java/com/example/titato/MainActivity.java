package com.example.titato;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public void gamePVP(View view){
        Intent intent = new Intent(this, gameActivity.class);
        startActivity(intent); //As we can use finish activity to go back
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null){ //To handle the null pointer exception shown in logs without this, from google
            getSupportActionBar().hide(); //If the function is not returning a null value, then it proceeds
        }
        setContentView(R.layout.activity_main);
    }
}
package com.example.mobileapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class NewActivity extends AppCompatActivity {

    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        button = (Button)findViewById(R.id.button);
        try
        {
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    new Connection(NewActivity.this).execute();

                    Log.d("Error", "Message");
                }
            });

        }
        catch(Exception ex)
        {
            Log.d("Exception" , ex.getMessage());
        }

    }
}
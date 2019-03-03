package com.example.mobileapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

public class RentActivity extends AppCompatActivity {

    TextView txtMoreFilters;
    TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        txtMoreFilters = findViewById(R.id.txtMoreFilters);
        tableLayout = findViewById(R.id.tableLayout);

        txtMoreFilters.setOnClickListener(new MoreOptions());
    }

    class MoreOptions implements View.OnClickListener {

        @Override
        public void onClick(View v) {
         tableLayout.setVisibility(View.VISIBLE);
        }
    }
}

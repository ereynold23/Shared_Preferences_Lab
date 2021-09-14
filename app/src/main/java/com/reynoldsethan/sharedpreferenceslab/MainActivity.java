package com.reynoldsethan.sharedpreferenceslab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView top_left;
    TextView top_right;
    TextView bot_left;
    TextView bot_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        top_left = findViewById(R.id.top_left_button);
        top_right = findViewById(R.id.top_right_button);
        bot_left = findViewById(R.id.bot_left_button);
        bot_right = findViewById(R.id.bot_right_button);
        top_left.setOnClickListener(this);
        top_right.setOnClickListener(this);
        bot_right.setOnClickListener(this);
        bot_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView v = (TextView)view;
        v.setText(""+(Integer.parseInt(v.getText().toString())+1));
    }
}

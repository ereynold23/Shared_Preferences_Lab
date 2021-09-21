package com.reynoldsethan.sharedpreferenceslab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ShareCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView top_left;
    TextView top_right;
    TextView bot_left;
    TextView bot_right;
    TextView center;
    SeekBar font_changer;
    ConstraintLayout main_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        top_left = findViewById(R.id.top_left_button);
        top_right = findViewById(R.id.top_right_button);
        bot_left = findViewById(R.id.bot_left_button);
        bot_right = findViewById(R.id.bot_right_button);
        font_changer = findViewById(R.id.font_changer);
        center = findViewById(R.id.center_number);
        top_left.setOnClickListener(this);
        top_right.setOnClickListener(this);
        bot_right.setOnClickListener(this);
        bot_left.setOnClickListener(this);
        center.setOnClickListener(this);
        main_layout = findViewById(R.id.main_layout);
        main_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                top_left.setText("0");
                top_right.setText("0");
                bot_left.setText("0");
                bot_right.setText("0");
                font_changer.setProgress(50);
                center.setText("0");
                SharedPreferences wipe = getSharedPreferences("AppSave", MODE_PRIVATE);
                SharedPreferences.Editor remove = wipe.edit();
                remove.clear();
                remove.apply();
                return false;
            }
        });
        font_changer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                top_left.setTextSize(i);
                top_right.setTextSize(i);
                bot_left.setTextSize(i);
                bot_right.setTextSize(i);
                center.setText("" + (Integer.parseInt(center.getText().toString())+i));
                Snackbar snackbar = Snackbar.make(main_layout, "Font Changed to " + i + "dp.", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        TextView v = (TextView)view;
        v.setText(""+(Integer.parseInt(v.getText().toString())+1));

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("AppSave", MODE_PRIVATE);
        int tl = sh.getInt("top_left", 0);
        int tr = sh.getInt("top_right", 0);
        int bl = sh.getInt("bot_left", 0);
        int br = sh.getInt("bot_right", 0);
        int ct = sh.getInt("center", 0);
        int sb = sh.getInt("font_changer", 0);
        top_left.setText(String.valueOf(tl));
        top_right.setText(String.valueOf(tr));
        bot_left.setText(String.valueOf(bl));
        bot_right.setText(String.valueOf(br));
        center.setText(String.valueOf(ct));
        font_changer.setProgress(sb);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("AppSave", MODE_PRIVATE);
        SharedPreferences.Editor save = sharedPreferences.edit();
        save.putInt("top_right", Integer.parseInt(top_right.getText().toString()));
        save.putInt("top_left", Integer.parseInt(top_left.getText().toString()));
        save.putInt("bot_right", Integer.parseInt(bot_right.getText().toString()));
        save.putInt("bot_left", Integer.parseInt(bot_left.getText().toString()));
        save.putInt("center", Integer.parseInt(center.getText().toString()));
        save.putInt("font_changer", font_changer.getProgress());
        save.apply();
    }
}

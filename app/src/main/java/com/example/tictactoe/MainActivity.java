package com.example.tictactoe;

import static com.example.tictactoe.R.raw.winning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class MainActivity extends AppCompatActivity {

    Button btn11, btn12, btn13, btn21, btn22, btn23, btn31, btn32, btn33;
    boolean flag, sleepFlag;
    String s11, s12, s13, s21, s22, s23, s31, s32, s33;
    String ans;
    int count;
    TextView winText;
    MediaPlayer win, tie, click, dualclick;
    KonfettiView konfetti;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flag = sleepFlag = false;
        count = 0;
        ans = null;
        init();
    }

    private void init() {
        btn11 = findViewById(R.id.btn11);
        btn12 = findViewById(R.id.btn12);
        btn13 = findViewById(R.id.btn13);
        btn21 = findViewById(R.id.btn21);
        btn22 = findViewById(R.id.btn22);
        btn23 = findViewById(R.id.btn23);
        btn31 = findViewById(R.id.btn31);
        btn32 = findViewById(R.id.btn32);
        btn33 = findViewById(R.id.btn33);

        win = MediaPlayer.create(this, R.raw.winning);
        tie = MediaPlayer.create(this, R.raw.loose);
        click = MediaPlayer.create(this, R.raw.click_sound);
        dualclick = MediaPlayer.create(this, R.raw.dooont);

        konfetti = findViewById(R.id.konfettiView);
        winText = findViewById(R.id.winText);
        linearLayout = findViewById(R.id.linLayout);
    }

    public void reset(View v) {
        if (sleepFlag == true)
            return;

        flag = false;
        count = 0;
        ans = null;
        click.start(); //playing audio
        btn11.setText("");
        btn11.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.purple_500)));
        btn12.setText("");
        btn12.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.purple_500)));
        btn13.setText("");
        btn13.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.purple_500)));
        btn21.setText("");
        btn21.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.purple_500)));
        btn22.setText("");
        btn22.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.purple_500)));
        btn23.setText("");
        btn23.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.purple_500)));
        btn31.setText("");
        btn31.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.purple_500)));
        btn32.setText("");
        btn32.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.purple_500)));
        btn33.setText("");
        btn33.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.purple_500)));
    }

    public void check(View view) {
        if (sleepFlag == true)
            return;

        Button btn = (Button) view;
        String str = btn.getText().toString();
        if (!str.isEmpty()) {
            dualclick.start(); //playing audio
            Toast.makeText(this, "Please Select Another Block ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (flag == false) {
            btn.setText("O");
            btn.setTextColor(getResources().getColor(R.color.green));
            btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            flag = true;
        } else {
            btn.setText("X");
            btn.setTextColor(getResources().getColor(R.color.red));
            btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            flag = false;
        }
        click.start(); //playing audio
        count++;
        if (count >= 5) {
            s11 = btn11.getText().toString();
            s12 = btn12.getText().toString();
            s13 = btn13.getText().toString();
            s21 = btn21.getText().toString();
            s22 = btn22.getText().toString();
            s23 = btn23.getText().toString();
            s31 = btn31.getText().toString();
            s32 = btn32.getText().toString();
            s33 = btn33.getText().toString();

            if (!s11.equals("") && s11.equals(s12) && s11.equals(s13)) //row 1
            {
                Log.d("", "Con 1");
                ans = s11;
            } else if (!s11.equals("") && s11.equals(s21) && s11.equals(s31)) //col 1
            {
                Log.d("", "Con 2");
                ans = s11;
            } else if (!s11.equals("") && s11.equals(s22) && s11.equals(s33)) //dig 1
            {
                Log.d("", "Con 3");
                ans = s11;
            } else if (!s22.equals("") && s22.equals(s21) && s22.equals(s23)) //row 2
            {
                Log.d("", "Con 4");
                ans = s22;
            } else if (!s22.equals("") && s22.equals(s12) && s22.equals(s32)) //col 2
            {
                Log.d("", "Con 5");
                ans = s22;
            } else if (!s22.equals("") && s22.equals(s13) && s22.equals(s31)) //dig 2
            {
                Log.d("", "Con 6");
                ans = s22;
            } else if (!s33.equals("") && s33.equals(s31) && s33.equals(s32)) // row 3
            {
                Log.d("", "Con 7");
                ans = s33;
            } else if (!s33.equals("") && s33.equals(s13) && s33.equals(s23)) // col 3
            {
                Log.d("", "Con 8");
                ans = s33;
            }


            if (ans != null && ans.length() > 0) //someone won
            {
                win.start(); //playing audio

                EmitterConfig emitter = new Emitter(2, TimeUnit.SECONDS).max(2000);
                konfetti.start(new PartyFactory(emitter)
                        .shapes(Shape.Circle.INSTANCE, Shape.Square.INSTANCE)
                        .spread(360)
                        .position(0, 0.0, 1, 0.0)
                        .sizes(new Size(8, 1, 10))
                        .timeToLive(1000)
                        .build());

                //    Toast.makeText(this, ans + " WON🤩", Toast.LENGTH_LONG).show();
                sleepFlag = true;

                winText.setText(ans + " WON🤩");
                if (ans.equals("X")) {
                    winText.setTextColor(getResources().getColor(R.color.red));
                } else {
                    winText.setTextColor(getResources().getColor(R.color.green));
                }
                linearLayout.setAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha2));
                winText.setVisibility(View.VISIBLE);
                winText.setAnimation(AnimationUtils.loadAnimation(this, R.anim.result_animation1));
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        winText.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.result_animation2));
                        linearLayout.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha1));
                    }
                }, 1000);


                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // this code will be executed after 2 seconds
                        sleepFlag = false;
                        reset(null);
                        winText.setVisibility(View.INVISIBLE);
                    }
                }, 2500);
            } else if (count == 9) {
                tie.start(); //playing audio
                Toast.makeText(this, "It's a TIE😂", Toast.LENGTH_LONG).show();
                sleepFlag = true;
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // this code will be executed after 2 seconds
                        sleepFlag = false;
                        reset(null);
                    }
                }, 3000);
            }
        }
    }
}
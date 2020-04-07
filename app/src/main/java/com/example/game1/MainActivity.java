package com.example.game1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    String text="A";
    ImageView imageViewspin;
    Random random;
    int old=0,lastest=0;
    private static final float FACTOR=6.92f;
    int round = 1;
    int time = 59;
    int score = 0;
    Animation animation;
    String list_all_fruits;
    String list_all_animals;
    String list_all_birds;
    String list_all_cities;
    TextView timer,point;
    ImageView next;
    String list_all_things;
    TextView coin;
    Boolean flags_solo, flags_team, flags_create;
    Boolean f_name, f_place, f_thing, f_animal, f_bird, f_fruit, f_movie, f_song;
    TextView t_name, t_place, t_thing, t_animal, t_bird, t_fruit, t_movie, t_song;
    EditText e_name, e_place, e_thing, e_animal, e_bird, e_fruit, e_movie, e_song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer();
        imageViewspin = findViewById ( R.id.imageView4 );
        random = new Random();
        spin();
        point=(TextView)findViewById(R.id.point);
        flags_create = getIntent().getExtras().getBoolean("flags_create");
        flags_solo = getIntent().getExtras().getBoolean("flags_solo");
        flags_team = getIntent().getExtras().getBoolean("flags_team");
        f_name = getIntent().getExtras().getBoolean("f_name");
        timer = (TextView) findViewById(R.id.timer);
        f_place = getIntent().getExtras().getBoolean("f_place");
        f_thing = getIntent().getExtras().getBoolean("f_thing");
        f_animal = getIntent().getExtras().getBoolean("f_animal");
        f_bird = getIntent().getExtras().getBoolean("f_bird");
        f_fruit = getIntent().getExtras().getBoolean("f_fruit");
        f_movie = getIntent().getExtras().getBoolean("f_movie");
        f_song = getIntent().getExtras().getBoolean("f_song");
        t_name = (TextView) findViewById(R.id.pname);
        t_place = (TextView) findViewById(R.id.place);
        t_thing = (TextView) findViewById(R.id.thing);
        t_animal = (TextView) findViewById(R.id.animal);
        //   t_bird=(TextView)findViewById(R.id.Birds);
        t_fruit = (TextView) findViewById(R.id.fruit);
        t_movie = (TextView) findViewById(R.id.movie);
        t_song = (TextView) findViewById(R.id.song);
        e_name = (EditText) findViewById(R.id.namef);
        e_place = (EditText) findViewById(R.id.placef);
        e_thing = (EditText) findViewById(R.id.thingf);
        e_animal = (EditText) findViewById(R.id.animalf);
        // e_bird=(EditText)findViewById(R.id.);
        e_fruit = (EditText) findViewById(R.id.fruitf);
        e_movie = (EditText) findViewById(R.id.movief);
        e_song = (EditText) findViewById(R.id.songf);
        coin = (TextView) findViewById(R.id.coin);
        if (flags_create == true) {
            set_visibility();
            play_create();
        } else if (flags_solo == true) {
            set_visibility();
            play_solo();
        } else if (flags_team == true) {
            set_visibility();
            play_team();
        }
    }
    private void set_visibility() {
        if (f_animal == false) {
            t_animal.setVisibility(View.INVISIBLE);
            e_animal.setVisibility(View.INVISIBLE);
        }
        if (f_name == false) {
            t_name.setVisibility(View.INVISIBLE);
            e_name.setVisibility(View.INVISIBLE);
        }
        if (f_place == false) {
            t_place.setVisibility(View.INVISIBLE);
            e_place.setVisibility(View.INVISIBLE);
        }
        if (f_thing == false) {
            t_thing.setVisibility(View.INVISIBLE);
            e_thing.setVisibility(View.INVISIBLE);
        }
        if (f_fruit == false) {
            t_fruit.setVisibility(View.INVISIBLE);
            e_fruit.setVisibility(View.INVISIBLE);
        }
        if (f_song == false) {
            t_song.setVisibility(View.INVISIBLE);
            e_song.setVisibility(View.INVISIBLE);
        }
        if (f_movie == false) {
            t_movie.setVisibility(View.INVISIBLE);
            e_movie.setVisibility(View.INVISIBLE);
        }
        /*if(f_bird==false)
        {
            t_bird.setVisibility(View.INVISIBLE);
            e_bird.setVisibility(View.INVISIBLE);
        }*/
    }

    private void play_create() {
    }

    private void play_solo() {
        if (f_animal == true) {
            try {
                InputStream is = getAssets().open("listAnimals.txt");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                list_all_animals = new String(buffer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (f_place == true) {
            try {
                InputStream is = getAssets().open("listCities.txt");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                list_all_cities = new String(buffer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (f_thing == true) {
            try {
                InputStream is = getAssets().open("things.txt");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                list_all_things = new String(buffer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (f_fruit == true) {
            try {
                InputStream is = getAssets().open("listallFruits-veg.txt");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                list_all_fruits = new String(buffer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private void play_team() {
    }
    private void iscontain(String text, String s) {
        String pattern = "\\b" + s + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        if (m.find() == true) {
                score += 10;
            }
        point.setText(""+score);
        }
    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
    public void timer() {
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("0:" + checkDigit(time));
                time--;
            }
            public void onFinish() {
                e_place.setEnabled(false);
                e_animal.setEnabled(false);
                e_thing.setEnabled(false);
                e_name.setEnabled(false);
                e_fruit.setEnabled(false);
                e_movie.setEnabled(false);
                e_song.setEnabled(false);
                //if (e_fruit.getText().toString() != null)
                    iscontain(list_all_fruits, e_fruit.getText().toString().trim());
                //if (e_animal.getText().toString() != null)
                    iscontain(list_all_animals, e_animal.getText().toString().trim());
                //if (e_thing.getText().toString() != null)
                    iscontain(list_all_things, e_thing.getText().toString().trim());
                //if (e_place.getText().toString() != null)
                    iscontain(list_all_cities, e_place.getText().toString().trim());
                point.setText(""+score);
                next_round();
            }
        }.start();
    }
    public void next_round() {
        if (round <= 1) {
            spin();
            round++;
            time = 59;
            timer();
            e_place.setEnabled(true);
            e_place.setText(null);
            e_animal.setEnabled(true);
            e_animal.setText(null);
            e_thing.setEnabled(true);
            e_thing.setText(null);
            e_name.setEnabled(true);
            e_name.setText(null);
            e_fruit.setEnabled(true);
            e_fruit.setText(null);
            e_movie.setEnabled(true);
            e_movie.setText(null);
            e_song.setEnabled(true);
            e_song.setText(null);
        }
    }
    private String Number(int lastest)
    {
        if(lastest >= (FACTOR * 1) && lastest < (FACTOR * 3))
        {
            text ="A";
        }
        if(lastest >= (FACTOR * 3) && lastest < (FACTOR * 5))
        {
            text ="B";
        }
        if(lastest >= (FACTOR * 5) && lastest < (FACTOR * 7))
        {
            text ="C";
        }
        if(lastest >= (FACTOR * 7) && lastest < (FACTOR * 9))
        {
            text ="D";
        }
        if(lastest >= (FACTOR * 9) && lastest < (FACTOR * 11))
        {
            text ="E";
        }
        if(lastest >= (FACTOR * 11) && lastest < (FACTOR * 13))
        {
            text ="F";
        }
        if(lastest >= (FACTOR * 13) && lastest < (FACTOR * 15))
        {
            text ="G";
        }
        if(lastest >= (FACTOR * 15) && lastest < (FACTOR * 17))
        {
            text ="H";
        }
        if(lastest >= (FACTOR * 17) && lastest < (FACTOR * 19))
        {
            text ="I";
        }
        if(lastest >= (FACTOR * 19) && lastest < (FACTOR * 21))
        {
            text ="J";
        }
        if(lastest >= (FACTOR * 21) && lastest < (FACTOR * 23))
        {
            text ="K";
        }
        if(lastest >= (FACTOR * 23) && lastest < (FACTOR * 25))
        {
            text ="L";
        }
        if(lastest >= (FACTOR * 25) && lastest < (FACTOR * 27))
        {
            text ="M";
        }
        if(lastest >= (FACTOR * 27) && lastest < (FACTOR * 29))
        {
            text ="N";
        }
        if(lastest >= (FACTOR * 29) && lastest < (FACTOR * 31))
        {
            text ="O";
        }
        if(lastest >= (FACTOR * 31) && lastest < (FACTOR * 33))
        {
            text ="P";
        }
        if(lastest >= (FACTOR * 33) && lastest < (FACTOR * 35))
        {
            text ="Q";
        }
        if(lastest >= (FACTOR * 35) && lastest < (FACTOR * 37))
        {
            text ="R";
        }
        if(lastest >= (FACTOR * 37) && lastest < (FACTOR * 39))
        {
            text ="S";
        }
        if(lastest >= (FACTOR * 39) && lastest < (FACTOR * 41))
        {
            text ="T";
        }
        if(lastest >= (FACTOR * 41) && lastest < (FACTOR * 43))
        {
            text ="U";
        }
        if(lastest >= (FACTOR * 43) && lastest < (FACTOR * 45))
        {
            text ="V";
        }
        if(lastest >= (FACTOR * 45) && lastest < (FACTOR * 47))
        {
            text ="W";
        }
        if(lastest >= (FACTOR * 47) && lastest < (FACTOR * 49))
        {
            text ="X";
        }
        if(lastest >= (FACTOR * 49) && lastest < (FACTOR * 51))
        {
            text ="Y";
        }
        if(lastest >=(FACTOR *51) && lastest<360 || (lastest>=0 && lastest<(FACTOR*1)))
        {
            text="Z";
        }
        return text;
    }
    public void spin()
    {   old=0;lastest=0;
        old = lastest % 360;
        lastest = random.nextInt ( 3600 ) + 720;
        RotateAnimation rotateAnimation = new RotateAnimation ( old , lastest , RotateAnimation.RELATIVE_TO_SELF ,
                0.5f ,
                RotateAnimation.RELATIVE_TO_SELF , 0.5f
        );
        rotateAnimation.setDuration ( 3600 );
        rotateAnimation.setFillAfter ( true );
        rotateAnimation.setInterpolator ( new DecelerateInterpolator() );
        rotateAnimation.setAnimationListener ( new Animation.AnimationListener () {
            @Override
            public void onAnimationStart(Animation animation) {
                //Tvspin.setText ( "hello" );
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                    point.setText(null);
                point.setText (""+ Number ( 360 - (lastest % 360) ) );

            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        } );
        imageViewspin.startAnimation ( rotateAnimation);
    }
}